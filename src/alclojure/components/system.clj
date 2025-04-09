(ns alclojure.components.system
  (:require [com.stuartsierra.component :as component]
            [alclojure.components.web-server :as web-server]
            [alclojure.components.database :as database]))

(defn new-system
  "Constructs a new system with the given configuration."
  [config]
  (component/system-map
   :db (database/new-database (:db config))
   :web-server (component/using
                (web-server/new-web-server (:server config))
                [:db])))