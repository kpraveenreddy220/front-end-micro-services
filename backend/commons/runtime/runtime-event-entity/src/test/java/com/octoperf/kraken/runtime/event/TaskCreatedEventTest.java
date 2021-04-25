package com.octoperf.kraken.runtime.event;

import com.google.common.testing.NullPointerTester;
import com.octoperf.kraken.runtime.entity.task.Task;
import com.octoperf.kraken.runtime.entity.task.TaskTest;
import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.Test;

import static com.google.common.testing.NullPointerTester.Visibility.PACKAGE;

public class TaskCreatedEventTest {
  public static final TaskCreatedEvent TASK_CREATED_EVENT = TaskCreatedEvent.builder()
      .task(TaskTest.TASK)
      .build();


  @Test
  public void shouldPassTestUtils() {
    TestUtils.shouldPassEquals(TASK_CREATED_EVENT.getClass());
    TestUtils.shouldPassToString(TASK_CREATED_EVENT);
    new NullPointerTester()
        .setDefault(Task.class, TaskTest.TASK)
        .testConstructors(TASK_CREATED_EVENT.getClass(), PACKAGE);
  }

}
