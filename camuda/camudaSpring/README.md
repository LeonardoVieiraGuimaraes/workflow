# 🚀 Aplicação Camunda BPMN - Processo de Aprovação

Esta é uma aplicação completa de exemplo usando **Camunda Platform 7** com **Spring Boot** para gerenciamento de workflows BPMN.

## 📋 Descrição

A aplicação implementa um **Processo de Aprovação** completo com as seguintes etapas:

1. **Preencher Solicitação** - Usuário preenche os dados da solicitação
2. **Validar Dados** - Sistema valida automaticamente os dados
3. **Decisão de Validação** - Se dados inválidos, retorna para correção
4. **Aprovar Solicitação** - Gestor aprova ou rejeita a solicitação
5. **Processar Aprovação** - Sistema processa a aprovação
6. **Notificar Rejeição** - Sistema notifica em caso de rejeição

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.1.5**
- **Camunda Platform 7.20.0**
- **H2 Database** (em memória para desenvolvimento)
- **Maven** (gerenciamento de dependências)
- **Lombok** (redução de boilerplate)

## 📁 Estrutura do Projeto

```
camunda-workflow-app/
├── src/
│   ├── main/
│   │   ├── java/com/exemplo/camunda/
│   │   │   ├── CamundaApplication.java          # Classe principal
│   │   │   ├── controller/
│   │   │   │   └── ProcessoController.java      # API REST
│   │   │   └── delegate/
│   │   │       ├── ValidarDadosDelegate.java    # Validação de dados
│   │   │       ├── ProcessarAprovacaoDelegate.java
│   │   │       └── NotificarRejeicaoDelegate.java
│   │   └── resources/
│   │       ├── application.yml                   # Configurações
│   │       └── bpmn/
│   │           └── processo-aprovacao.bpmn      # Diagrama BPMN
│   └── test/
└── pom.xml
```

## 🚀 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+

### Passos para executar:

1. **Clone ou navegue até o diretório do projeto**

2. **Compile o projeto:**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

4. **A aplicação estará disponível em:**
   - **Aplicação:** http://localhost:8080
   - **Camunda Cockpit:** http://localhost:8080/camunda/app/cockpit
   - **Camunda Tasklist:** http://localhost:8080/camunda/app/tasklist
   - **Camunda Admin:** http://localhost:8080/camunda/app/admin
   - **H2 Console:** http://localhost:8080/h2-console

### Credenciais padrão:
- **Usuário:** admin
- **Senha:** admin

## 📡 API REST

### Iniciar Processo de Aprovação

**Endpoint:** `POST /api/processo/iniciar`

**Body (JSON):**
```json
{
  "solicitante": "João Silva",
  "descricao": "Compra de equipamentos",
  "valor": 5000.00
}
```

**Resposta:**
```json
{
  "processInstanceId": "abc123-def456",
  "processDefinitionId": "processo-aprovacao:1:789",
  "businessKey": null,
  "mensagem": "Processo iniciado com sucesso!"
}
```

### Exemplo com cURL:

```bash
curl -X POST http://localhost:8080/api/processo/iniciar \
  -H "Content-Type: application/json" \
  -d '{
    "solicitante": "Maria Santos",
    "descricao": "Contratação de serviço",
    "valor": 3500.00
  }'
```

## 🎯 Usando o Camunda Tasklist

1. Acesse: http://localhost:8080/camunda/app/tasklist
2. Faça login com **admin/admin**
3. Você verá as tarefas disponíveis
4. Clique em uma tarefa para visualizar e completar
5. Preencha as variáveis necessárias:
   - Para aprovar: defina `aprovado = true`
   - Para rejeitar: defina `aprovado = false` e `motivoRejeicao = "motivo"`

## 🔍 Monitorando Processos

### Camunda Cockpit

Acesse http://localhost:8080/camunda/app/cockpit para:
- Visualizar instâncias de processos em execução
- Ver histórico de processos
- Analisar estatísticas
- Debugar processos

### H2 Console

Acesse http://localhost:8080/h2-console para visualizar o banco de dados:
- **JDBC URL:** `jdbc:h2:mem:camunda`
- **User:** `sa`
- **Password:** `sa`

## 📊 Variáveis do Processo

O processo utiliza as seguintes variáveis:

| Variável | Tipo | Descrição |
|----------|------|-----------|
| `solicitante` | String | Nome do solicitante |
| `descricao` | String | Descrição da solicitação |
| `valor` | Double | Valor da solicitação |
| `dadosValidos` | Boolean | Resultado da validação |
| `aprovado` | Boolean | Decisão de aprovação |
| `aprovador` | String | Nome do aprovador |
| `motivoRejeicao` | String | Motivo da rejeição |
| `processoStatus` | String | Status final (APROVADO/REJEITADO) |

## 🔧 Configuração

### Banco de Dados

Por padrão, usa H2 em memória. Para usar PostgreSQL em produção, ative o perfil:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=production
```

E configure no `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/camunda
    username: seu-usuario
    password: sua-senha
```

## 📝 Personalizando o Processo

### Modificar o Diagrama BPMN

1. Baixe o **Camunda Modeler**: https://camunda.com/download/modeler/
2. Abra o arquivo `src/main/resources/bpmn/processo-aprovacao.bpmn`
3. Edite o diagrama visualmente
4. Salve e reinicie a aplicação

### Adicionar Novos Delegates

1. Crie uma classe que implemente `JavaDelegate`
2. Anote com `@Component("nomeDoDelegado")`
3. Implemente o método `execute(DelegateExecution execution)`
4. Referencie no BPMN usando `${nomeDoDelegado}`

## 🧪 Testes

Execute os testes com:

```bash
mvn test
```

## 📚 Recursos Adicionais

- [Documentação Camunda](https://docs.camunda.org/)
- [Camunda BPMN Tutorial](https://camunda.com/bpmn/)
- [Spring Boot com Camunda](https://docs.camunda.org/get-started/spring-boot/)
- [BPMN 2.0 Specification](https://www.omg.org/spec/BPMN/2.0/)

## 🤝 Contribuindo

Sinta-se à vontade para contribuir com melhorias:

1. Fork o projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

## 📄 Licença

Este projeto é um exemplo educacional e pode ser usado livremente.

## 👨‍💻 Autor

Desenvolvido como exemplo de aplicação Camunda BPMN com Spring Boot.

---

**Dúvidas?** Consulte a documentação oficial do Camunda ou abra uma issue!
