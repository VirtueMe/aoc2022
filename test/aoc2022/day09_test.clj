(ns aoc2022.day09-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2022.day09 :refer [solution-09-01 solution-09-02 move]]))


(def ^:private input ["R 4"
                      "U 4"
                      "L 3"
                      "D 1"
                      "R 4"
                      "D 1"
                      "L 5"
                      "R 2"])

(def ^:private big-input ["R 5"
                          "U 8"
                          "L 8"
                          "D 3"
                          "R 17"
                          "D 10"
                          "L 25"
                          "U 20"])

(deftest test-move-overlapped
  (testing "Should be able to move correct when overlapped"
    (is (= (move [[0 0] [0 0]] [0 1]) [[0 1] [0 0]]))))

(deftest test-move-side-by-side
  (testing "Should be able to move correct when side by side"
    (is (= (move [[0 1] [0 0]] [0 1]) [[0 2] [0 1]]))))

(deftest test-move-corner
  (testing "Should be able to move correct when corner-piece"
    (is (= (move [[-1 1] [0 0]] [0 1]) [[-1 2] [-1 1]]))))

(deftest test-move-to-corner 
  (testing "Should be able to move to a corner connection"
    (is (= (move [[0 4] [0 3]] [-1 0]) [[-1 4] [0 3]]))))

(deftest test-solution-09-01
  (testing "Should find how many positions does the tail of the rope visit at least once"
    (is (= (solution-09-01 input) 13))))

(deftest test-solution-09-02
  (testing "Should find how many positions does the tail of the longer rope visit at least once"
    (is (= (solution-09-02 big-input) 36))))