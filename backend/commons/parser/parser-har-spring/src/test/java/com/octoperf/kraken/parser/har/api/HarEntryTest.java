package com.octoperf.kraken.parser.har.api;

import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HarEntryTest {

  public static final HarEntry HAR_ENTRY = HarEntry.builder()
      .index(42)
      .timestamp(1337L)
      .name("name")
      .build();

  @Test
  public void shouldPassTestUtils() {
    TestUtils.shouldPassAll(HAR_ENTRY);
  }

  @Test
  public void shouldWither() {
    assertThat(HAR_ENTRY.withName("other").getName()).isEqualTo("other");
  }

}
