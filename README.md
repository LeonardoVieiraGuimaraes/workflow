# Projeto de Workflow com n8n e Docker

Exemplo simples de automação de workflows usando n8n com Docker.

## 🚀 O que é n8n?

n8n é uma ferramenta de automação de workflows que permite conectar diferentes aplicações através de uma interface visual.

## 📋 Pré-requisitos

- Docker instalado ([Download Docker](https://www.docker.com/products/docker-desktop))

## � Como Usar

### Iniciar o n8n:
```powershell
.\start.ps1
```

Ou manualmente:
```powershell
docker-compose up -d
```

### Ver os logs:
```powershell
docker-compose logs -f
```

### Parar o n8n:
```powershell
.\stop.ps1
```

Ou manualmente:
```powershell
docker-compose down
```

## 🌐 Acessando o n8n

Após iniciar, acesse:
- **URL**: http://localhost:5678
- Na primeira vez, você criará seu usuário e senha

## 🚀 Ambientes Disponíveis

Este projeto está configurado para 3 ambientes:

### 1. 🔧 **DEV - Development** (docker-compose.dev.yml)
- Desenvolvimento local
- Sem autenticação (facilita testes)
- Banco SQLite (leve e rápido)
- Logs detalhados (debug)
```bash
docker-compose -f docker-compose.dev.yml --env-file .env.dev up -d
# Acesse: http://localhost:5678
```

### 2. 🧪 **STAGING** (docker-compose.staging.yml)
- Para testes no servidor
- Com autenticação
- PostgreSQL
- Domínio de staging
```bash
docker-compose -f docker-compose.staging.yml --env-file .env.staging up -d
# Acesse: https://staging.seudominio.com:5678
```

### 3. 🚀 **PRODUCTION** (docker-compose.production.yml)
- Ambiente de produção
- Máxima segurança
- PostgreSQL
- Backups automáticos
- Limites de recursos
```bash
docker-compose -f docker-compose.production.yml --env-file .env.production up -d
# Acesse: https://n8n.seudominio.com
```

## 📖 Documentação

- 🚀 **[COMANDOS.md](./COMANDOS.md)** - Todos os comandos Docker
- � **[DEPLOY.md](./DEPLOY.md)** - Guia completo de deploy

## 💡 Próximos Passos

1. 🔧 Desenvolva localmente com DEV
2. 🧪 Teste em STAGING
3. 🚀 Deploy em PRODUCTION

## 🎯 Comandos Rápidos

```bash
# DEV (Desenvolvimento Local)
docker-compose -f docker-compose.dev.yml --env-file .env.dev up -d

# STAGING (Servidor de Teste)
docker-compose -f docker-compose.staging.yml --env-file .env.staging up -d

# PRODUCTION (Servidor Real)
docker-compose -f docker-compose.production.yml --env-file .env.production up -d
```

📝 Veja todos os comandos em **[COMANDOS.md](./COMANDOS.md)**

## 📚 Exemplos Simples

- Enviar notificações por email
- Integrar com Google Sheets
- Criar webhooks para receber dados
- Automatizar tarefas repetitivas

## 🛠️ Comandos Úteis

### Ver status:
```powershell
docker-compose ps
```

### Reiniciar:
```powershell
docker-compose restart
```

### Limpar tudo (apaga dados):
```powershell
docker-compose down -v
```

## 📖 Mais Informações

- [Documentação n8n](https://docs.n8n.io/)
- [Templates prontos](https://n8n.io/workflows/)

---

Feito com ❤️ usando n8n e Docker
