(ns aoc2022.day03-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2022.day03 :refer [value split-compartments find-common-item calculate solution-03-01 solution-03-02]]))

(def ^:private input ["vJrwpWtwJgWrhcsFMMfFFhFp"
                      "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                      "PmmdzqPrVvPwwTWBwg"
                      "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                      "ttgJtRGJQctTZtZT"
                      "CrZsJsPPZsGzwwsLwLmpwMDw"])

(deftest test-value-A
  (testing "Should calculate the correct of a uppercase letter"
    (is (= (value \A) 27))))

(deftest test-value-a
  (testing "Should calculate the correct of a lowercase letter"
    (is (= (value \a) 1))))

(deftest test-split-compartments
  (testing "Should be able to split compartments in the middle"
    (is (= (count (split-compartments (first input))) 2))))

(deftest test-find-common-item
  (testing "Should be able to find the common item in the compartments"
    (is (= (first (find-common-item (split-compartments (first input)))) \p))))

(deftest test-should-calculate-value-correct
  (testing "Should be able to calculate the values of common items in the compartments"
    (is (= (calculate (find-common-item (split-compartments (first input)))) 16))))

(deftest test-solution-03-01
  (testing "Should find the total value of common items in the elfes sacks"
    (is (= (solution-03-01 input) 157))))

(deftest test-solution-03-02
  (testing "Should find the total value of common items in the group elfes sacks"
    (is (= (solution-03-02 input) 70))))