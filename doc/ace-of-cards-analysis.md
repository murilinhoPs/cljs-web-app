# Análise do Repositório: ace-of-cards

## Visão Geral

O projeto **ace-of-cards** é uma aplicação web interativa, provavelmente um jogo de cartas, construída com **ClojureScript** e **React**. Ele utiliza a biblioteca **Reagent**, que é um wrapper comum em ClojureScript para usar React de forma mais idiomática.

---

## 1. Estrutura do Repositório

A estrutura do projeto é bem definida e segue padrões comuns para aplicações ClojureScript:

- **`.github/workflows/`**: Contém a configuração de CI/CD (Integração Contínua e Deploy Contínuo) usando GitHub Actions.
- **`public/`**: Armazena os arquivos estáticos da aplicação.
  - `index.html`: É o ponto de entrada da aplicação web. Ele carrega o CSS e o JavaScript compilado.
  - `css/`: Contém as folhas de estilo.
- **`src/`**: Onde fica todo o código-fonte da aplicação em ClojureScript (`.cljs`).
  - `main/core.cljs`: É o arquivo principal que inicia a aplicação.
- **`deps.edn`**: Arquivo de configuração para gerenciar as dependências do Clojure/ClojureScript.
- **`package.json`**: Padrão do ecossistema Node.js, usado para gerenciar dependências JavaScript (como React, ReactDOM) e para definir scripts de build/desenvolvimento.
- **`shadow-cljs.edn`**: O arquivo de configuração central para o `shadow-cljs`, a ferramenta de build do projeto. Ele define como o código ClojureScript é compilado para JavaScript.
- **`karma.conf.js`**: Arquivo de configuração para o Karma, um executor de testes para JavaScript, indicando que o projeto tem uma estrutura de testes.

---

## 2. Ferramentas Utilizadas

- **Linguagem Principal**: **ClojureScript**, um dialeto de Clojure que compila para JavaScript.
- **Biblioteca de UI**: **React**, através do wrapper **Reagent**, que permite criar componentes de UI usando uma sintaxe declarativa e funcional.
- **Ferramenta de Build**: **Shadow-cljs**. É uma ferramenta de compilação extremamente popular e poderosa no ecossistema ClojureScript. Ela é responsável por:
  - Compilar o código `.cljs` para `.js`.
  - Gerenciar um servidor de desenvolvimento com *hot-reloading* (atualização automática do código no navegador).
  - Otimizar o código para produção (minificação, etc.).
- **Gerenciador de Pacotes**:
  - **Clojure CLI + deps.edn**: Para as dependências do mundo Clojure (ex: `reagent`).
  - **NPM/Yarn**: Para as dependências do mundo JavaScript (ex: `react`, `react-dom`).
- **Testes**: **Karma**, para rodar testes automatizados no código compilado.
- **CI/CD**: **GitHub Actions**, para automatizar o build e o deploy da aplicação.

---

## 3. Configuração: CLJS com React/HTML

A conexão entre as partes da aplicação acontece em três arquivos principais: `public/index.html`, `shadow-cljs.edn` e `src/main/core.cljs`.

1. **`public/index.html`**:
    - Contém um elemento HTML, `<div id="app"></div>`, que servirá como o "container" da aplicação React.
    - Inclui a tag `<script>` que carrega o JavaScript compilado: `<script src="/js/main.js" type="text/javascript"></script>`.

2. **`shadow-cljs.edn`**:
    - Na seção `:builds`, a configuração `:app` define como o código será compilado.
    - `:target :browser` especifica que o código será executado no navegador.
    - `:output-dir "public/js"` instrui o compilador a colocar os arquivos JavaScript gerados na pasta `public/js`.
    - `:modules {:main {:init-fn main.core/init}}` define que o ponto de entrada do código é a função `init` no namespace `main.core`.

3. **`src/main/core.cljs`**:
    - A função `init` usa `reagent.dom/render` para "montar" o componente principal da aplicação na `div#app` do `index.html`. A chamada é similar a:

      ```clojure
      (reagent.dom/render [componente-principal] (js/document.getElementById "app"))
      ```

**Fluxo:** O navegador carrega o `index.html`, que por sua vez carrega o `main.js`. Este arquivo, compilado pelo `shadow-cljs`, contém todo o código da aplicação. A função `main.core/init` é chamada, e o Reagent/React assume o controle da `div#app`, renderizando a interface do usuário.

---

## 4. Regras de CI/CD para Deploy

O deploy é automatizado pelo arquivo `.github/workflows/deploy.yml` usando **GitHub Actions**.

- **Gatilho (Trigger)**: O processo é acionado por um `push` na branch `main`.

- **Processo de Build e Deploy**:
  1. **Setup**: Inicia uma máquina virtual Ubuntu.
  2. **Checkout**: Baixa o código do repositório.
  3. **Install Dependencies**: Executa `npm install` para instalar as dependências JavaScript.
  4. **Build**: Executa `npm run release` (que roda `shadow-cljs release app`) para compilar e otimizar o código para produção.
  5. **Deploy**: Usa a action `peaceiris/actions-gh-pages` para enviar o conteúdo da pasta `public/` para a branch `gh-pages`, publicando a aplicação no GitHub Pages.

---

## Guia: Criando um Novo Web App com a Mesma Stack (Helix)

Este é um passo a passo para criar um projeto do zero com ClojureScript, React (via Helix), shadow-cljs e deps.edn.

### Pré-requisitos

1. **JDK (Java Development Kit)**: Versão 8 ou superior.
2. **Clojure CLI Tools**: Para gerenciar o `deps.edn`.
3. **Node.js e NPM**: Para gerenciar o `package.json`.

---

