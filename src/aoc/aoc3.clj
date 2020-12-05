(ns aoc3)
(require '[clojure.string :as str])

(defn
  parse-line
  [line]
  {:trees (map-indexed (fn [idx item] (when (= item \#) idx)) line)
   :dots (map-indexed (fn [idx item] (when (= item \.) idx)) line)})

(def
  input
  (map parse-line (str/split (slurp "resources/input3.txt") #"\n")))

(def width (count (:trees (first input))))

(defn
  check-if-tree
  [row col path]
  (if (some #{(mod col width)}  (:trees (nth path row))) 1 0))


(defn traverse
  [input [col-step row-step]]
  (loop [path input row 0 col 0 trees 0]
    (if (<= (+ row row-step) (dec (count path)))
      (recur path (+ row row-step) (+ col col-step) (+ trees (check-if-tree row col path)))
      trees)))

(def slops `([1 1]
            [3 1]
            [5 1]
            [7 1]
            [1 2]))

(apply * (map #(traverse input %) slops))