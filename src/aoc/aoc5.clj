(ns aoc.aoc5)
(require '[clojure.string :as str])

(defn
  parse-row
  [row]
  (if (= row \F) 1 0))

(defn
  parse-col
  [col]
  (if (= col \R) 0 1))

(defn
  parse-line
  [line]
  (let [[rows cols] (str/split line #"(?=(R|L))" 2)]
    [(map parse-row rows)
     (map parse-col cols)]))

(def
  input
  (map parse-line (str/split (slurp "resources/input5.txt") #"\n")))


(defn
  binary-move
  "accepts list of [1,0] if 1 then upper scoper if 0 then lower scope"
  [path row-count]
  (loop [[letter & left] path start 0 end row-count]
    (if
     (not= nil letter)
      (recur
       left
       (if (= letter 0) (+ (Math/ceil  (/ (- end start) 2)) start)  start)
       (if (= letter 1) (-  end   (Math/ceil (/ (- end start) 2) )) end))
    end)))

(defn
  find-id
  [[row col]]   
  (+
   (* 8 (binary-move row 127))
   (binary-move col 7)))

(binary-move(second (parse-line "BFFFBBFRRR")) 7)

(apply max (map find-id input))

(def seats (map find-id input))
(def all-seats (range (apply min seats) (apply max seats)))

(defn
  in
  "Check if element is in list"
  [list ele]
   (some #{ele} list))

(def not-in (complement in))

(filter #( not-in seats % ) all-seats)