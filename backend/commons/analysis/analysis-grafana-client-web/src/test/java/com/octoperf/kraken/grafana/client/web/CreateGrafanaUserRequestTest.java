package com.octoperf.kraken.grafana.client.web;

import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.Test;

public class CreateGrafanaUserRequestTest {

  public static final CreateGrafanaUserRequest REQUEST = CreateGrafanaUserRequest.builder()
      .name("name")
      .email("email")
      .login("login")
      .password("password")
      .build();

  @Test
  public void shouldPassEquals() {
    TestUtils.shouldPassEquals(REQUEST.getClass());
  }

  @Test
  public void shouldPassNPE() {
    TestUtils.shouldPassNPE(CreateGrafanaUserRequest.class);
  }

  @Test
  public void shouldPassToString() {
    TestUtils.shouldPassToString(REQUEST);
  }

}