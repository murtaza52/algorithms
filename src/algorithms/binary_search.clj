(ns algorithms.binary-search
  (:require [clojure.tools.trace :refer [trace]]))

;; Creating a seq of integers. 
(def int-vec #(take % (range)))


(def map-vec (fn[n](take n (iterate #(update-in % [:id] inc) {:id 1}))))
(def map-vec (fn[n](take n (map #(assoc {} :id %) (range)))))

(map-vec 3)

(def keyfn-map :id)

(defn binary-search
  ([coll v]
   (binary-search coll identity v))
  ([coll keyfn v]
   (loop [coll2 coll]
     (if (<= (count coll2) 1)
       (first coll2)
       (let [[lower upper] (split-at (/ (count coll2) 2) coll2)]
         (if (<= (keyfn v) (keyfn (last lower)))
           (recur lower)
           (recur upper)))))))

(time (binary-search (int-vec 10000) 652))
(time (binary-search (map-vec 10000) keyfn-map {:id 87}))
