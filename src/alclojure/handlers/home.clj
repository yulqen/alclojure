(ns alclojure.handlers.home
  (:require [alclojure.views.home :as views]))

(defn index [request]
  (let [user (get-in request [:session :user])]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (views/index-page user)}))