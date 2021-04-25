package com.octoperf.kraken.security.configuration;

import com.octoperf.kraken.tests.web.security.AuthControllerTest;
import org.junit.jupiter.api.Test;

public class SecurityConfigurationTest extends AuthControllerTest {

  @Test
  public void shouldReturnUser() {
    webTestClient.get()
        .uri(uriBuilder -> uriBuilder.path("/test/user")
            .build())
        .header("Authorization", "Bearer user-token")
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .isEqualTo("hello username");
  }

  @Test
  public void shouldReturnUserCookie() {
    webTestClient.get()
        .uri(uriBuilder -> uriBuilder.path("/test/user")
            .build())
        .cookie(KrakenServerAuthenticationConverter.JWT_COOKIE_NAME, "user-token")
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .isEqualTo("hello username");
  }

  @Test
  public void shouldFailAdmin() {
    webTestClient.get()
        .uri(uriBuilder -> uriBuilder.path("/test/admin")
            .build())
        .header("Authorization", "Bearer user-token")
        .exchange()
        .expectStatus().is4xxClientError();
  }
}
