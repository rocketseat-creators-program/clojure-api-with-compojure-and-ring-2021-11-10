(ns awesome-you.handlers
  (:require [awesome-you.contracts.in.achievement :as in.achievement]
            [awesome-you.contracts.out.achievement :as out.achievement]
            [awesome-you.adapters.achievement :as a.achievement]
            [awesome-you.controllers.achievement :as c.achievement]
            [awesome-you.handlers :as handle]
            [compojure.api.sweet :refer [GET POST PUT context]]
            [ring.util.http-response :refer [ok created not-found]]
            [schema.core :as s])
  (:import java.time.LocalDate))

(defn resolve-route
    [route params]
    (reduce (fn [route [k v]]
              (clojure.string/replace route
                                      (re-pattern (str k))
                                      (str v)))
            route
            params))

(s/defn new-achievement!
  [new :- in.achievement/New
   location-route :- s/Str
   db :- s/Any]
  (let [achievement (-> new
                      a.achievement/to-new-internal
                      (c.achievement/new! db)
                      a.achievement/from-internal)]
    (created (resolve-route location-route
                            {:id (:id achievement)})
             achievement)))

(s/defn get-achievement
  [id :- s/Uuid
   db :- s/Any]
  (if-let [achievement (c.achievement/one id db)]
    (ok (a.achievement/from-internal achievement))
    (not-found)))

(s/defn update-achievement!
  [achievement :- in.achievement/Achievement
   db :- s/Any]
  (if-let [achievement (c.achievement/update! (a.achievement/to-internal achievement) db)]
    (ok (a.achievement/from-internal achievement))
    (not-found)))

(def api-routes
  (context "/api" []
    (POST "/achievements" {db :db}
      :return out.achievement/Achievement
      :body [achievement in.achievement/New]
      :summary "Adds a new achievement"
      (new-achievement! achievement "/api/achievements/:id" db))
    (GET "/achievements/:id" {db :db}
      :return out.achievement/Achievement
      :summary "Returns achievement with id"
      :path-params [id :- s/Uuid]
      (get-achievement id db))
    (PUT "/achievements/:id" {db :db}
      :return out.achievement/Achievement
      :body [achievement in.achievement/Achievement]
      :path-params [id :- s/Uuid]
      :summary "Updates achievement with id"
      (update-achievement! achievement db))))
