# 🚀 Comandos Docker - n8n Workflows

## 📦 Ambientes Disponíveis

- 🔧 **DEV** (Development) - Localhost
- 🧪 **STAGING** - Servidor de Teste  
- 🚀 **PRODUCTION** - Servidor Real

---

## 💻 DEVELOPMENT (Local)

### Iniciar ambiente DEV:
```bash
docker-compose -f docker-compose.dev.yml --env-file .env.dev up -d
```

### Ver logs:
```bash
docker-compose -f docker-compose.dev.yml logs -f
```

### Parar:
```bash
docker-compose -f docker-compose.dev.yml down
```

### Reiniciar:
```bash
docker-compose -f docker-compose.dev.yml restart
```

### Ver status:
```bash
docker-compose -f docker-compose.dev.yml ps
```

### Acessar:
- URL: http://localhost:5678
- Sem autenticação (acesso direto)

---

## 🧪 STAGING (Servidor de Teste)

### Iniciar ambiente STAGING:
```bash
docker-compose -f docker-compose.staging.yml --env-file .env.staging up -d
```

### Ver logs:
```bash
docker-compose -f docker-compose.staging.yml logs -f
```

### Parar:
```bash
docker-compose -f docker-compose.staging.yml down
```

### Reiniciar:
```bash
docker-compose -f docker-compose.staging.yml restart
```

### Ver status:
```bash
docker-compose -f docker-compose.staging.yml ps
```

### Acessar:
- URL: https://staging.seudominio.com:5678
- Usuário: Conforme configurado no .env.staging
- Senha: Conforme configurado no .env.staging

---

## 🚀 PRODUCTION (Servidor Real)

### Iniciar ambiente PRODUCTION:
```bash
docker-compose -f docker-compose.production.yml --env-file .env.production up -d
```

### Ver logs:
```bash
docker-compose -f docker-compose.production.yml logs -f
```

### Parar:
```bash
docker-compose -f docker-compose.production.yml down
```

### Reiniciar:
```bash
docker-compose -f docker-compose.production.yml restart
```

### Ver status:
```bash
docker-compose -f docker-compose.production.yml ps
```

### Acessar:
- URL: https://n8n.seudominio.com
- Usuário: Conforme configurado no .env.production
- Senha: Conforme configurado no .env.production

---

## 🔄 Comandos Gerais (Todos os Ambientes)

### Atualizar imagens:
```bash
# DEV
docker-compose -f docker-compose.dev.yml pull

# STAGING
docker-compose -f docker-compose.staging.yml pull

# PRODUCTION
docker-compose -f docker-compose.production.yml pull
```

### Ver todos os containers:
```bash
docker ps -a
```

### Limpar containers parados:
```bash
docker container prune
```

### Ver uso de recursos:
```bash
docker stats
```

### Limpar volumes (⚠️ APAGA DADOS):
```bash
# DEV
docker-compose -f docker-compose.dev.yml down -v

# STAGING
docker-compose -f docker-compose.staging.yml down -v

# PRODUCTION (CUIDADO!)
docker-compose -f docker-compose.production.yml down -v
```

---

## 💾 Backup e Restore

### Criar backup STAGING:
```bash
docker-compose -f docker-compose.staging.yml exec postgres pg_dump -U n8n n8n_staging > backup_staging.sql
```

### Criar backup PRODUCTION:
```bash
docker-compose -f docker-compose.production.yml exec postgres pg_dump -U n8n_prod n8n_production > backup_production.sql
```

### Restaurar backup:
```bash
# STAGING
cat backup_staging.sql | docker-compose -f docker-compose.staging.yml exec -T postgres psql -U n8n n8n_staging

# PRODUCTION
cat backup_production.sql | docker-compose -f docker-compose.production.yml exec -T postgres psql -U n8n_prod n8n_production
```

---

## 🎯 Fluxo de Trabalho Recomendado

1. **Desenvolver no DEV:**
   ```bash
   docker-compose -f docker-compose.dev.yml --env-file .env.dev up -d
   ```

2. **Testar no STAGING:**
   ```bash
   docker-compose -f docker-compose.staging.yml --env-file .env.staging up -d
   ```

3. **Deploy em PRODUCTION:**
   ```bash
   docker-compose -f docker-compose.production.yml --env-file .env.production up -d
   ```

---

## 📝 Notas Importantes

- **DEV**: Sem autenticação, logs detalhados, ideal para desenvolvimento
- **STAGING**: Com autenticação, PostgreSQL, para testes antes de produção
- **PRODUCTION**: Máxima segurança, backups, monitoramento

---

## 🆘 Troubleshooting

### Container não inicia:
```bash
docker-compose -f docker-compose.dev.yml logs
```

### Porta já está em uso:
```bash
# Ver o que está usando a porta 5678
netstat -ano | findstr :5678

# Matar o processo (Windows)
taskkill /PID <numero_do_pid> /F
```

### Resetar tudo:
```bash
docker-compose -f docker-compose.dev.yml down -v
docker-compose -f docker-compose.dev.yml up -d
```
