(ns awesome.adapters.achievement
  (:require [awesome.contracts.achievement :as contracts.achievement]
            [awesome.models.achievement :as m.achievement]
            [schema.core :as s]))

(s/defn to-internal :- m.achievement/Achievement
  [{:keys [title description date]} :- contracts.achievement/NewAchievement]
  (conj {:achievement/title title
         :achievement/description description}
        (when date
          {:achievement/date date})))

(s/defn from-internal :- contracts.achievement/Achievement
  [{id          :achievement/id
    title       :achievement/title
    description :achievement/description
    date        :achievement/date} :- m.achievement/Achievement]
  {:id          id
   :title       title
   :description description
   :date        date})
