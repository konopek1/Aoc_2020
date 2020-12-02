(ns aoc2)
(require '[clojure.string :as str])


(def input (map #(Integer/parseInt %) (str/split (slurp "resources/input1.txt") #"\n")))