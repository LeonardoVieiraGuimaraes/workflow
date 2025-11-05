# 🎨 Como Criar Formulários com Arrastar e Soltar no Camunda

## 📥 **Opção 1: Camunda Modeler Desktop (Recomendado)**

### Passo 1: Baixar o Camunda Modeler
1. Acesse: https://camunda.com/download/modeler/
2. Baixe a versão para Windows
3. Instale o aplicativo

### Passo 2: Abrir seu Processo
1. Abra o Camunda Modeler
2. Clique em `File > Open File`
3. Navegue até: `d:\GitHub\ProjetosPessoais\workflow\Camunda\src\main\resources\bpmn\processo-aprovacao.bpmn`

### Passo 3: Editar Formulários Visualmente
1. Clique em uma **User Task** (ex: "Aprovar Solicitação")
2. No painel direito, clique na aba **Forms**
3. Clique em **Create new Camunda Form**
4. Arraste elementos da paleta:
   - 📝 **Text Field** - Campo de texto
   - 🔢 **Number** - Campo numérico
   - 📋 **Textarea** - Área de texto
   - ✅ **Checkbox** - Caixa de seleção
   - 🔘 **Radio** - Botões de opção
   - 📅 **Date** - Data
   - ⏰ **Time** - Hora
   - 📎 **File** - Upload de arquivo

### Passo 4: Configurar Campos em Português
Para cada campo arrastado, configure:
- **Label**: Nome em português (ex: "Nome do Solicitante")
- **Key**: Nome da variável (ex: "solicitante")
- **Description**: Texto de ajuda em português
- **Validation**: Marque "Required" para campos obrigatórios

### Passo 5: Salvar
1. Salve o formulário: `Ctrl+S`
2. Salve o BPMN: `Ctrl+S`

---

## 🌐 **Opção 2: Camunda Forms Web (Navegador)**

### Já Configurado no Projeto!
Os formulários `.form` estão em:
```
src/main/resources/forms/
  ├── revisar-solicitacao.form
  └── aprovar-solicitacao.form
```

### Como Editar:
1. Abra o Camunda Modeler Desktop
2. Clique em `File > Open File`
3. Selecione o arquivo `.form`
4. Use a interface visual para:
   - Arrastar novos campos
   - Reordenar elementos
   - Configurar validações

---

## 📋 **Campos Disponíveis para Arrastar**

### Campos de Entrada:
- ✏️ **Text Field** - Texto simples
- 🔢 **Number** - Números (inteiros ou decimais)
- 📧 **Email** - E-mail com validação
- 🔗 **URL** - Link com validação
- 📞 **Phone** - Telefone
- 📋 **Textarea** - Texto longo (múltiplas linhas)

### Campos de Seleção:
- ✅ **Checkbox** - Sim/Não
- 🔘 **Radio** - Escolha única
- 📦 **Select** - Lista suspensa
- ☑️ **Checklist** - Múltipla escolha

### Campos Especiais:
- 📅 **Date** - Seletor de data
- ⏰ **Datetime** - Data e hora
- 📎 **File Upload** - Upload de arquivos
- 🔐 **Password** - Senha (oculta)

### Elementos Visuais:
- 📝 **Text** - Texto estático (Markdown)
- ➖ **Separator** - Linha divisória
- 📦 **Group** - Agrupar campos

---

## 🎯 **Exemplo Prático: Criar Formulário de Aprovação**

### 1. Arraste os elementos nesta ordem:
```
1. Text - "## ✅ Aprovar Solicitação"
2. Text Field (readonly) - Label: "Solicitante", Key: "solicitante"
3. Textarea (readonly) - Label: "Descrição", Key: "descricao"  
4. Number (readonly) - Label: "Valor (R$)", Key: "valor"
5. Separator
6. Radio - Label: "Decisão", Key: "aprovado"
   - Opção 1: Label "✅ Aprovar", Value: true
   - Opção 2: Label "❌ Rejeitar", Value: false
7. Textarea - Label: "Comentários", Key: "comentarios"
```

### 2. Configurar Validações:
- Marque "Required" no campo **Radio** (Decisão)
- Adicione "Description" nos campos para ajudar o usuário

### 3. Salvar:
- Salve como: `aprovar-solicitacao.form`
- Coloque em: `src/main/resources/forms/`

---

## 🚀 **Como Testar os Formulários**

### 1. Reinicie a aplicação:
```bash
mvn spring-boot:run
```

### 2. Crie um processo via API:
```bash
curl -X POST http://localhost:8080/api/processo/iniciar \
  -H "Content-Type: application/json" \
  -d '{"solicitante":"João Silva","descricao":"Compra de equipamentos","valor":3500.00}'
```

### 3. Acesse o Tasklist:
- URL: http://localhost:8080/camunda/app/tasklist
- Login: admin / admin
- Clique na tarefa pendente
- **Veja o formulário em português com os campos que você arrastou!** 🎉

---

## 📱 **Formulários Responsivos**

Os formulários Camunda Forms são **automaticamente responsivos**:
- ✅ Funcionam em desktop
- ✅ Funcionam em tablet
- ✅ Funcionam em smartphone
- ✅ Tema escuro/claro automático

---

## 💡 **Dicas Importantes**

### ✅ Boas Práticas:
1. Use **labels** claros em português
2. Adicione **descriptions** para ajudar usuários
3. Configure **validations** (required, min, max, pattern)
4. Use **readonly** para campos informativos
5. Agrupe campos relacionados com **Group**

### ⚠️ Importante:
- O **Key** do campo deve corresponder à variável no processo
- Use `camunda:formRef` no BPMN para referenciar o formulário
- Formulários `.form` devem estar em `src/main/resources/forms/`

---

## 🎓 **Recursos Adicionais**

- [Documentação Camunda Forms](https://docs.camunda.io/docs/components/modeler/forms/)
- [Tutorial de Forms](https://camunda.com/blog/2022/05/camunda-forms-introduction/)
- [Exemplos de Formulários](https://github.com/camunda/camunda-forms-examples)

---

**Pronto! Agora você pode criar formulários profissionais em português arrastando e soltando elementos!** 🚀✨
