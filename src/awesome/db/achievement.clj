(ns awesome.db.achievement
  (:require [awesome.models.achievement :as m.achievement]
            [schema.core :as s]))

(s/defn one :- (s/maybe m.achievement/Achievement)
  [id :- s/Uuid
   db :- clojure.lang.Atom]
  (get-in @db [:achievements id]))

(s/defn upsert! :- m.achievement/Achievement
  [{id :achievement/id :as achievement} :- m.achievement/Achievement
   db :- clojure.lang.Atom]
  (-> db
      (swap! #(assoc-in % [:achievements id] achievement))
      (get-in [:achievements id])))

(s/defn delete! :- m.achievement/Achievement
  [id :- s/Uuid
   db :- clojure.lang.Atom]
  (let [achievement (one id db)]
    (-> db
        (swap! (fn[m] (update m :achievements #(dissoc % id)))))
    achievement))

(s/defn all :- (s/maybe m.achievement/Achievement)
  [db :- clojure.lang.Atom]
  (vals (get @db :achievements)))
