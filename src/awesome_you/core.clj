(ns awesome-you.core
  (:require [awesome-you.routes :as routes]
            [compojure.api.sweet :refer [api]]))

(def swagger-config
  {:ui   "/"
   :spec "/swagger.json"
   :data {:info {:title       "Awesome-you"
                 :description "Awesome-you apis"}
          :tags [{:name "api", :description "Awesome-you apis"}]}})

(def app-config
  {:swagger swagger-config})

(def app
  (api app-config routes/api-routes))

(comment
  (require '[ring.adapter.jetty :refer [run-jetty]])
  (def debug-app
    (run-jetty app {:join? false :port 8080}))
  (.stop debug-app))
