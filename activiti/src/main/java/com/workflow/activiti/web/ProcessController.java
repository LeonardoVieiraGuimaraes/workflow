package com.workflow.activiti.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public ProcessController(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    // Inicia o processo simples com variáveis opcionais
    @PostMapping("/process/start")
    public ResponseEntity<Map<String, Object>> startProcess(@RequestBody(required = false) Map<String, Object> variables) {
        var processInstance = runtimeService.startProcessInstanceByKey("simpleApproval", variables);
        return ResponseEntity.ok(Map.of(
            "processInstanceId", processInstance.getId(),
            "processDefinitionId", processInstance.getProcessDefinitionId(),
            "status", "started"
        ));
    }

    // Lista tarefas, opcionalmente filtrando por candidato usuário ou grupo
    @GetMapping("/tasks")
    public ResponseEntity<List<Map<String, String>>> listTasks(
            @RequestParam(required = false) String assignee,
            @RequestParam(required = false, name = "candidateUser") String candidateUser,
            @RequestParam(required = false, name = "candidateGroup") String candidateGroup) {

        var query = taskService.createTaskQuery();
        if (assignee != null && !assignee.isBlank()) {
            query = query.taskAssignee(assignee);
        }
        if (candidateUser != null && !candidateUser.isBlank()) {
            query = query.taskCandidateUser(candidateUser);
        }
        if (candidateGroup != null && !candidateGroup.isBlank()) {
            query = query.taskCandidateGroup(candidateGroup);
        }
        List<Task> tasks = query.list();

        var result = tasks.stream().map(t -> Map.<String, String>of(
            "id", t.getId(),
            "name", t.getName(),
            "assignee", t.getAssignee(),
            "processInstanceId", t.getProcessInstanceId()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // Completa uma tarefa
    @PostMapping("/tasks/{taskId}/complete")
    public ResponseEntity<Map<String, Object>> completeTask(@PathVariable String taskId,
                                                            @RequestBody(required = false) Map<String, Object> variables) {
        taskService.complete(taskId, variables);
        return ResponseEntity.ok(Map.of(
            "taskId", taskId,
            "status", "completed"
        ));
    }
}
