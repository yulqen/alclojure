(ns alclojure.models.resource-test
  (:require [clojure.test :refer :all]
            [alclojure.models.resource :as resource]
            [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]))

(defn test-db-fixture [f]
  (let [db-spec {:dbtype "sqlite"
                :dbname "resources/db/test-database.sqlite"}
        db {:datasource (jdbc/get-datasource db-spec)}]
    ;; Create test database
    (jdbc/execute! (:datasource db) ["CREATE TABLE resource (
                                        id INTEGER PRIMARY KEY,
                                        name TEXT NOT NULL,
                                        stripe_product_id TEXT,
                                        thumbnail_filenames TEXT,
                                        resource_type_id INTEGER,
                                        main_resource_category_id INTEGER,
                                        description TEXT,
                                        card_description TEXT,
                                        age_range TEXT,
                                        curriculum TEXT,
                                        feature_slot INTEGER,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                                      )"])
    
    ;; Insert test data
    (jdbc/execute! (:datasource db)
                   ["INSERT INTO resource (name, card_description) VALUES (?, ?)"
                    "Test Worksheet"
                    "A test worksheet for letters"])
    
    (binding [*db* db] 
      (f))
    
    ;; Clean up
    (jdbc/execute! (:datasource db) ["DROP TABLE resource"])))

(use-fixtures :once test-db-fixture)

(deftest get-all-resources-test
  (testing "get-all returns all resources"
    (let [resources (resource/get-all *db*)]
      (is (= 1 (count resources)))
      (is (= "Test Worksheet" (:name (first resources)))))))