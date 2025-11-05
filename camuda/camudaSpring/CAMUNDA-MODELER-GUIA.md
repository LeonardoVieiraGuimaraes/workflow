# 🎨 Guia Completo: Camunda Modeler + Spring Boot

## 📥 **O que é o Camunda Modeler?**

O **Camunda Modeler** é um aplicativo **desktop separado** usado para:
- ✏️ Desenhar processos BPMN visualmente
- 📋 Criar formulários com arrastar e soltar
- 🔧 Configurar regras DMN (Decision Model and Notation)
- 💾 Exportar arquivos `.bpmn` e `.form` para usar no Spring Boot

**⚠️ IMPORTANTE:** O Modeler **NÃO** roda dentro do Spring Boot. Ele é uma ferramenta externa!

---

## 📥 **Como Instalar o Camunda Modeler**

### Passo 1: Download
1. Acesse: https://camunda.com/download/modeler/
2. Escolha a versão para **Windows**
3. Baixe o instalador (aprox. 150MB)

### Passo 2: Instalação
1. Execute o arquivo `.exe` baixado
2. Siga o assistente de instalação
3. Inicie o **Camunda Modeler**

---

## 🔄 **Fluxo de Trabalho: Modeler → Spring Boot**

```
1. DESENHAR no Modeler (Desktop)
   └─> Criar processo BPMN visualmente
   └─> Criar formulários com drag-and-drop
   └─> Salvar arquivos .bpmn e .form

2. COPIAR para o Spring Boot
   └─> Copiar .bpmn para: src/main/resources/bpmn/
   └─> Copiar .form para: src/main/resources/forms/

3. EXECUTAR no Spring Boot
   └─> mvn spring-boot:run
   └─> Camunda faz deploy automático
   └─> Processos ficam disponíveis
```

---

## 🎯 **Como Usar o Camunda Modeler**

### 1️⃣ **Criar um Novo Processo BPMN**

1. Abra o **Camunda Modeler**
2. Clique em **File > New File > BPMN Diagram**
3. Arraste elementos da paleta:
   - 🟢 **Start Event** - Evento de início
   - 📝 **User Task** - Tarefa humana
   - ⚙️ **Service Task** - Tarefa automática
   - 💎 **Gateway** - Decisões (if/else)
   - 🔴 **End Event** - Fim do processo

4. Conecte os elementos clicando e arrastando
5. Configure cada elemento (clique nele):
   - **ID**: Identificador único
   - **Name**: Nome em português
   - **Implementation**: Delegate, Script, etc.

6. Salve: **Ctrl+S**

### 2️⃣ **Criar Formulários Visual**

1. No Modeler, clique em **File > New File > Form**
2. Arraste campos da paleta:
   - ✏️ **Text Field** - Campo de texto
   - 🔢 **Number** - Número
   - 📋 **Textarea** - Texto longo
   - ✅ **Checkbox** - Sim/Não
   - 🔘 **Radio** - Escolha única
   - 📅 **Date** - Data

3. Configure cada campo:
   - **Field Label**: Nome em português (ex: "Solicitante")
   - **Key**: Nome da variável (ex: "solicitante")
   - **Type**: Tipo de dado
   - **Validation**: Marque "Required" se obrigatório

4. Salve como `.form`

### 3️⃣ **Vincular Formulário ao Processo**

1. No Modeler, abra seu processo BPMN
2. Clique na **User Task**
3. No painel direito, aba **Forms**:
   - **Form Type**: Camunda Forms
   - **Form reference**: Nome do arquivo `.form` (sem extensão)
   - **Binding**: `latest`

4. Salve o BPMN

---

## 📂 **Estrutura de Arquivos no Projeto**

```
src/main/resources/
├── bpmn/
│   └── processo-aprovacao.bpmn   ← Criado no Modeler
├── forms/
│   ├── revisar-solicitacao.form  ← Criado no Modeler
│   └── aprovar-solicitacao.form  ← Criado no Modeler
└── application.yml
```

---

## ✅ **Passo a Passo Completo**

### 1. **Criar Processo no Modeler**

```
1. Abrir Camunda Modeler
2. File > New File > BPMN Diagram
3. Arrastar:
   - Start Event
   - User Task "Preencher Solicitação"
   - Service Task "Validar Dados"
   - User Task "Aprovar Solicitação"
   - End Event
4. Conectar elementos
5. Salvar como: processo-aprovacao.bpmn
```

### 2. **Criar Formulário no Modeler**

