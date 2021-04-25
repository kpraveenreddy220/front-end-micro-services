package com.octoperf.kraken.security.authentication.impersonate;

import com.octoperf.kraken.config.security.client.api.SecurityClientCredentialsProperties;
import com.octoperf.kraken.config.security.client.api.SecurityClientProperties;
import com.octoperf.kraken.security.client.api.SecurityClient;
import com.octoperf.kraken.security.decoder.api.TokenDecoder;
import com.octoperf.kraken.security.entity.token.KrakenTokenTest;
import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ImpersonateUserProviderTest {
  @Mock(lenient = true)
  SecurityClientProperties clientProperties;
  @Mock
  SecurityClientCredentialsProperties credentialsProperties;
  @Mock
  TokenDecoder decoder;
  @Mock
  SecurityClient client;

  ImpersonateUserProvider userProvider;

  @BeforeEach
  public void setUp() {
    given(clientProperties.getApi()).willReturn(credentialsProperties);
    userProvider = new ImpersonateUserProvider(clientProperties, decoder, Mono.just(client), "userId");
  }

  @Test
  public void shouldCreateToken() throws IOException {
    given(client.impersonate(credentialsProperties, "userId")).willReturn(Mono.just(KrakenTokenTest.KRAKEN_TOKEN));
    final var token = userProvider.newToken().block();
    assertThat(token).isNotNull();
    assertThat(token).isEqualTo(KrakenTokenTest.KRAKEN_TOKEN);
  }

  @Test
  public void shouldRefreshToken() throws IOException {
    given(client.refreshToken(credentialsProperties, "refreshToken")).willReturn(Mono.just(KrakenTokenTest.KRAKEN_TOKEN));
    final var tokenRefresh = userProvider.refreshToken(KrakenTokenTest.KRAKEN_TOKEN).block();
    assertThat(tokenRefresh).isNotNull();
    assertThat(tokenRefresh).isEqualTo(KrakenTokenTest.KRAKEN_TOKEN);
  }

  @Test
  public void shouldPassNPE() {
    TestUtils.shouldPassNPE(ImpersonateUserProvider.class);
  }
}