package com.octoperf.kraken.grafana.client.web;

import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.Test;

public class CreateGrafanaUserResponseTest {

  public static final CreateGrafanaUserResponse RESPONSE = CreateGrafanaUserResponse.builder()
      .id(2L)
      .message("messag")
      .build();


  @Test
  public void shouldPassEquals() {
    TestUtils.shouldPassEquals(RESPONSE.getClass());
  }

  @Test
  public void shouldPassNPE() {
    TestUtils.shouldPassNPE(CreateGrafanaUserResponse.class);
  }

  @Test
  public void shouldPassToString() {
    TestUtils.shouldPassToString(RESPONSE);
  }

}