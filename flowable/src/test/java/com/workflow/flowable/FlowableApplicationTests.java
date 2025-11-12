package com.workflow.flowable;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlowableApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	@Test
	void startAndCompleteDemoProcess() {
		Map<String, Object> vars = Map.of("assignee", "kermit");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("demoProcess", vars);
		assertNotNull(pi.getId());

		List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
		assertEquals(1, tasks.size(), "Deveria existir 1 tarefa aguardando");

		taskService.complete(tasks.get(0).getId());

		// Processo deve terminar após completar a única userTask
		assertNull(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult());
	}

}
