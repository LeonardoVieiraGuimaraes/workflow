# 🚀 Guia de Deploy - Staging e Produção

Este guia explica como fazer deploy do n8n em ambientes de staging e produção.

## 📋 Pré-requisitos no Servidor

### 1. Instalar Docker e Docker Compose
```bash
# Ubuntu/Debian
curl -fsSL https://get.docker.com | sh
sudo usermod -aG docker $USER
```

### 2. Configurar Firewall
```bash
# Liberar portas necessárias
sudo ufw allow 80/tcp
sudo ufw allow 443/tcp
sudo ufw allow 5678/tcp  # Apenas para staging/testes
sudo ufw enable
```

### 3. Configurar DNS
Aponte seus domínios para o IP do servidor:
- `staging.seudominio.com` → IP do servidor
- `n8n.seudominio.com` → IP do servidor

## 🧪 Deploy em Staging

### 1. Preparar ambiente
```powershell
# Edite as configurações
notepad .env.staging

# Altere:
# - N8N_HOST para seu domínio de staging
# - Senhas (use senhas diferentes de produção)
```

### 2. Fazer deploy
```powershell
.\deploy-staging.ps1
```

### 3. Acessar
```
https://staging.seudominio.com:5678
```

### 4. Testar workflows
- Crie e teste seus workflows em staging
- Valide integrações
- Teste webhooks

## 🚀 Deploy em Produção

### 1. Preparar ambiente
```powershell
# Edite as configurações
notepad .env.production

# ⚠️ IMPORTANTE:
# - Altere TODAS as senhas
# - Gere chave de criptografia: openssl rand -hex 32
# - Configure domínio correto
```

### 2. Gerar chave de criptografia
```powershell
# No PowerShell (Windows)
[System.Convert]::ToBase64String((1..32 | ForEach-Object { Get-Random -Minimum 0 -Maximum 256 }))

# Ou no Linux/Mac
openssl rand -hex 32
```

### 3. Fazer deploy
```powershell
.\deploy-production.ps1
```

## 💾 Backups

### Criar backup
```powershell
# Staging
.\backup.ps1 -Environment staging

# Production
.\backup.ps1 -Environment production
```

### Restaurar backup
```powershell
# Staging
.\restore.ps1 -Environment staging -BackupFile "backups/postgres/backup_staging_20250104.sql.zip"

# Production
.\restore.ps1 -Environment production -BackupFile "backups/postgres/backup_production_20250104.sql.zip"
```

### Agendar backups automáticos
```powershell
# Criar tarefa agendada (Windows)
# Execute como Administrador:

$action = New-ScheduledTaskAction -Execute "PowerShell.exe" -Argument "-File D:\GitHub\ProjetosPessoais\workflow\backup.ps1 -Environment production"
$trigger = New-ScheduledTaskTrigger -Daily -At 3am
Register-ScheduledTask -Action $action -Trigger $trigger -TaskName "N8N Backup" -Description "Backup diário do n8n"
```

## 🔄 Migração Staging → Production

### 1. Exportar workflows do staging
```bash
# No servidor de staging
docker-compose -f docker-compose.staging.yml exec n8n n8n export:workflow --all --output=/home/node/backups/workflows.json
```

### 2. Importar workflows na produção
```bash
# No servidor de produção
docker-compose -f docker-compose.production.yml exec n8n n8n import:workflow --input=/home/node/backups/workflows.json
```

## 🔒 Checklist de Segurança

### Staging
- [ ] Senhas diferentes de produção
- [ ] Acesso restrito por IP (opcional)
- [ ] HTTPS configurado
- [ ] Backups automáticos

### Production
- [ ] Senhas fortes e únicas
- [ ] Chave de criptografia gerada
- [ ] HTTPS obrigatório
- [ ] Backups automáticos diários
- [ ] Monitoramento configurado
- [ ] Limites de recursos configurados
- [ ] Firewall configurado
- [ ] Acesso restrito

## 🌐 Configurar HTTPS com Let's Encrypt

### Opção 1: Certbot (Recomendado)
```bash
# Instalar Certbot
sudo apt install certbot python3-certbot-nginx

# Gerar certificado
sudo certbot --nginx -d staging.seudominio.com
sudo certbot --nginx -d n8n.seudominio.com

# Renovação automática
sudo certbot renew --dry-run
```

### Opção 2: Cloudflare (Mais fácil)
1. Adicione seu domínio ao Cloudflare
2. Configure DNS
3. Ative SSL/TLS (Full ou Full Strict)
4. n8n funcionará automaticamente com HTTPS

## 📊 Monitoramento

### Ver logs em tempo real
```powershell
# Staging
docker-compose -f docker-compose.staging.yml logs -f

# Production
docker-compose -f docker-compose.production.yml logs -f
```

### Verificar status dos containers
```powershell
docker ps
docker stats
```

### Verificar uso de recursos
```bash
docker system df
docker volume ls
```

## 🆘 Troubleshooting

### Container não inicia
```powershell
# Ver logs detalhados
docker-compose -f docker-compose.production.yml logs

# Verificar configurações
docker-compose -f docker-compose.production.yml config
```

### Resetar ambiente (⚠️ APAGA DADOS)
```powershell
# Staging
docker-compose -f docker-compose.staging.yml down -v

# Production (CUIDADO!)
docker-compose -f docker-compose.production.yml down -v
```

### PostgreSQL não conecta
```bash
# Verificar se está rodando
docker-compose -f docker-compose.production.yml exec postgres pg_isready

# Ver logs do PostgreSQL
docker-compose -f docker-compose.production.yml logs postgres
```

## 📈 Próximos Passos

1. ✅ Deploy em staging
2. ✅ Testar workflows
3. ✅ Configurar backups automáticos
4. ✅ Configurar HTTPS
5. ✅ Deploy em produção
6. ✅ Monitorar logs e métricas

## 🔗 Links Úteis

- [Documentação n8n](https://docs.n8n.io/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Let's Encrypt](https://letsencrypt.org/)
- [Cloudflare](https://cloudflare.com/)

---

⚠️ **IMPORTANTE**: Sempre teste em staging antes de fazer deploy em produção!
