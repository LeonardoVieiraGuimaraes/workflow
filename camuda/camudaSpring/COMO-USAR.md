# 🚨 INSTRUÇÕES PARA USAR A APLICAÇÃO CAMUNDA

## ⚠️ PROBLEMA IDENTIFICADO:

Você está **apertando Ctrl+C** no terminal, o que **PARA a aplicação**!

Quando você vê isso no terminal:
```
^C
Deseja finalizar o arquivo em lotes (S/N)?
Process Engine default closed
```

Isso significa que **VOCÊ INTERROMPEU A APLICAÇÃO**!

---

## ✅ COMO INICIAR CORRETAMENTE:

### **1. Abra um terminal PowerShell**

### **2. Execute:**
```powershell
cd D:\GitHub\ProjetosPessoais\workflow\Camunda
.\iniciar-camunda.bat
```

### **3. Aguarde a mensagem:**
```
Started CamundaApplication in X seconds
```

### **4. ⚠️ NÃO APERTE CTRL+C!**
### **5. ⚠️ NÃO FECHE O TERMINAL!**

---

## 🌐 QUANDO A APLICAÇÃO ESTIVER RODANDO:

### **Abra o navegador e acesse:**

1. **Tasklist (Lista de Tarefas)**
   ```
   http://localhost:8080/camunda/app/tasklist
   ```

2. **Cockpit (Painel de Controle)**
   ```
   http://localhost:8080/camunda/app/cockpit
   ```

3. **Admin (Administração)**
   ```
   http://localhost:8080/camunda/app/admin
   ```

**Login:** `admin` / `admin`

---

## 🧹 ANTES DE ACESSAR:

### **LIMPE O CACHE DO NAVEGADOR!**

1. Pressione `Ctrl + Shift + Delete`
2. Marque "Cached images and files" (Imagens e arquivos em cache)
3. Clique em "Clear data" (Limpar dados)
4. Feche e abra o navegador novamente

**OU use modo anônimo:** `Ctrl + Shift + N`

---

## 🛑 PARA PARAR A APLICAÇÃO:

### **SOMENTE quando quiser REALMENTE parar:**

1. Vá no terminal onde a aplicação está rodando
2. Pressione `Ctrl + C`
3. Digite `S` (Sim) para confirmar
4. Aguarde a mensagem "Process Engine default closed"

---

## 🔧 SE OS ENDPOINTS DEREM ERRO:

### **Verifique se a aplicação está rodando:**

```powershell
Get-Process -Name java
```

Se não houver processos Java, **a aplicação NÃO está rodando**!

### **Teste a conexão:**

```powershell
Invoke-WebRequest -Uri "http://localhost:8080/camunda/app/tasklist/" -UseBasicParsing
```

Se der erro "Nenhuma conexão pôde ser feita", **a aplicação NÃO está rodando**!

---

## ✅ CHECKLIST:

- [ ] Terminal aberto com `.\iniciar-camunda.bat`
- [ ] Mensagem "Started CamundaApplication" apareceu
- [ ] **NÃO apertei Ctrl+C**
- [ ] Terminal ainda está aberto e rodando
- [ ] Cache do navegador limpo
- [ ] Acessei http://localhost:8080/camunda/app/tasklist
- [ ] Fiz login com admin/admin

---

## 💡 DICA:

**Deixe o terminal minimizado, mas NÃO feche!**

A aplicação precisa ficar rodando no terminal para funcionar!

---

## 🎯 RESUMO DO PROBLEMA:

```
Você: *aperta Ctrl+C*
Aplicação: *para*
Você: "Por que os endpoints dão erro?"
Resposta: PORQUE VOCÊ PAROU A APLICAÇÃO! 😅
```

**Solução:** NÃO aperte Ctrl+C! Deixe a aplicação rodando!
