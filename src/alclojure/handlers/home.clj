(ns alclojure.handlers.home
  (:require [alclojure.views.home :as views]))

(defn index [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (views/index-page)})