package com.octoperf.kraken.config.kubernetes.api;

import com.octoperf.kraken.config.api.KrakenProperties;

import java.util.Optional;

public interface KubernetesProperties extends KrakenProperties {

  String getNamespace();

  boolean isDebug();

  boolean isPatchHosts();

  String getPretty();

  KubernetesClientBuilderType getBuilderType();

  Optional<String> getConfigPath();
}
