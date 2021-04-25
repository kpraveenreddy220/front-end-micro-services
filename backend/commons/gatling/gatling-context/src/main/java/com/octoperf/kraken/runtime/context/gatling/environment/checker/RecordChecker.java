package com.octoperf.kraken.runtime.context.gatling.environment.checker;

import com.octoperf.kraken.runtime.context.api.environment.EnvironmentChecker;
import com.octoperf.kraken.runtime.entity.task.TaskType;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.octoperf.kraken.tools.environment.KrakenEnvironmentKeys.*;

@Component
final class RecordChecker implements EnvironmentChecker {

  @Override
  public void accept(final Map<String, String> environment) {
    requireEnv(
        environment,
        KRAKEN_GATLING_SIMULATION_CLASS_NAME,
        KRAKEN_GATLING_SIMULATION_PACKAGE_NAME,
        KRAKEN_GATLING_HAR_PATH_REMOTE
    );
  }

  @Override
  public boolean test(final TaskType taskType) {
    return test(taskType, TaskType.GATLING_RECORD);
  }
}
