(ns awesome-you.routes
  (:require [awesome-you.contracts.in.achievement :as in.achievement]
            [awesome-you.contracts.out.achievement :as out.achievement]
            [awesome-you.handlers :as handle]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s])
  (:import java.time.LocalDate))

(def api-routes
  (context "/api" []
    :tags ["api"]
    (POST "/achievements" []
      :return out.achievement/Achievement
      :body [achievement in.achievement/Achievement]
      :responses {201 {:schema out.achievement/Achievement}}
      :summary "Adds a new achievement"
      (handle/new-achievement! achievement))))
