(ns aoc2022.day04-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2022.day04 :refer [map-elements solution-04-01 solution-04-02]]))

(def input ["2-4,6-8"
            "2-3,4-5"
            "5-7,7-9"
            "2-8,3-7"
            "6-6,4-6"
            "2-6,4-8"])

(deftest test-map-elements
  (testing "Should split correct"
    (is (= (map-elements (first input)) '((2 4) (6 8))))))

(deftest test-solution-04-01
  (testing "Should find 2 items that overlap in the input"
    (is (= (solution-04-01 input) 2))))

(deftest test-solution-04-02
  (testing "Should find 4 items that overlap in the input"
    (is (= (solution-04-02 input) 4))))