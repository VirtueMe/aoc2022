(ns aoc2022.day06-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2022.day06 :refer [solution-06-01 solution-06-02]]))

(deftest test-solution-06-01-01
  (testing "Should find the correct sum 1"
    (is (= (solution-06-01 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 7))))

(deftest test-solution-06-01-02
  (testing "Should find the correct sum 2"
    (is (= (solution-06-01 "bvwbjplbgvbhsrlpgdmjqwftvncz") 5))))

(deftest test-solution-06-01-03
  (testing "Should find the correct sum 3"
    (is (= (solution-06-01 "nppdvjthqldpwncqszvftbrmjlhg") 6))))

(deftest test-solution-06-01-04
  (testing "Should find the correct sum 4"
    (is (= (solution-06-01 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 10))))

(deftest test-solution-06-01-05
  (testing "Should find the correct sum 5"
    (is (= (solution-06-01 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 11))))

(deftest test-solution-06-02-01
  (testing "Should find the correct sum 1"
    (is (= (solution-06-02 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 19))))

(deftest test-solution-06-02-02
  (testing "Should find the correct sum 2"
    (is (= (solution-06-02 "bvwbjplbgvbhsrlpgdmjqwftvncz") 23))))

(deftest test-solution-06-02-03
  (testing "Should find the correct sum 3"
    (is (= (solution-06-02 "nppdvjthqldpwncqszvftbrmjlhg") 23))))

(deftest test-solution-06-02-04
  (testing "Should find the correct sum 4"
    (is (= (solution-06-02 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 29))))

(deftest test-solution-06-02-05
  (testing "Should find the correct sum 5"
    (is (= (solution-06-02 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 26))))
