(ns awesome-you.controllers.achievement
  (:require [awesome-you.db.achievement :as db.achievement]
            [awesome-you.logic.achievement :as l.achievement]
            [awesome-you.models.achievement :as m.achievement]
            [schema.core :as s]))

(s/defn new! :- m.achievement/Achievement
  [new :- m.achievement/New
   as-of :- java.time.LocalDate
   db :- clojure.lang.Atom]
  (-> new
      (l.achievement/creation as-of)
      (db.achievement/upsert! db)))

(s/defn one :- (s/maybe m.achievement/Achievement)
  [id :- s/Uuid
   db :- clojure.lang.Atom]
  (db.achievement/one id db))

(s/defn all :- (s/maybe m.achievement/Achievement)
  [db :- clojure.lang.Atom]
  (db.achievement/all db))

(s/defn update! :- (s/maybe m.achievement/Achievement)
  [achievement :- m.achievement/Achievement
   db :- clojure.lang.Atom]
  (when (db.achievement/one (:achievement/id achievement) db)
   (db.achievement/upsert! achievement db)))

(s/defn delete! :- (s/maybe m.achievement/Achievement)
  [id :- s/Uuid
   db :- clojure.lang.Atom]
  (when-let [achievement (db.achievement/one id db)]
    (and (db.achievement/delete! id db)
         achievement)))
