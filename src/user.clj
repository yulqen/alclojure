(ns user)

(defn load-dev
  "Load the development namespace and setup REPL helpers."
  []
  (println "Loading development environment...")
  (require 'dev)
  (in-ns 'dev)
  :loaded)

;; Helper function that delegates to the actual implementation
(defn go []
  (require 'dev)
  ((resolve 'dev/go)))

;; Helper function that delegates to the actual implementation
(defn stop []
  (require 'dev)
  ((resolve 'dev/stop)))

;; Helper function that delegates to the actual implementation
(defn restart []
  (require 'dev)
  ((resolve 'dev/restart)))

(println "Type (user/load-dev) to load the development environment.")