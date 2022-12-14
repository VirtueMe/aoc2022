(ns aoc2022.day05-test 
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2022.day05 :refer [solution-05-01 solution-05-02]]))

(def ^:private input ["    [D]     "
                      "[N] [C]     "
                      "[Z] [M] [P] "
                      " 1   2   3  "
                      ""
                      "move 1 from 2 to 1"
                      "move 3 from 1 to 3"
                      "move 2 from 2 to 1"
                      "move 1 from 1 to 2"])

(deftest test-solution-05-01
  (testing "Should find the correct word"
    (is (= (solution-05-01 input) "CMZ"))))

(deftest test-solution-05-01-given
  (testing "SHould find the correct word for the real-input"
    (is (= (solution-05-01) "SHQWSRBDL"))))

(deftest test-solution-05-02
  (testing "Should find the correct word with the new crane"
    (is (= (solution-05-02 input) "MCD"))))

(deftest test-solution-05-02-given
  (testing "Should find the correct word with the new crane"
    (is (= (solution-05-02) "CDTQZHBRS"))))


