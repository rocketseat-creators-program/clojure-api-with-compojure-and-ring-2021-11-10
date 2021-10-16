(ns awesome-you.middlewares)

(defn with-db
  [handler db]
  (fn [request]
    (handler (assoc request :db db))))

(defn with-as-of
  [handler]
  (fn [request]
    (handler (assoc request :as-of (java.time.LocalDate/now)))))
