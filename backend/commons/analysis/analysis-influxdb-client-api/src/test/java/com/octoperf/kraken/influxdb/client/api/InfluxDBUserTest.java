package com.octoperf.kraken.influxdb.client.api;

import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.Test;

public class InfluxDBUserTest {
  public static final InfluxDBUser INFLUX_DB_USER = InfluxDBUser.builder()
      .username("username")
      .password("password")
      .database("database")
      .build();


  @Test
  public void shouldPassEquals() {
    TestUtils.shouldPassEquals(INFLUX_DB_USER.getClass());
  }

  @Test
  public void shouldPassNPE() {
    TestUtils.shouldPassNPE(InfluxDBUser.class);
  }

  @Test
  public void shouldPassToString() {
    TestUtils.shouldPassToString(INFLUX_DB_USER);
  }

}