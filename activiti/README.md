# Exemplo simples de workflow (Flowable + Spring Boot)

Este projeto demonstra um fluxo BPMN simples (início -> tarefa de usuário -> fim) usando Flowable com Spring Boot 3.

> Observação: Flowable é o motor BPM compatível com Spring Boot 3. Ele é 100% open source e amplamente compatível com APIs do Activiti.

## Como rodar

Requisitos:
- Java 21
- Maven Wrapper incluído no repo

Execute os testes e suba a aplicação:

```powershell
# Windows PowerShell
./mvnw.cmd clean test
./mvnw.cmd spring-boot:run
```

A aplicação sobe em `http://localhost:8080` e o console do H2 fica em `http://localhost:8080/h2` (JDBC URL: `jdbc:h2:mem:flowable-db`).

## Endpoints

- POST `/process/start` – Inicia o processo `simpleApproval`.
  - Corpo opcional JSON com variáveis (ex: `{ "solicitante": "alice" }`).
- GET `/tasks` – Lista tarefas.
  - Parâmetros opcionais: `assignee`, `candidateUser`, `candidateGroup`.
- POST `/tasks/{id}/complete` – Completa a tarefa por id.
  - Corpo opcional JSON com variáveis (ex: `{ "aprovado": true }`).

### Teste rápido com PowerShell

```powershell
# Iniciar processo
$start = Invoke-RestMethod -Method POST -Uri http://localhost:8080/process/start -ContentType 'application/json' -Body '{"solicitante":"alice"}'
$start

# Listar tarefas do grupo managers
Invoke-RestMethod -Method GET -Uri 'http://localhost:8080/tasks?candidateGroup=managers'

# Completar a primeira tarefa retornada
$tasks = Invoke-RestMethod -Method GET -Uri 'http://localhost:8080/tasks'
$taskId = $tasks[0].id
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/tasks/$taskId/complete" -ContentType 'application/json' -Body '{"aprovado":true}'
```

## Onde está o processo BPMN?

O arquivo BPMN fica em `src/main/resources/processes/simple-approval.bpmn20.xml`. O Flowable faz o deploy automaticamente na inicialização.

## Estrutura principal

- `src/main/java/.../web/ProcessController.java` – Endpoints REST para iniciar e completar tarefas.
- `src/main/resources/processes/simple-approval.bpmn20.xml` – Definição do processo BPMN.
- `src/test/java/.../FlowableSimpleProcessTest.java` – Teste de integração simples.

## Próximos passos

- Adicionar atribuição dinâmica de usuário (assignee) e gateways de aprovação.
- Persistir em banco relacional externo (PostgreSQL/MySQL).
- Habilitar o Flowable UI Apps para explorar processos e tarefas.
