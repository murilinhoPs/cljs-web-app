# Mapa Mental: Criação de um Projeto CLJS Web App

## CLJS Web App/

├─── 1. **Estrutura e Dependências**
│    ├─── 1.1. ***Dependências Clojure ***(`deps.edn`)
│    │    ├─── org.clojure/clojure
│    │    ├─── org.clojure/clojurescript
│    │    ├─── thheller/shadow-cljs
│    │    ├─── lilactown/helix (para React)
│    │    └─── Outras dependências...
│    ├─── 1.2. ***Dependências NPM*** (`package.json`)
│    │    ├─── react
│    │    ├─── react-dom
│    │    ├─── react-refresh
│    │    ├─── lucide-react
│    │    ├─── process (dev)
│    │    ├─── scheduler (dev)
│    │    └─── shadow-cljs (dev)
│    │    ├─── 1.2.1 scripts (`package.json`)
│    │    │  ├─── `"start": "shadow-cljs watch app"`,
│    │    │  └─── `"deploy": "npx shadow-cljs release app-release"`
│    └─── 1.3. ***Estrutura de Pastas***
│         ├─── src/
│         ├─── public/
│         ├─── test/
│         └─── doc/
│
├─── 2. **Configuração do Build**
│    └─── 2.1. `shadow-cljs.edn`
│         ├─── `:source-paths`: Aponta para as pastas com código-fonte.
│         ├─── `:deps`: Define as dependências.
│         └─── `:builds`: Configura os builds da aplicação.
│              ├─── `:app` (Desenvolvimento): Com devtools, hot-reload.
│              └─── `:app-release` (Produção): Otimizado para deploy.
│
├─── 3. **Esqueleto da Aplicação**
│    ├─── 3.1.** `public/index.html`**
│    │    ├─── Define a estrutura base da página.
│    │    ├─── *`<div id="app"></div>`*: Ponto de entrada para o React.
│    │    ├─── *`<script src="/resources/js/main.js">`*: Carrega o JavaScript compilado.
│    │    └─── *`<link rel="stylesheet" href="styles/index.css">`*: Carrega os estilos.
│    └─── 3.2. **`public/styles/index.css`**
│         └─── Estilos globais, reset de CSS e variáveis de cores.
│
└─── 4. **Código "Hello World"**
     └─── 4.1. `src/cljs_web_app/cljs/core.cljs`
          ├─── ***Namespace e Requires***: Importa as bibliotecas necessárias (`react-dom/client`, `helix.core`, `helix.dom`).
          ├─── ***Componente*** `App`: Criado com `helix.core/defnc`.
          └─── ***Renderização***:
               ├─── Cria a "raiz" do React com `rdom/createRoot`.
               ├─── Renderiza o componente `App` na `<div id="app">`.
               └─── `init!` é a função de entrada chamada pelo `shadow-cljs`.
