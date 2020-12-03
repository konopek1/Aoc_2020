(ns aoc2)
(require '[clojure.string :as str])

(defn 
  parse-line [line]
  (let [[times letters passwd] (str/split line #" ")]
    (let [[min max] (str/split times #"-")
         [letter] (first (str/split letters #":"))
         ]
      {:min (Integer/parseInt min) :max (Integer/parseInt max) :letter letter :passwd passwd}
      )
    )
  )

(def 
  input 
  (map parse-line (str/split (slurp "resources/input2.txt") #"\n"))) 

(defn pass?
  [record]
  (<=
   (:min record)
   (count  (filter #(= % (:letter record)) (:passwd record)))
   (:max record))
  )

(defn pass2?
  [record]   
    (count (filter #(= % (:letter record))
            [(nth (:passwd record) (dec (:min record)) nil) (nth (:passwd record) (dec (:max record)) nil)]))
  )

(count input)

(pass2? (parse-line "1-3 a: abcde"))

(count(filter #(= % true) (map pass? input)))

(count (filter #(= % 1) (map pass2? input)))
(map pass2? input)
