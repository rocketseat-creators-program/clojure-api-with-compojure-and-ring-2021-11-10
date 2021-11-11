(ns awesome.system
  (:require [compojure.api.sweet :refer [api]]
            [awesome.handler :refer [app-routes]]))

(def app-config {:swagger
                 {:ui "/"
                  :spec "/swagger.json"
                  :data {:info {:title "Awesome"
                                :description "Compojure Api example"}
                         :tags [{:name "api", :description "some apis"}]}}})

(def in-memory-db (atom {:achievements {}}))

(defn- wrap-db
  [handler db]
  (fn [request]
    (handler (assoc request
                    :db db))))

(def app (wrap-db (api app-config app-routes)
                  in-memory-db))

(comment
  (require '[ring.adapter.jetty :refer [run-jetty]])
  (def debug-app (run-jetty app {:port 8080 :join? false}))
  (.stop debug-app))
