(ns awesome-you.contracts.in.achievement
  (:require [schema.core :as s])
  (:import java.time.LocalDate))

(s/defschema Achievement
  {:title                 s/Str
   :description           s/Str
   (s/optional-key :date) LocalDate})
