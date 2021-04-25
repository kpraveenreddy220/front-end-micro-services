package com.octoperf.kraken.influxdb.client.web;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static com.octoperf.kraken.influxdb.client.api.InfluxDBUser.*;
import static com.octoperf.kraken.influxdb.client.api.InfluxDBUserTest.INFLUX_DB_USER;
import static com.octoperf.kraken.security.entity.user.KrakenUserTest.KRAKEN_USER;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WebInfluxDBUserConverter.class)
public class WebInfluxDBUserConverterTest {

  @Autowired
  WebInfluxDBUserConverter converter;

  @Test
  public void shouldApply() {
    final var krakenUser = KRAKEN_USER.withAttributes(ImmutableMap.of(
        USERNAME_ATTRIBUTE, ImmutableList.of(INFLUX_DB_USER.getUsername()),
        PASSWORD_ATTRIBUTE, ImmutableList.of(INFLUX_DB_USER.getPassword()),
        DATABASE_ATTRIBUTE, ImmutableList.of(INFLUX_DB_USER.getDatabase())
    ));
    Assertions.assertThat(converter.apply(krakenUser)).isEqualTo(INFLUX_DB_USER);
  }
}