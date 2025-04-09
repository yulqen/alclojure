(ns alclojure.models.resource
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [honey.sql :as sql]))

(defn get-all [db]
  (let [query (sql/format {:select [:*]
                          :from [:resource]
                          :order-by [[:id :asc]]})
        ds (:datasource db)]
    (jdbc/execute! ds query {:builder-fn rs/as-unqualified-maps})))

(defn get-by-id [db id]
  (let [query (sql/format {:select [:*]
                          :from [:resource]
                          :where [:= :id id]})
        ds (:datasource db)]
    (first (jdbc/execute! ds query {:builder-fn rs/as-unqualified-maps}))))

(defn create [db resource]
  (let [query (sql/format {:insert-into :resource
                          :values [resource]
                          :returning [:*]})
        ds (:datasource db)]
    (first (jdbc/execute! ds query {:builder-fn rs/as-unqualified-maps}))))