package com.octoperf.kraken.security.entity.token;

import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.Test;

public class KrakenTokenTest {

  public static final KrakenToken KRAKEN_TOKEN = KrakenToken.builder()
      .accessToken("accessToken")
      .refreshToken("refreshToken")
      .expiresIn(300L)
      .refreshExpiresIn(1800L)
      .build();

  @Test
  public void shouldPassEquals() {
    TestUtils.shouldPassEquals(KRAKEN_TOKEN.getClass());
  }

  @Test
  public void shouldPassToString() {
    TestUtils.shouldPassToString(KRAKEN_TOKEN);
  }

}
