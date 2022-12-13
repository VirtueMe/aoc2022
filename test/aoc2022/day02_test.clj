(ns aoc2022.day02-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2022.day02 :refer [evalute calculate-score evalute-2]]))

(def input [["A" "Y"]
            ["B" "X"]
            ["C" "Z"]])

(deftest test-evalute
  (testing "Should calculate the correct sum of fight"
    (is (= (evalute (first input)) 8))))

(deftest test-calculate-score
  (testing "Should calculate the correct sum of all fights"
    (is (= (calculate-score input) 15))))

(deftest test-calculate-score-2
  (testing "Should calculate the correct sum of all fights with second strategy"
    (is (= (calculate-score input evalute-2) 12))))