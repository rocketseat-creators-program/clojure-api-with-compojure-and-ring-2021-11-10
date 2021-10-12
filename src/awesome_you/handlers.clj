(ns awesome-you.handlers
  (:require [awesome-you.adapters.achievement :as a.achievement]
            [awesome-you.contracts.in.achievement :as in.achievement]
            [awesome-you.controllers.achievement :as c.achivement]
            [schema.core :as s]))

(s/defn new-achievement!
  [new :- in.achievement/Achievement]
  (let [achievement (-> new
                        a.achievement/to-new-internal
                        c.achivement/new!
                        a.achievement/from-internal)]
    {:status 201
     :body   achievement}))
