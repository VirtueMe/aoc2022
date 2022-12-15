(ns aoc2022.day07-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2022.day07 :refer [solution-07-01 solution-07-02]]))

(def ^:private input ["$ cd /"
                      "$ ls"
                      "dir a"
                      "14848514 b.txt"
                      "8504156 c.dat"
                      "dir d"
                      "$ cd a"
                      "$ ls"
                      "dir e"
                      "29116 f"
                      "2557 g"
                      "62596 h.lst"
                      "$ cd e"
                      "$ ls"
                      "584 i"
                      "$ cd .."
                      "$ cd .."
                      "$ cd d"
                      "$ ls"
                      "4060174 j"
                      "8033020 d.log"
                      "5626152 d.ext"
                      "7214296 k"])

(deftest test-solution-07-01
  (testing "Should find the sum of all folders below 100000"
    (is (= (solution-07-01 input) 95437))))

(deftest test-solution-07-02
  (testing "Should find the folder needed to free at least 30000000"
    (is (= (solution-07-02 input) 24933642))))

