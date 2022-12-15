(ns aoc2022.day08-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2022.day08 :refer [solution-08-01 solution-08-02]]))

(def ^:private input ["30373"
                      "25512"
                      "65332"
                      "33549"
                      "35390"])

(deftest test-solution-08-01
  (testing "Should find how many trees are visible from outside the grid"
    (is (= (solution-08-01 input) 21))))

(deftest test-solution-08-02
  (testing "Should find the highest scenic score possible for any tree"
    (is (= (solution-08-02 input) 8))))
