package com.octoperf.kraken.security.authentication.api;

public interface ExchangeFilterFactory {

  ExchangeFilter create(final String userId);

  AuthenticationMode getMode();

}
