package com.octoperf.kraken.security.client.keycloak;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.octoperf.kraken.config.security.client.api.SecurityClientCredentialsProperties;
import com.octoperf.kraken.config.security.client.api.SecurityClientProperties;
import com.octoperf.kraken.security.entity.token.KrakenToken;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class KeycloakSecurityClientTest {

  private ObjectMapper mapper;
  private MockWebServer server;
  private KeycloakSecurityClient client;
  private String tokenStr;
  private KrakenToken krakenToken;

  @Mock(lenient = true)
  SecurityClientProperties securityClientProperties;
  @Mock(lenient = true)
  SecurityClientCredentialsProperties apiCredentials;
  @Mock(lenient = true)
  SecurityClientCredentialsProperties webCredentials;
  @Mock(lenient = true)
  SecurityClientCredentialsProperties containerCredentials;


  @BeforeEach
  public void setUp() throws IOException {
    server = new MockWebServer();
    mapper = new ObjectMapper();
    final String url = server.url("/auth").toString();
    given(securityClientProperties.getUrl()).willReturn(url);
    given(securityClientProperties.getRealm()).willReturn("kraken");
    given(webCredentials.getId()).willReturn("kraken-web");
    given(apiCredentials.getId()).willReturn("kraken-api");
    given(apiCredentials.getSecret()).willReturn("api-secret");
    given(containerCredentials.getId()).willReturn("kraken-container");
    given(containerCredentials.getSecret()).willReturn("container-secret");
    client = (KeycloakSecurityClient) new KeycloakSecurityClientBuilder(securityClientProperties).build().block();

    tokenStr = mapper.writeValueAsString(ImmutableMap.of("access_token", "accessToken",
        "refresh_token", "refreshToken",
        "expires_in", "300",
        "refresh_expires_in", "1800"
    ));
    krakenToken = KrakenToken.builder()
        .accessToken("accessToken")
        .refreshToken("refreshToken")
        .expiresIn(300L)
        .refreshExpiresIn(1800L)
        .build();
  }

  @AfterEach
  public void tearDown() throws IOException {
    server.shutdown();
  }

  @Test
  @Timeout(5)
  public void shouldUserLogin() throws InterruptedException {
    System.out.println("test");

    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setBody(tokenStr)
    );

    final var token = client.userLogin(webCredentials, "username", "password").block();
    assertThat(token).isNotNull();
    assertThat(token).isEqualTo(krakenToken);

    final var request = server.takeRequest();
    assertThat(request.getMethod()).isEqualTo("POST");
    assertThat(request.getPath()).isEqualTo("/auth/realms/kraken/protocol/openid-connect/token");
    assertThat(request.getBody().readUtf8()).isEqualTo("username=username&password=password&grant_type=password&client_id=kraken-web");
  }

  @Test
  @Timeout(5)
  public void shouldClientLogin() throws InterruptedException {
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setBody(tokenStr)
    );

    final var token = client.clientLogin(apiCredentials).block();
    assertThat(token).isNotNull();
    assertThat(token).isEqualTo(krakenToken);

    final var request = server.takeRequest();
    assertThat(request.getMethod()).isEqualTo("POST");
    assertThat(request.getPath()).isEqualTo("/auth/realms/kraken/protocol/openid-connect/token");
    assertThat(request.getBody().readUtf8()).isEqualTo("client_id=kraken-api&client_secret=api-secret&grant_type=client_credentials");
  }

  @Test
  @Timeout(5)
  public void shouldExchangeToken() throws InterruptedException {
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setBody(tokenStr)
    );

    final var token = client.exchangeToken(containerCredentials, "accessToken").block();
    assertThat(token).isNotNull();
    assertThat(token).isEqualTo(krakenToken);

    final var request = server.takeRequest();
    assertThat(request.getMethod()).isEqualTo("POST");
    assertThat(request.getPath()).isEqualTo("/auth/realms/kraken/protocol/openid-connect/token");
    assertThat(request.getBody().readUtf8()).isEqualTo("client_id=kraken-container&client_secret=container-secret&grant_type=urn%3Aietf%3Aparams%3Aoauth%3Agrant-type%3Atoken-exchange&subject_token=accessToken&requested_token_type=urn%3Aietf%3Aparams%3Aoauth%3Atoken-type%3Arefresh_token&audience=kraken-container");
  }

  @Test
  @Timeout(5)
  public void shouldRefreshToken() throws InterruptedException {
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setBody(tokenStr)
    );

    final var token = client.refreshToken(containerCredentials, "refreshToken").block();
    assertThat(token).isNotNull();
    assertThat(token).isEqualTo(krakenToken);

    final var request = server.takeRequest();
    assertThat(request.getMethod()).isEqualTo("POST");
    assertThat(request.getPath()).isEqualTo("/auth/realms/kraken/protocol/openid-connect/token");
    assertThat(request.getBody().readUtf8()).isEqualTo("grant_type=refresh_token&refresh_token=refreshToken&client_id=kraken-container&client_secret=container-secret");
  }
}