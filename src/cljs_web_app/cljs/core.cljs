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
