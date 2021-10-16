(ns awesome-you.logic.achievement
  (:require [awesome-you.models.achievement :as m.achievement]
            [schema-generators.generators :as gen]
            [schema.core :as s])
  (:import java.time.LocalDate))

(s/defn creation :- m.achievement/Achievement
  [{input-date :achievement/date :as new} :- m.achievement/New
   as-of :- java.time.LocalDate]
  (let [date (or input-date as-of)
        id   (gen/generate s/Uuid)]
    (assoc new :achievement/date date
               :achievement/id id)))
