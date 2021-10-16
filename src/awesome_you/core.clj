(ns awesome-you.core
  (:require [awesome-you.handlers :as handlers]
            [awesome-you.middlewares :as middlewares]
            [compojure.api.sweet :refer [api]]))

(def swagger-config
  {:ui   "/"
   :spec "/swagger.json"
   :data {:info {:title       "Awesome-you"
                 :description "Awesome-you apis"}
          :tags [{:name "api", :description "Awesome-you apis"}]}})

(defonce in-memory-db (atom {:achievements {}}))

(def app-config
  {:swagger swagger-config})

(def app
  (middlewares/with-db
    (api app-config handlers/api-routes)
    in-memory-db))

(comment
  (require '[ring.adapter.jetty :refer [run-jetty]])
  (def debug-app
    (run-jetty app {:join? false :port 8080}))
  (.stop debug-app))
