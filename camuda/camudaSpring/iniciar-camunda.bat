@echo off
cls
echo ========================================
echo  INICIANDO CAMUNDA WORKFLOW APP
echo ========================================
echo.
echo Aplicacao: Camunda Platform 7.20.0
echo Porta: 8080
echo.
echo URLs Disponiveis:
echo - http://localhost:8080/camunda/app/tasklist
echo - http://localhost:8080/camunda/app/cockpit
echo - http://localhost:8080/camunda/app/admin
echo.
echo Login: admin / admin
echo.
echo ========================================
echo  NAO FECHE ESTA JANELA!
echo ========================================
echo.

java -jar target\camunda-workflow-app-1.0.0.jar

pause
