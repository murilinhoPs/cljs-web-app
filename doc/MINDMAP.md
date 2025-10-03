# Mapa Mental: Criação de um Projeto CLJS Web App

cljs-web-app/
├─── 📄 Configuração & Metadados
│    ├─── deps.edn (Dependências Clojure)
│    ├─── shadow-cljs.edn (Build do ClojureScript)
│    ├─── package.json (Dependências & Scripts Node.js)
│    ├─── pnpm-lock.yaml (Lockfile do pnpm)
│    ├─── .gitignore (Arquivos ignorados pelo Git)
│    ├─── README.md
│    ├─── CHANGELOG.MD
│    └─── LICENSE
│
├─── 📁 Código Fonte (src)
│    └─── cljs_web_app/
│         ├─── clj/ (Backend Clojure)
│         │    └─── core.clj
│         └─── cljs/ (Frontend ClojureScript)
│              └─── core.cljs
│
├─── 📁 Arquivos Públicos (public)
│    ├─── index.html (Ponto de entrada da UI)
│    ├─── styles/
│    │    └─── index.css
│    └─── resources/ (Assets como imagens, fontes, etc.)
│
├─── 📁 Testes (test)
│    └─── clj/
│         └─── cljs_web_app/
│              └─── core_test.clj
│
├─── 📁 Documentação (doc)
│    ├─── ace-of-cards-analysis.md
│    ├─── MINDMAP.md
│    └─── SETUP.md
│
└─── ⚙️ Ferramentas & Dependências (gerenciados automaticamente)
     ├─── .clj-kondo/ (Configuração do Linter)
     ├─── .cpcache/
     ├─── .git/
     ├─── .lsp/
     ├─── .shadow-cljs/
     └─── node_modules/
