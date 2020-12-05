(ns aoc4)
(require '[clojure.string :as str])

(defn
  parse
  [line]
  (let [pairs (str/split line #"\s")]
    (map #(first (str/split % #":")) pairs)))

(def
  input
  (map parse (str/split (slurp "resources/input4.txt") #"\n\n")))


(def fields ["byr"
             "iyr"
             "eyr"
             "hgt"
             "hcl"
             "ecl"
             "pid"])

(defn
  pass?
  [passport]
  (every? #(not= (some #{%} passport) nil) fields))


(def result1 (count (filter #(= % true) (map pass? input))))
result1

(defn
  parse2
  [line]
  (let [pairs (str/split line #"\s")]
    (map #(str/split % #":") pairs)))

(def
  input2
  (map parse2 (str/split (slurp "resources/input4.txt") #"\n\n")))

(def fields2 {"byr" #(<= 1920 (Integer. %) 2002)
              "iyr" #(<= 2010 (Integer. %) 2020)
              "eyr" #(<= 2020 (Integer. %) 2030)
              "hgt" #(if (re-matches #"\d+cm" %)
                       (let [[v _] (str/split % #"[a-zA-Z]" 2)]
                         (<= 150 (Integer. v) 193))
                       (if (re-matches #"\d+in" %)
                         (let [[v _] (str/split % #"[a-zA-Z]" 2)]
                           (<= 59 (Integer. v) 76))
                         false))
              "hcl" #(re-matches #"^#[a-f\d]{6}$" %)
              "ecl" #(re-matches #"^(amb|blu|brn|gry|grn|hzl|oth)$" %)
              "pid" #(re-matches #"^\d{9}$" %)
              "cid" #(= % %)})

(defn get-fn
  [key] (get fields2 key #(not= % %)))

(defn
  extract-to-1
  [dict]
  (reduce #(conj % (first %2)) () dict))

(defn
  pass2?
  [[type value]]
  (if  ((get-fn type) value) true false))

(defn
  pass1-2?
  [passport]
  (if (and  (pass? (extract-to-1 passport)) (every? pass2? passport)) 1 0))

(apply + (map pass1-2?  input2))

