(ns alclojure.components.web-server
  (:require [com.stuartsierra.component :as component]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :as params]
            [ring.middleware.keyword-params :as keyword-params]
            [ring.middleware.session :as session]
            [ring.middleware.resource :as resource]
            [alclojure.routes.core :as routes]))

(defrecord WebServer [config server db]
  component/Lifecycle
  
  (start [component]
    (println "Starting web server component")
    (if server
      component
      (let [handler (-> (routes/app-routes db)
                        (resource/wrap-resource "public")
                        keyword-params/wrap-keyword-params
                        params/wrap-params
                        session/wrap-session)
            server (jetty/run-jetty handler {:port (:port config)
                                            :join? false})]
        (assoc component :server server))))
  
  (stop [component]
    (println "Stopping web server component")
    (when server
      (.stop server))
    (assoc component :server nil)))

(defn new-web-server [config]
  (map->WebServer {:config config}))
