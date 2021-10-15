(ns awesome-you.handlers
  (:require [awesome-you.adapters.achievement :as a.achievement]
            [awesome-you.contracts.in.achievement :as in.achievement]
            [awesome-you.controllers.achievement :as c.achivement]
            [schema.core :as s]))

(s/defn new-achievement!
  [new :- in.achievement/Achievement
   db :- s/Any]
  (let [achievement (-> new
                        a.achievement/to-new-internal
                        (c.achivement/new! db)
                        a.achievement/from-internal)]
    {:status 201
     :body   achievement}))

(s/defn get-achievement
  [id :- s/Uuid
   db :- s/Any]
  (if-let [achievement (c.achivement/one id db)]
    {:status 200
     :body (a.achievement/from-internal achievement)}
    {:status 404
     :body "Not Found"}))
