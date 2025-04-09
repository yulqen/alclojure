(ns alclojure.components.database
  (:require [com.stuartsierra.component :as component]
            [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [clojure.java.io :as io])
  (:import (java.io File)))

(defn init-database
  "Initialize the database with schema if it doesn't exist."
  [db-spec]
  (let [db-file (io/file (:dbname db-spec))]
    (when-not (.exists db-file)
      (.mkdirs (.getParentFile db-file))
      (with-open [conn (jdbc/get-connection db-spec)]
        (jdbc/execute! conn ["
          CREATE TABLE resource_type (
            id INTEGER PRIMARY KEY,
            name TEXT NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
          )"])
        (jdbc/execute! conn ["
          CREATE TABLE resource_category (
            id INTEGER PRIMARY KEY,
            name TEXT NOT NULL,
            colour_css_class TEXT NOT NULL,
            badge_foreground_colour TEXT NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
          )"])
        (jdbc/execute! conn ["
          CREATE TABLE resource_subcategory (
            id INTEGER PRIMARY KEY,
            name TEXT NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
          )"])
        (jdbc/execute! conn ["
          CREATE TABLE resource (
            id INTEGER PRIMARY KEY,
            name TEXT NOT NULL,
            stripe_product_id TEXT,
            thumbnail_filenames TEXT,
            resource_type_id INTEGER REFERENCES resource_type(id),
            main_resource_category_id INTEGER REFERENCES resource_category(id),
            description TEXT,
            card_description TEXT,
            age_range TEXT,
            curriculum TEXT,
            feature_slot INTEGER,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
          )"])
        (jdbc/execute! conn ["
          CREATE TABLE resource_subcategories (
            resource_id INTEGER REFERENCES resource(id),
            subcategory_id INTEGER REFERENCES resource_subcategory(id),
            PRIMARY KEY (resource_id, subcategory_id)
          )"])
        (jdbc/execute! conn ["
          CREATE TABLE pdf_resource (
            id INTEGER PRIMARY KEY,
            resource_id INTEGER REFERENCES resource(id),
            file_name TEXT NOT NULL,
            file_size INTEGER NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
          )"])
        (jdbc/execute! conn ["
          CREATE TABLE pdf_page_snapshot (
            id INTEGER PRIMARY KEY,
            name TEXT NOT NULL,
            file_name TEXT NOT NULL,
            pdf_file_id INTEGER REFERENCES pdf_resource(id),
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
          )"])
        (jdbc/execute! conn ["
          CREATE TABLE user (
            id INTEGER PRIMARY KEY,
            email TEXT UNIQUE NOT NULL,
            password_hash TEXT NOT NULL,
            is_verified BOOLEAN DEFAULT FALSE,
            free_downloads_remaining INTEGER DEFAULT 0,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
          )"])))))

(defrecord Database [db-spec datasource]
  component/Lifecycle
  
  (start [component]
    (println "Starting database component")
    (init-database db-spec)
    (let [ds (jdbc/get-datasource db-spec)]
      (assoc component :datasource ds)))
  
  (stop [component]
    (println "Stopping database component")
    (assoc component :datasource nil)))

(defn new-database [db-config]
  (map->Database {:db-spec db-config}))