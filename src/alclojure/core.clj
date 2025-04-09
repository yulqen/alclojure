(ns alclojure.core
  (:require [com.stuartsierra.component :as component]
            [alclojure.components.system :as system])
  (:gen-class))

(defn start-system [config]
  (-> (system/new-system config)
      component/start))

(defn -main
  "Start the Alphabet Learning application."
  [& args]
  (let [config {:server {:port 3050}
                :db {:dbtype "sqlite"
                     :dbname "resources/db/database.sqlite"}}
        system (start-system config)]
    (println "Alphabet Learning started on port 3050")
    (.addShutdownHook (Runtime/getRuntime)
                      (Thread. (fn []
                                 (component/stop system)
                                 (println "Alphabet Learning stopped"))))))