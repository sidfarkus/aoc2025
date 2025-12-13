(ns advent-of-code.day-01
  (:require [clojure.string :as str]))

(defn next-rot [cur [dir num]] (mod (if (= dir "L") (- cur num) (+ cur num)) 100))

(defn num-zeros [cur [dir num]]
  (let [full-rots (quot num 100)
        partial-rot (next-rot cur [dir (mod num 100)])
        extra-rot (if (or (= partial-rot 0) (if (= dir "L") (> partial-rot cur) (< partial-rot cur))) 1 0)
        ]
    [(if (= cur 0) full-rots (+ full-rots extra-rot)) partial-rot]
    ))

(defn part-1
  "Day 01 Part 1"
  [input]
  (->> (str/split-lines input)
       (reduce (fn [acc line] (conj acc (next-rot (last acc) [(subs line 0 1) (read-string (subs line 1))]))) [50])
       (filter #(= %1 0))
       (count)
       )
  )

(defn part-2

  "Day 01 Part 2"
  [input]
  (->> (str/split-lines input)
       (reduce (fn [acc line] (let [[num rot] (num-zeros (last acc) [(subs line 0 1) (read-string (subs line 1))])] [(+ (first acc) num) rot])) [0 50])
       (first)
       )
  )
