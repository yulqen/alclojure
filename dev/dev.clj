(ns dev
  (:require [alclojure.core :as core]
            [alclojure.components.system :as system]
            [com.stuartsierra.component :as component]))

(def system nil)

(def config
  {:server {:port 3050}
   :db {:dbtype "sqlite"
        :dbname "resources/db/database.sqlite"}})

(defn init
  "Initialize the system."
  []
  (alter-var-root #'system (constantly (system/new-system config))))

(defn start
  "Start the system."
  []
  (alter-var-root #'system component/start)
  (println "Alphabet Learning started on port 3050")
  :started)

(defn stop
  "Stop the system."
  []
  (when system
    (alter-var-root #'system component/stop)
    (println "Alphabet Learning stopped"))
  :stopped)

(defn go
  "Initialize and start the system."
  []
  (init)
  (start))

(defn restart
  "Stop and restart the system without code reloading."
  []
  (stop)
  (go))

(comment
  ;; Example usage in REPL:
  ;; Start the server
  (go)
  
  ;; Stop the server
  (stop)
  
  ;; Just restart without reloading
  (restart)
)