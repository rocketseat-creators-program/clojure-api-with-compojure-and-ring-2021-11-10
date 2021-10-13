(ns awesome-you.core
  (:require [awesome-you.routes :as routes]
            [compojure.api.sweet :refer [api]]
            [integrant.core :as ig]))

(def swagger-config
  {:ui   "/"
   :spec "/swagger.json"
   :data {:info {:title       "Awesome-you"
                 :description "Awesome-you apis"}
          :tags [{:name "api", :description "Awesome-you apis"}]}})

(defonce in-memory-db (atom {:achievements {}}))

(defn with-db
  [handler db]
  (fn [request]
    (handler (assoc request :db db))))

(def app-config
  {:swagger swagger-config})

(def app
  (with-db
    (api app-config routes/api-routes)
    in-memory-db))

(comment
  (require '[ring.adapter.jetty :refer [run-jetty]])
  (def debug-app
    (run-jetty app {:join? false :port 8080}))
  (.stop debug-app))
