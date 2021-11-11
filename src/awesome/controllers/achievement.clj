(ns awesome.controllers.achievement
  (:require [schema.core :as s]
            [awesome.models.achievement :as m.achievement]
            [awesome.logic.achievement :as l.achievement]
            [awesome.db.achievement :as db.achievement])
  (:import java.time.LocalDate))

(s/defn new! :- m.achievement/Achievement
  [new-achievement :- m.achievement/NewAchievement
   as-of-date :- LocalDate
   db :- clojure.lang.Atom]
  (-> new-achievement
      (l.achievement/creation as-of-date)
      (db.achievement/upsert! db)))

(s/defn one :- m.achievement/Achievement
  [id :- s/Uuid
   db :- clojure.lang.Atom]
  (db.achievement/one id db))
