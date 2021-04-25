package com.octoperf.kraken.config.runtime.server.spring;

import com.octoperf.kraken.config.runtime.server.api.RuntimeServerProperties;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@Builder(toBuilder = true)
@ConstructorBinding
@ConfigurationProperties("kraken.runtime.server")
class SpringServerProperties implements RuntimeServerProperties {
  @NonNull
  String configPath;
}