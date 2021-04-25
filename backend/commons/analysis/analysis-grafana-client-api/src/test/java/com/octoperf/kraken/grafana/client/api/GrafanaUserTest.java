package com.octoperf.kraken.grafana.client.api;

import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.Test;

public class GrafanaUserTest {

  public static final GrafanaUser GRAFANA_USER = GrafanaUser.builder()
      .id("id")
      .password("password")
      .email("email")
      .datasourceName("datasourceName")
      .orgId("orgId")
      .build();


  @Test
  public void shouldPassEquals() {
    TestUtils.shouldPassEquals(GRAFANA_USER.getClass());
  }

  @Test
  public void shouldPassToString() {
    TestUtils.shouldPassToString(GRAFANA_USER);
  }

  @Test
  public void shouldTestNPE() {
    TestUtils.shouldPassNPE(GrafanaUser.class);
  }

}