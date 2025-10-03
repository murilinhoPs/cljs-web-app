# Guia de Configuração

## Passo 1: Estrutura Inicial e Dependências

1. **Crie o arquivo `deps.edn`:**

    ```clojure
    {:paths ["src" "resources" "src/cljs" "src/clj"]

     :deps {org.clojure/clojure {:mvn/version "1.12.3"} ;; clj deps
            org.clojure/clojurescript {:mvn/version "1.12.42"}
            org.clojure/tools.cli {:mvn/version "1.2.245"}
            prismatic/schema {:mvn/version "1.4.1"}
            com.taoensso/tempura {:mvn/version "1.5.4"}}

     :aliases {:cljs  {:extra-deps {thheller/shadow-cljs {:mvn/version "3.2.1"} ;; deps apenas para .cljs
                                    lilactown/helix {:mvn/version "0.1.11"}}}
               :build {:main-opts ["-m" "shadow.cljs.devtools.cli"]} ;; equivalente ao "npm start"
               :dev   {:main-opts ["-m" "shadow.cljs.devtools.cli" "watch" "app"]}    ;; equivalente ao "npm run dev"
               :run-m {:main-opts ["-m" "cljs-web-app.clj.core"]}}}
    ```

2. **Crie o arquivo `package.json`:**

    ```json
    {
      "name": "cljs-web-app",
      "version": "1.0.0",
      "scripts": {
        "test": "echo "Error: no test specified" && exit 1",
        "start": "shadow-cljs watch app",
        "deploy": "npx shadow-cljs release app-release"
      },
      "keywords": [],
      "author": "murilinhops",
      "license": "EPL-1.0",
      "description": "A simple web app built with React and Shadow CLJS",
      "dependencies": {
        "lucide-react": "^0.544.0",
        "react": "^19.2.0",
        "react-dom": "^19.2.0",
        "react-refresh": "^0.18.0"
      },
      "devDependencies": {
        "process": "^0.11.10",
        "scheduler": "^0.27.0",
        "shadow-cljs": "^3.2.1"
      }
    }
    ```

## Passo 2: Configuração do Build

1. **Crie o arquivo `shadow-cljs.edn`:**

    ```clojure
    {:source-paths ["src" "test"]
     :deps  {:aliases [:cljs]} ;; pegar todas as deps.edn e + as deps do alias :cljs
     :dev-http {8020 "public"} ;; onde vai servir o server de desenvolvimento
     :builds {:app {:target :browser
                    :output-dir "public/resources/js" ;; onde vai salvar o arquivo js
                    :modules {:main {:init-fn cljs-web-app.cljs.core/init!}}
                    :devtools {:reload-strategy :full
                               :http-root "public"
                               :http-port 8020}}
              :app-release {:target :browser
                            :output-dir "public/resources/js-prod"
                            :modules {:main {:init-fn cljs-web-app.cljs.core/init!}}}}}
    ```

## Passo 3: Esqueleto da Aplicação

1. **Crie o arquivo `public/index.html`:**

    ```html
    <!DOCTYPE html>
    <html>
      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0 viewport-fit=cover">
        <title>Meu Novo App</title>
        <link type="text/css" rel="stylesheet" href="styles/index.css">
      </head>
      <body>
        <div id="app"></div>
        <script src="/resources/js/main.js" defer></script> <!-- dev js, mudar para js-prod -->
      </body>
    </html>
    ```

2. **Crie o arquivo `public/styles/index.css`:**

    ```css
    @import url('https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap'); /* Fonte, pode trocar */

    * {
      /* Reset de estilos */
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    :root {
      /* Variáveis de cores, trocar */
      --main-bg-color: #21202e;
      --grey-text: #b2b9af;
      --text-color: #f1faee;
      --lighter-color: #a8dadc;
      --secondary-color: #457b9d;
      --darker-color: #1e314e;
      --main-color: #cf3441;
      --main-accent-color: #e63946;
      --soft-pink: #d0687a;
      --golden-yellow: #9e8f38;
      --green-check: #51e975;

      background-color: var(--main-bg-color);
      color: var(--text-color);

      font-family: 'Lexend', sans-serif;
      font-synthesis: none;
      text-rendering: optimizeLegibility;
      -moz-osx-font-smoothing: grayscale;
      -webkit-font-smoothing: antialiased;
      -webkit-text-size-adjust: 100%;
    }

    button:hover {
      cursor: pointer;
      opacity: 90%;
    }

    button:disabled, /* Botões desabilitados, default */
    button:disabled:hover,
    #header-button:disabled:hover {
      cursor: default;
      opacity: 70%;
      transform: translateY(0px);
      transition: all 0.64s ease;
    }
    ```

## Passo 4: Código "Hello World"

1. **Crie o arquivo `src/cljs_web_app/cljs/core.cljs`:**

    ```clojure
    (ns cljs-web-app.cljs.core
      (:require ["react-dom/client" :as rdom]
                [helix.core :as hx]
                [helix.dom :as d]))

    (hx/defnc App []
      (d/div
       (d/h1 "Olá, mundo do Helix e React!")
       (d/p "Meu novo app está funcionando.")))

    (defonce root (rdom/createRoot (js/document.getElementById "app")))

    (defn ^:export init! []
      (js/console.log "App initialized!")
      (.render root (hx/$ App)))
    ```
