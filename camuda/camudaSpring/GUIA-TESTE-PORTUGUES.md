# 🎯 Guia Rápido - Testando a Aplicação em Português BR

## ✅ Aplicação Iniciada com Sucesso!

A aplicação Camunda está rodando e configurada para **Português Brasileiro**.

---

## 🌐 Acessando as Interfaces Web

### 1. **Tasklist** (Lista de Tarefas)
```
http://localhost:8080/camunda/app/tasklist
```
- **O que faz**: Mostra e permite executar tarefas de processos
- **Login**: `admin` / `admin`
- **Deve aparecer em português**: "Lista de Tarefas", "Todas as tarefas", etc.

### 2. **Cockpit** (Painel de Controle)
```
http://localhost:8080/camunda/app/cockpit
```
- **O que faz**: Monitora processos em execução, visualiza diagramas BPMN
- **Login**: `admin` / `admin`
- **Deve aparecer em português**: "Painel de Controle", "Processos", "Decisões"

### 3. **Admin** (Administração)
```
http://localhost:8080/camunda/app/admin
```
- **O que faz**: Gerencia usuários, grupos, autorizações
- **Login**: `admin` / `admin`
- **Deve aparecer em português**: "Administração", "Usuários", "Grupos"

---

## 🧪 Como Testar

### **Teste 1: Criar Instância via API**

1. Abra o arquivo `TESTE-FINAL.http` no VS Code
2. Clique em **"Send Request"** na primeira requisição
3. Você receberá uma resposta em português:
   ```json
   {
     "idInstanciaProcesso": "xxxxx",
     "mensagem": "Processo de aprovação iniciado com sucesso"
   }
   ```

### **Teste 2: Ver Tarefa no Tasklist**

1. Após criar a instância, acesse: http://localhost:8080/camunda/app/tasklist
2. **IMPORTANTE**: Limpe o cache do navegador (`Ctrl + Shift + Delete`)
3. Faça login com `admin` / `admin`
4. Você verá:
   - ✅ Interface em português
   - ✅ Tarefa "Revisar Solicitação" na lista
5. Clique na tarefa para ver o **formulário Camunda**:
   - Campos em português: "Solicitante", "Descrição", "Valor"
   - Opções: "Aprovar" / "Rejeitar"

### **Teste 3: Ver Processo no Cockpit**

1. Acesse: http://localhost:8080/camunda/app/cockpit
2. Login: `admin` / `admin`
3. Clique em "Processos" → "processo-aprovacao"
4. Você verá:
   - ✅ Diagrama BPMN visual
   - ✅ Estatísticas do processo
   - ✅ Instâncias em execução

---

## 🚨 Se a Interface NÃO Estiver em Português

### **Causa**: Cache do navegador está mantendo a versão antiga em inglês

### **Solução**:

#### **Chrome / Edge**:
1. Pressione `Ctrl + Shift + Delete`
2. Marque **"Cached images and files"** (Imagens e arquivos em cache)
3. Clique em **"Clear data"**
4. Pressione `Ctrl + F5` para recarregar a página

#### **Firefox**:
1. Pressione `Ctrl + Shift + Delete`
2. Marque **"Cache"**
3. Clique em **"Clear Now"**
4. Pressione `Ctrl + F5` para recarregar a página

#### **Alternativa**: Use **Modo Anônimo/Privado**
- Chrome/Edge: `Ctrl + Shift + N`
- Firefox: `Ctrl + Shift + P`

---

## 📊 O Que Deve Estar em Português

### ✅ **API REST**
- Mensagens de resposta em português
- DTO `RespostaProcesso` com campos em português

### ✅ **Formulários Camunda**
- Labels dos campos em português
- Botões e opções em português
- Placeholders em português

### ✅ **Interface Tasklist**
- Menus: "Todas as tarefas", "Filtros", "Processo"
- Título: "Lista de Tarefas"
- Botões: "Reivindicar", "Completar", etc.

### ✅ **Interface Cockpit**
- Menus: "Processos", "Decisões", "Batches"
- Título: "Painel de Controle"
- Estatísticas em português

### ✅ **Interface Admin**
- Menus: "Usuários", "Grupos", "Autorizações"
- Título: "Administração"

---

## 🔧 Troubleshooting

### **Problema**: "Aplicação não carrega"
- ✅ **Solução**: Verifique se a aplicação está rodando (deve estar no terminal)
- ✅ **Confirmar**: Acesse http://localhost:8080 (deve redirecionar)

### **Problema**: "Interface em inglês"
- ✅ **Solução**: Limpe o cache do navegador completamente
- ✅ **Alternativa**: Use modo anônimo/privado

### **Problema**: "Erro de autenticação"
- ✅ **Solução**: Use `admin` / `admin` (exatamente assim, minúsculas)

### **Problema**: "Formulário não aparece"
- ✅ **Solução**: Certifique-se de clicar na tarefa na lista (lado esquerdo)
- ✅ **Verificar**: O formulário aparece no lado direito após clicar

---

## 📝 Logs para Confirmar

No terminal, você deve ter visto:
```
✅ Locale do Camunda configurado para: pt_BR
STARTER-SB010 Creating initial Admin User: AdminUserProperty[id=admin, firstName=Administrador, lastName=Sistema, ...]
STARTER-SB021 Auto-Deploying resources: [file [...\processo-aprovacao.bpmn]]
Tomcat started on port(s): 8080 (http)
Started CamundaApplication in 4.528 seconds
```

---

## 🎉 Próximo Passo

Depois de testar a interface web, você pode:

1. **Baixar o Camunda Modeler** (desktop app):
   - https://camunda.com/download/modeler/
   - Ferramenta visual para criar/editar BPMN e formulários

2. **Editar o processo visualmente**:
   - Abrir `processo-aprovacao.bpmn` no Modeler
   - Arrastar e soltar elementos
   - Salvar e reiniciar a aplicação

3. **Criar novos processos**:
   - Criar novo `.bpmn` no Modeler
   - Salvar em `src/main/resources/bpmn/`
   - Reiniciar a aplicação (auto-deploy)

---

## ✅ Checklist Final

- [ ] Aplicação rodando (terminal ativo)
- [ ] Cache do navegador limpo
- [ ] Login efetuado: `admin` / `admin`
- [ ] Interface Tasklist em português
- [ ] Instância de processo criada via API
- [ ] Tarefa aparecendo na lista
- [ ] Formulário Camunda visível e em português
- [ ] Cockpit mostrando diagrama BPMN
- [ ] Admin acessível

---

**Qualquer dúvida, é só perguntar!** 🚀
