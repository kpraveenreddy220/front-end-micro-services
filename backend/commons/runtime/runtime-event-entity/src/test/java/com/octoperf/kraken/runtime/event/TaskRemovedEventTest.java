package com.octoperf.kraken.runtime.event;

import com.google.common.testing.NullPointerTester;
import com.octoperf.kraken.runtime.entity.task.Task;
import com.octoperf.kraken.runtime.entity.task.TaskTest;
import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.Test;

import static com.google.common.testing.NullPointerTester.Visibility.PACKAGE;

public class TaskRemovedEventTest {
  public static final TaskRemovedEvent TASK_REMOVED_EVENT = TaskRemovedEvent.builder()
      .task(TaskTest.TASK)
      .build();


  @Test
  public void shouldPassTestUtils() {
    TestUtils.shouldPassEquals(TASK_REMOVED_EVENT.getClass());
    TestUtils.shouldPassToString(TASK_REMOVED_EVENT);
    new NullPointerTester()
        .setDefault(Task.class, TaskTest.TASK)
        .testConstructors(TASK_REMOVED_EVENT.getClass(), PACKAGE);
  }

}