### Passo a Passo: Criando o Projeto via CLI

#### Passo 1: Criar a Estrutura de Pastas

```bash
# 1. Crie e entre na pasta do projeto
mkdir meu-novo-app && cd meu-novo-app

# 2. Crie as pastas para o código-fonte e arquivos públicos
mkdir -p src/meu_novo_app
mkdir public
```

#### Passo 2: Configurar as Dependências ClojureScript (`deps.edn`)

Crie o arquivo `deps.edn` com o seguinte conteúdo:

```clojure
;; deps.edn
{:paths ["src"]

 :deps
 {org.clojure/clojurescript {:mvn/version "1.11.60"}
  lilactown/helix            {:mvn/version "0.1.9"}}

 :aliases
 {:build
  {:main-opts ["-m" "shadow.cljs.devtools.cli"]}}}
```

#### Passo 3: Configurar as Dependências JavaScript (`package.json`)

Execute `npm init -y` e depois instale as dependências:

```bash
npm install react react-dom
npm install -D shadow-cljs
```

Edite seu `package.json` para adicionar os scripts:

```json
// package.json
{
  "name": "meu-novo-app",
  "version": "1.0.0",
  "scripts": {
    "dev": "shadow-cljs watch app",
    "release": "shadow-cljs release app"
  },
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0"
  },
  "devDependencies": {
    "shadow-cljs": "^2.20.14"
  }
}
```

#### Passo 4: Configurar o `shadow-cljs`

Crie o arquivo `shadow-cljs.edn`:

```clojure
;; shadow-cljs.edn
{:source-paths
 ["src"]

 :dependencies
 []

 :builds
 {:app
  {:target     :browser
   :output-dir "public/js"
   :asset-path "/js"
   :modules    {:main {:init-fn meu-novo-app.core/init!}}
   :devtools   {:http-root "public"
                :http-port 8080}}}}
```

#### Passo 5: Criar o Ponto de Entrada (HTML e CLJS)

1. **Crie o `public/index.html`**:

    ```html
    <!-- public/index.html -->
    <!DOCTYPE html>
    <html>
      <head>
        <meta charset="UTF-8">
        <title>Meu Novo App</title>
      </head>
      <body>
        <div id="app"></div>
        <script src="/js/main.js"></script>
      </body>
    </html>
    ```

2. **Crie o `src/meu_novo_app/core.cljs`**:

    ```clojure
    ;; src/meu_novo_app/core.cljs
    (ns meu-novo-app.core
      (:require
       [helix.core :as hx]
       [helix.dom :as d]
       [react-dom/client :as rdom]))

    (hx/defnc App []
      (d/h1 "Olá, mundo do Helix e React!")
      (d/p "Meu novo app está funcionando."))

    (defn ^:export init! []
      (let [root (rdom/createRoot (js/document.getElementById "app"))]
        (rdom/render root (hx/$ App))))
    ```

#### Passo 6: Rodar a Aplicação

1. **Inicie o compilador**:

    ```bash
    npm run dev
    ```

2. **Abra no navegador**:
    Acesse **<http://localhost:8080>**.

---

### CLI vs. VSCode (com Calva)

- **CLI**: O método acima é a base, funciona em qualquer lugar.
- **VSCode + Calva**: A extensão Calva melhora a experiência com um **REPL Interativo**.
  - Use o comando **`Calva: Start a Project REPL and Connect (aka "Jack-in")`**.
  - Escolha `shadow-cljs` e o build `:app`.
  - O Calva inicia o processo e conecta o editor à sua aplicação em execução.
  - Isso permite que você avalie código do seu editor diretamente no navegador, uma forma de desenvolvimento muito mais dinâmica e poderosa.

---

## O que é `shadow.cljs.devtools.cli` no `deps.edn`?

Em resumo, `shadow.cljs.devtools.cli` é o **ponto de entrada da interface de linha de comando (CLI) do `shadow-cljs`**.

### Detalhes

1. **Aliases no `deps.edn`**: A seção `:aliases` permite criar "atalhos" ou configurações alternativas que você pode ativar ao rodar um comando `clj`.

2. **O Alias `:build`**: O alias que criamos, `:build`, contém a instrução `:main-opts ["-m" "shadow.cljs.devtools.cli"]`. Isso significa:
    > "Quando o alias `:build` for ativado, execute a função principal (`-main`) do namespace `shadow.cljs.devtools.cli`."

3. **O Namespace `shadow.cljs.devtools.cli`**: Este é o código dentro da biblioteca `shadow-cljs` que sabe como interpretar comandos de terminal como `watch`, `release`, `compile`, etc. É a própria aplicação de linha de comando.

### Duas Maneiras de Rodar o Mesmo Comando

Isso nos dá duas maneiras de executar os comandos do `shadow-cljs`:

**1. A Forma `npm` (usada no tutorial):**

```bash
# O comando "npm run dev" executa o script definido no package.json
npm run dev

# que por sua vez chama o executável do shadow-cljs
# shadow-cljs watch app
```

**2. A Forma `clj` (usando o alias):**

```bash
# clj -M:build <comando>
clj -M:build watch app
```

Ambas as formas, `npm run dev` e `clj -M:build watch app`, são funcionalmente equivalentes.

### Por que ter o alias?

- **Flexibilidade**: Permite que desenvolvedores usem a toolchain do Clojure (`clj`) em vez do Node.js (`npm`) se preferirem.
- **Integração**: Algumas ferramentas do ecossistema Clojure podem usar este alias para se integrar ao build.
- **Padrão da Comunidade**: É uma convenção comum em projetos ClojureScript, oferecendo uma forma padronizada de interagir com o `shadow-cljs`.
