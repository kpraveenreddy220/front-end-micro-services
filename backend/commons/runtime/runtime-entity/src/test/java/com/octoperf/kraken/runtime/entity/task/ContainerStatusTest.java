package com.octoperf.kraken.runtime.entity.task;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContainerStatusTest {

  @Test
  public void shouldParse1() {
    Assertions.assertThat(ContainerStatus.parse("some-random-string_CREATING")).isEqualTo(ContainerStatus.CREATING);
  }

  @Test
  public void shouldParse2() {
    Assertions.assertThat(ContainerStatus.parse("some_random_string_DONE")).isEqualTo(ContainerStatus.DONE);
  }

}
