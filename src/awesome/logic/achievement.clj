(ns awesome.logic.achievement
  (:require [schema.core :as s]
            [awesome.models.achievement :as m.achievement]
            [schema-generators.generators :as gen])
  (:import java.time.LocalDate))

(s/defn creation :- m.achievement/Achievement
  [new-achievement :- m.achievement/NewAchievement
   as-of-date :- LocalDate]
  (let [id (gen/generate s/Uuid)
        date (or (:achievement/date new-achievement)
                 as-of-date)]
    (assoc new-achievement
           :achievement/id id
           :achievement/date date)))
