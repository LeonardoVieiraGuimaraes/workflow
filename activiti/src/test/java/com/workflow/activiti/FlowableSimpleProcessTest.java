package com.workflow.activiti;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlowableSimpleProcessTest {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Test
    void startAndCompleteSimpleApproval() {
        var instance = runtimeService.startProcessInstanceByKey("simpleApproval", Map.of("solicitante", "alice"));
        assertThat(instance).isNotNull();

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();
        assertThat(tasks).hasSize(1);
        Task task = tasks.get(0);
        assertThat(task.getName()).isEqualTo("Approve request");

        taskService.complete(task.getId(), Map.of("aprovado", true));

        long remaining = taskService.createTaskQuery().processInstanceId(instance.getId()).count();
        assertThat(remaining).isZero();
    }
}