```
1. File > New File > Form
2. Arrastar campos:
   - Text Field: "Solicitante" (key: solicitante)
   - Textarea: "Descrição" (key: descricao)
   - Number: "Valor" (key: valor, decimalDigits: 2)
3. Salvar como: formulario-solicitacao.form
```

### 3. **Vincular Formulário à User Task**

```
1. No BPMN, clicar na User Task
2. Aba Forms:
   - Form Type: Camunda Forms
   - Form reference: formulario-solicitacao
   - Binding: latest
3. Salvar BPMN
```

### 4. **Copiar para o Spring Boot**

```powershell
# Copiar BPMN
Copy-Item "processo-aprovacao.bpmn" "src\main\resources\bpmn\"

# Copiar Formulário
Copy-Item "formulario-solicitacao.form" "src\main\resources\forms\"
```

### 5. **Executar**

```powershell
mvn spring-boot:run
```

### 6. **Testar**

```
1. Abrir: http://localhost:8080/camunda/app/tasklist
2. Login: admin / admin
3. Start Process
4. Ver o formulário que você criou! 🎉
```

---

## 🌐 **Interface Web em Português BR**

A interface web do Camunda (Tasklist, Cockpit, Admin) já está configurada em **Português BR** neste projeto:

✅ Arquivos de localização criados em:
- `src/main/resources/META-INF/resources/webjars/camunda/app/tasklist/locales/pt_BR.json`
- Configuração em `config.js` de cada aplicação

---

## 📚 **Recursos do Camunda Modeler**

### Elementos BPMN Disponíveis:

#### **Eventos:**
- 🟢 Start Event - Início
- 🔴 End Event - Fim
- ⏰ Timer Event - Temporizador
- 📧 Message Event - Mensagem
- ⚠️ Error Event - Erro

#### **Tarefas:**
- 📝 User Task - Tarefa humana
- ⚙️ Service Task - Tarefa automática (Java)
- 📧 Send Task - Enviar mensagem
- 📥 Receive Task - Receber mensagem
- 📋 Script Task - Executar script

#### **Gateways (Decisões):**
- 💎 Exclusive Gateway - if/else
- ➕ Parallel Gateway - AND paralelo
- 🔀 Inclusive Gateway - OR inclusivo
- ⚡ Event Gateway - Baseado em eventos

#### **Subprocessos:**
- 📦 Subprocess - Processo aninhado
- 🔁 Call Activity - Chamar outro processo

---

## 🎓 **Tutoriais Recomendados**

1. **BPMN Básico:** https://camunda.com/bpmn/
2. **Camunda Forms:** https://docs.camunda.io/docs/components/modeler/forms/
3. **BPMN Best Practices:** https://camunda.com/best-practices/

---

## 💡 **Dicas Importantes**

### ✅ Boas Práticas:

1. **IDs únicos:** Use IDs descritivos (ex: `aprovar_solicitacao`)
2. **Nomes em português:** Configure o atributo `name` em PT-BR
3. **Versionamento:** Mantenha os arquivos BPMN no Git
4. **Formulários separados:** Um `.form` por User Task

### ⚠️ Cuidados:

- **NÃO** edite `.bpmn` manualmente (use o Modeler)
- **SEMPRE** teste o processo após alterações
- **CUIDADO** com IDs duplicados
- **BACKUP** dos arquivos antes de grandes mudanças

---

## 🚀 **Exemplo Prático Completo**

### Cenário: Processo de Férias

#### 1. No Camunda Modeler:

```
Start → [Solicitar Férias] → [Validar Saldo] → [Aprovar?] → End
                                                    ↓
                                               [Rejeitar] → End
```

#### 2. Formulário "Solicitar Férias":
- Data Início (Date)
- Data Fim (Date)
- Observações (Textarea)

#### 3. Salvar arquivos:
- `processo-ferias.bpmn`
- `solicitar-ferias.form`

#### 4. Copiar para Spring Boot:
```
src/main/resources/bpmn/processo-ferias.bpmn
src/main/resources/forms/solicitar-ferias.form
```

#### 5. Executar e testar! ✅

---

## 📞 **Suporte**

- **Documentação:** https://docs.camunda.io/
- **Fórum:** https://forum.camunda.io/
- **GitHub:** https://github.com/camunda/camunda-modeler

---

**Resumo:** O Camunda Modeler é uma ferramenta **externa** para **CRIAR** processos e formulários visualmente. Depois você **COPIA** os arquivos para o Spring Boot que **EXECUTA** os processos! 🎉
