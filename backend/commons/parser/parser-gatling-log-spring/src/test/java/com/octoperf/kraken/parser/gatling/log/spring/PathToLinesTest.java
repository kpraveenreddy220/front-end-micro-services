package com.octoperf.kraken.parser.gatling.log.spring;

import com.octoperf.kraken.tests.utils.TestUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PathToLinesTest {

  PathToLines pathToLines;
  int count;

  @BeforeEach
  public void before() {
    pathToLines = new PathToLines();
    count = 0;
  }

  @Test
  public void shouldPassTestUtils() {
    TestUtils.shouldPassNPE(PathToLines.class);
  }

  @Test
  public void shouldReadLines() throws IOException {
    final var logs = Paths.get("testDir/gatling.log");
    final var logsCopy = Paths.get("testDir/gatling_copy.log");
    final var lines = pathToLines.apply(logsCopy);
    Mono.delay(Duration.ofSeconds(2)).subscribe(aLong -> {
      try {
        Files.copy(logs, logsCopy);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    lines.take(Duration.ofSeconds(4)).subscribe(s -> count++);
    Files.delete(logsCopy);
    assertThat(count).isEqualTo(2858);
  }
}
