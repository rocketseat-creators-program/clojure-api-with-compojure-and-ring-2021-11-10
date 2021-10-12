(ns awesome-you.logic.achievement
  (:require [awesome-you.models.achievement :as m.achievement]
            [schema-generators.generators :as gen]
            [schema.core :as s])
  (:import java.time.LocalDate))

(s/defn creation :- m.achievement/Achievement
  [{input-date :achievement/date :as new} :- m.achievement/New]
  (let [date (or input-date (java.time.LocalDate/now))
        id   (gen/generate s/Uuid)]
    (-> new
        (select-keys [:achievement/title
                      :achievement/description])
        (assoc :achievement/date date
               :achievement/id id))))
