window.camTasklistConf = {
  // Configuração da aplicação Tasklist em Português BR
  app: {
    name: 'Lista de Tarefas',
    vendor: 'Sistema de Aprovação'
  },
  
  // Forçar idioma para Português BR
  locales: {
    availableLocales: ['pt_BR', 'pt', 'en'],
    fallbackLocale: 'pt_BR'
  },
  
  // Definir locale padrão
  defaultLocale: 'pt_BR',
  
  // Configurações customizadas
  customScripts: {
    // Scripts customizados podem ser adicionados aqui
  },
  
  // Configuração de data/hora
  dateFormat: {
    monthBeforeYear: false,
    monthFormat: 'MMMM'
  },
  
  // Configuração de autenticação
  auth: {
    autoLogin: false
  },
  
  // Configurações de filtros
  defaultFilter: {
    name: 'Todas as Tarefas',
    query: {}
  }
};
