(ns awesome-you.controllers.achievement
  (:require [awesome-you.models.achievement :as m.achievement]
            [awesome-you.logic.achievement :as l.achievement]
            [awesome-you.db.achievement :as db.achievement]
            [schema.core :as s]))

(s/defn new! :- m.achievement/Achievement
  [new :- m.achievement/New
   db :- s/Any]
  (-> new
      l.achievement/creation
      (db.achievement/upsert! db)))

(s/defn one :- (s/maybe m.achievement/Achievement)
  [id :- s/Uuid
   db :- s/Any]
  (db.achievement/one id db))
