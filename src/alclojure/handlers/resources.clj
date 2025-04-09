(ns alclojure.handlers.resources
  (:require [alclojure.views.resources :as views]
            [alclojure.models.resource :as resource-model]
            [clojure.data.json :as json]))

(defn index [request]
  (let [db (:db request)
        resources (resource-model/get-all db)]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (views/index-page resources)}))

(defn show [request]
  (let [db (:db request)
        id (get-in request [:params :id])
        resource (resource-model/get-by-id db id)]
    (if resource
      {:status 200
       :headers {"Content-Type" "text/html"}
       :body (views/show-page resource)}
      {:status 404
       :headers {"Content-Type" "text/html"}
       :body "Resource not found"})))

(defn api-index [request]
  (let [db (:db request)
        resources (resource-model/get-all db)]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/write-str resources)}))