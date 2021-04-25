package com.octoperf.kraken.analysis.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTypeTest {

  @Test
  public void shouldIsDebug() {
    assertThat(ResultType.RUN.isDebug()).isFalse();
    assertThat(ResultType.DEBUG.isDebug()).isTrue();
    assertThat(ResultType.HAR.isDebug()).isTrue();
  }

}