(ns aoc2022.day01-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2022.day01 :refer [split-it find-most-calories find-top-three-calories]]))

(def input (split-it ["1000"
                      "2000"
                      "3000"
                      ""
                      "4000"
                      ""
                      "5000"
                      "6000"
                      ""
                      "7000"
                      "8000"
                      "9000"
                      ""
                      "10000"]))

(deftest test-split-it
  (testing "Should return correct formated array"
    (is (= (count input) 5))))

(deftest test-find-most-calories
  (testing "Should be able to find the elf that has the most calories with him"
    (is (= (find-most-calories input) 24000))))

(deftest test-find-most-calories
  (testing "Should be able to find the 3 elfes that has the most calories with them"
    (is (= (find-top-three-calories input) 45000))))

