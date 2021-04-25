package com.octoperf.kraken.config.kraken;

import com.octoperf.kraken.config.api.ApplicationProperties;
import lombok.Builder;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import static com.google.common.base.Strings.nullToEmpty;

@Value
@Builder(toBuilder = true)
@ConstructorBinding
@ConfigurationProperties("kraken")
class SpringApplicationProperties implements ApplicationProperties {
  String data;
  String version;

  SpringApplicationProperties(final String data, final String version) {
    super();
    this.data = nullToEmpty(data);
    this.version = nullToEmpty(version);
  }
}
