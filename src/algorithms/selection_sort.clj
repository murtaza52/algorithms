(ns algorithms.selection-sort
  (:require [clojure.tools.trace :refer [trace]]))

;; Create a way to retrieve all mins in O(n) time.
(defn mins [coll]
  (reduce (fn[[min-coll rest-coll] val]
            (case (compare val (first min-coll))
              -1 [[val] (apply conj rest-coll min-coll)]
              0 [(conj min-coll val) rest-coll]
              1 [min-coll (conj rest-coll val)]))
          [[(first coll)] []]
          (rest coll)))
;; (mins [3 1 1 2]) => [[1 1] [3 2]]

(defn selection-sort [coll]
  (loop [[sorted coll] [[] coll]]
    (let [[s c] (mins coll)]
      (if-not (seq coll)
        sorted
        (recur [(concat sorted s) c])))))

(selection-sort [3 1 1 2 5 7 8 8 4 6])

(def selection-sort
  (lazy-cat (mins coll)))

;; (reduce (fn[vall acc] ) [] [:a [:a]])


(defn selection-sort
  [input]
  (let [ixs (vec (range (count input)))
        min-key-from (fn [acc ix] (apply min-key acc (subvec ixs ix)))
        swap (fn [coll i j] (assoc coll i (coll j) j (coll i)))]
    (reduce
     (fn [acc ix] (swap acc ix (min-key-from acc ix))) input ixs)))
