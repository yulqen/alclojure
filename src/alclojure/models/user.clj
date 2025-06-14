(ns alclojure.models.user
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [honey.sql :as sql]))

(defn get-by-email [db email]
  (let [query (sql/format {:select [:*]
                           :from   [:user]
                           :where  [:= :email email]})
        ds (:datasource db)]
    (first (jdbc/execute! ds query {:builder-fn rs/as-unqualified-maps}))))

(defn get-by-id [db id]
  (let [query (sql/format {:select [:*]
                           :from   [:user]
                           :where  [:= :id id]})
        ds (:datasource db)]
    (first (jdbc/execute! ds query {:builder-fn rs/as-unqualified-maps}))))

(defn create [db user]
  (let [query (sql/format {:insert-into :user
                           :values      [user]
                           :returning   [:*]})
        ds (:datasource db)]
    (first (jdbc/execute! ds query {:builder-fn rs/as-unqualified-maps}))))