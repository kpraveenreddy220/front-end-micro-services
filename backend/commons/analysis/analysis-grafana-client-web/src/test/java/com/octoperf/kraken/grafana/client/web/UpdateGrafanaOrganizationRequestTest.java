package com.octoperf.kraken.grafana.client.web;

import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.Test;

public class UpdateGrafanaOrganizationRequestTest {

  public static final UpdateGrafanaOrganizationRequest REQUEST = UpdateGrafanaOrganizationRequest.builder()
      .name("name")
      .build();


  @Test
  public void shouldPassEquals() {
    TestUtils.shouldPassEquals(REQUEST.getClass());
  }

  @Test
  public void shouldPassNPE() {
    TestUtils.shouldPassNPE(UpdateGrafanaOrganizationRequest.class);
  }

  @Test
  public void shouldPassToString() {
    TestUtils.shouldPassToString(REQUEST);
  }

}