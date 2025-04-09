(ns alclojure.handlers.auth
  (:require [alclojure.views.auth :as views]
            [alclojure.models.user :as user-model]
            [buddy.hashers :as hashers]))

(defn signup-page [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (views/signup-page)})

(defn signup [request]
  (let [db (:db request)
        params (:params request)
        email (:email params)
        password (:password params)]
    (if-let [existing-user (user-model/get-by-email db email)]
      ;; User already exists
      {:status 400
       :headers {"Content-Type" "text/html"}
       :body (views/signup-page {:error "Email already registered"})}
      ;; Create new user
      (let [password-hash (hashers/derive password)
            user (user-model/create db {:email email
                                        :password_hash password-hash
                                        :free_downloads_remaining 3})]
        {:status 302
         :headers {"Location" "/login"}
         :session {:flash {:type "success"
                           :message "Account created! Please log in."}}
         :body ""}))))

(defn login-page [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (views/login-page)})

(defn login [request]
  (let [db (:db request)
        params (:params request)
        email (:email params)
        password (:password params)
        user (user-model/get-by-email db email)]
    (if (and user (hashers/check password (:password_hash user)))
      ;; Successful login
      {:status 302
       :headers {"Location" "/"}
       :session {:user-id (:id user)}
       :body ""}
      ;; Failed login
      {:status 400
       :headers {"Content-Type" "text/html"}
       :body (views/login-page {:error "Invalid email or password"})})))

(defn logout [request]
  {:status 302
   :headers {"Location" "/"}
   :session nil
   :body ""})