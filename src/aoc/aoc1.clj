(require '[clojure.string :as str])
(def year 2020)
(def input (map #(Integer/parseInt %) (str/split (slurp "resources/input1.txt") #"\n")))

(defn aoc1
  "Aoc 1"
  [input offset]
  (let [minused (map #(- % offset) input)]
    (take 2 (filter #(some (partial = (* -1 %)) minused) input))))

(aoc1 input year)

(apply *
 (first
 (for [x input  y input z input :when (= 2020 (+ x y z))] [x y z])))



