package com.octoperf.kraken.security.authentication.noop;

import com.octoperf.kraken.security.authentication.api.AuthenticationMode;
import com.octoperf.kraken.security.authentication.api.UserProviderFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.octoperf.kraken.security.authentication.api.AuthenticationMode.NOOP;

@Slf4j
@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class NoopUserProviderFactory implements UserProviderFactory {

  @Override
  public NoopUserProvider create(final String userId) {
    return new NoopUserProvider();
  }

  @Override
  public AuthenticationMode getMode() {
    return NOOP;
  }
}
