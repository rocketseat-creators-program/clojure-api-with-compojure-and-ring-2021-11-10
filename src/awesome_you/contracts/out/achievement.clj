(ns awesome-you.contracts.out.achievement
  (:require [schema.core :as s])
  (:import java.time.LocalDate))

(s/defschema Achievement
  {:id          s/Uuid
   :title       s/Str
   :description s/Str
   :date        LocalDate})
