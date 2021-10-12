(ns awesome-you.controllers.achievement
  (:require [awesome-you.models.achievement :as m.achievement]
            [awesome-you.logic.achievement :as l.achievement]
            [schema.core :as s]))

(s/defn new! :- m.achievement/Achievement
  [new :- m.achievement/New]
  (l.achievement/creation new))
