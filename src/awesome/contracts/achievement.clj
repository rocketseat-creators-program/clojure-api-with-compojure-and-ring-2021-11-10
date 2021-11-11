(ns awesome.contracts.achievement
  (:require [schema.core :as s])
  (:import java.time.LocalDate))

(s/defschema NewAchievement
  {:title s/Str
   :description s/Str
   (s/optional-key :date) LocalDate})

(s/defschema Achievement
  {:id          s/Uuid
   :title       s/Str
   :description s/Str
   :date        LocalDate})
