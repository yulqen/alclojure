(ns alclojure.routes.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [alclojure.handlers.home :as home]
            [alclojure.handlers.resources :as resources]
            [alclojure.handlers.auth :as auth]))

(defroutes app-routes*
  ;; Public routes
  (GET "/" [] home/index)
  (GET "/resources" [] resources/index)
  (GET "/resources/:id" [id] resources/show)
  
  ;; Authentication routes
  (GET "/signup" [] auth/signup-page)
  (POST "/signup" [] auth/signup)
  (GET "/login" [] auth/login-page)
  (POST "/login" [] auth/login)
  (GET "/logout" [] auth/logout)
  
  ;; API routes
  (GET "/api/resources" [] resources/api-index)
  
  ;; Static files and 404
  (route/not-found "Not Found"))

(defn app-routes [db]
  (fn [request]
    (app-routes* (assoc request :db db))))