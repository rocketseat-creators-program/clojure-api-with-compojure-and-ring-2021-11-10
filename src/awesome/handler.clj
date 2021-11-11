(ns awesome.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [awesome.adapters.achievement :as a.achievement]
            [awesome.controllers.achievement :as c.achievement]
            [awesome.contracts.achievement :as contracts.achievement]))

(defn new!
  [new-achievement as-of-date db]
  (-> new-achievement
      a.achievement/to-internal
      (c.achievement/new! as-of-date db)
      a.achievement/from-internal
      ok))

(defn one
  [id db]
  (-> id
      (c.achievement/one db)
      a.achievement/from-internal
      ok))

(defn- wrap-as-of-date
  [handler]
  (fn [request]
    (handler (assoc request
                    :as-of-date (java.time.LocalDate/now)))))

(def app-routes
  (context "/api" []
      :tags ["api"]

      (GET "/achievement/:id" {db :db}
       :return contracts.achievement/Achievement
       :path-params [id :- s/Uuid]
       :summary "return achievement with id"
       (one id db))

      (POST "/achievements" {db :db
                             as-of-date :as-of-date}
        :middleware [wrap-as-of-date]
        :return contracts.achievement/Achievement
        :body [new-achievement contracts.achievement/NewAchievement]
        :summary "add new achievement"
        (new! new-achievement as-of-date db))))

(comment
  (context "/api" []
    :tags ["api"]

    (GET "/plus" []
      :return {:result Long}
      :query-params [x :- Long, y :- Long]
      :summary "adds two numbers together"
      (ok {:result (+ x y)}))

    (POST "/echo" []
      :return Pizza
      :body [pizza Pizza]
      :summary "echoes a Pizza"
      (ok pizza))))
