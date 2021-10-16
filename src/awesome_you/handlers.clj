(ns awesome-you.handlers
  (:require [awesome-you.adapters.achievement :as a.achievement]
            [awesome-you.contracts.in.achievement :as in.achievement]
            [awesome-you.contracts.out.achievement :as out.achievement]
            [awesome-you.controllers.achievement :as c.achievement]
            [awesome-you.middlewares :as middlewares]
            [compojure.api.sweet :refer [context DELETE GET POST PUT]]
            [ring.util.http-response :refer [created not-found ok]]
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
   as-of :- java.time.LocalDate
   db :- clojure.lang.Atom]
  (let [achievement (-> new
                      a.achievement/to-new-internal
                      (c.achievement/new! as-of db)
                      a.achievement/from-internal)]
    (created (resolve-route location-route
                            {:id (:id achievement)})
             achievement)))

(s/defn get-achievement
  [id :- s/Uuid
   db :- clojure.lang.Atom]
  (if-let [achievement (c.achievement/one id db)]
    (ok (a.achievement/from-internal achievement))
    (not-found)))

(s/defn list-achievements
  [db :- clojure.lang.Atom]
  (ok (map a.achievement/from-internal (c.achievement/all db))))

(s/defn update-achievement!
  [achievement :- in.achievement/Achievement
   db :- clojure.lang.Atom]
  (if-let [achievement (c.achievement/update! (a.achievement/to-internal achievement) db)]
    (ok (a.achievement/from-internal achievement))
    (not-found)))

(s/defn delete-achievement!
  [id :- s/Uuid
   db :- clojure.lang.Atom]
  (if-let [achievement (c.achievement/delete! id db)]
    (ok (a.achievement/from-internal achievement))
    (not-found)))

(def api-routes
  (context "/api" []
    (POST "/achievements" {:keys [db as-of]}
      :middleware [middlewares/with-as-of]
      :return out.achievement/Achievement
      :body [achievement in.achievement/New]
      :summary "Adds a new achievement"
      (new-achievement! achievement "/api/achievements/:id" as-of db))
    (GET "/achievements" {db :db}
      :return [out.achievement/Achievement]
      :summary "Returns all achievements"
      (list-achievements db))
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
      (update-achievement! achievement db))
    (DELETE "/achievements/:id" {db :db}
      :return out.achievement/Achievement
      :path-params [id :- s/Uuid]
      :summary "Removes achievement with id"
      (delete-achievement! id db))))
