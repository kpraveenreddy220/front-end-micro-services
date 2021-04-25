package com.octoperf.kraken.analysis.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ObjectMapper.class)
public class ResultJacksonTest {

  @Autowired
  private ObjectMapper mapper;

  @Test
  public void shouldDeSerialize() throws IOException {
    final var object = ResultTest.RESULT;
    final String json = mapper.writeValueAsString(object);
    Assertions.assertThat(mapper.readValue(json, Result.class)).isEqualTo(object);
  }
}
