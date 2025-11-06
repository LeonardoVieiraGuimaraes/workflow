package com.workflow.flowable;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProcessController {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public ProcessController(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    @PostMapping("/process/start")
    public ResponseEntity<Map<String, Object>> startProcess(@RequestParam(name = "assignee", required = false) String assignee) {
        Map<String, Object> vars = new HashMap<>();
        if (assignee != null && !assignee.isBlank()) {
            vars.put("assignee", assignee);
        }
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("demoProcess", vars);
        Map<String, Object> body = new HashMap<>();
        body.put("processInstanceId", pi.getId());
        body.put("definitionId", pi.getProcessDefinitionId());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/process/{id}/tasks")
    public List<Map<String, Object>> listTasks(@PathVariable("id") String processInstanceId) {
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .orderByTaskCreateTime().desc()
                .list();

        return tasks.stream().map(t -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", t.getId());
            m.put("name", t.getName());
            m.put("assignee", t.getAssignee());
            m.put("createTime", t.getCreateTime() != null ? t.getCreateTime().toInstant() : Instant.now());
            return m;
        }).collect(Collectors.toList());
    }

    @PostMapping("/task/{taskId}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable String taskId, @RequestBody(required = false) Map<String, Object> variables) {
        if (variables == null) variables = Map.of();
        taskService.complete(taskId, variables);
        return ResponseEntity.noContent().build();
    }
}
