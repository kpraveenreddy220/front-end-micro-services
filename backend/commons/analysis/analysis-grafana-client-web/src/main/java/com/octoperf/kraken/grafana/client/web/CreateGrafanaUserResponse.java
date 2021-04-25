package com.octoperf.kraken.grafana.client.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.octoperf.kraken.tools.obfuscation.ExcludeFromObfuscation;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ExcludeFromObfuscation
class CreateGrafanaUserResponse {
  Long id;
  String message;

  @JsonCreator
  CreateGrafanaUserResponse(
      @NonNull @JsonProperty("id") final Long id,
      @NonNull @JsonProperty("message") final String message
  ) {
    super();
    this.id = id;
    this.message = message;
  }
}