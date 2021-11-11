(ns awesome.models.achievement
  (:require [schema.core :as s])
  (:import java.time.LocalDate))

(s/defschema NewAchievement
  {:achievement/title s/Str
   :achievement/description s/Str
   (s/optional-key :achievement/date) LocalDate})

(s/defschema Achievement
  {:achievement/id          s/Uuid
   :achievement/title       s/Str
   :achievement/description s/Str
   :achievement/date        LocalDate})
