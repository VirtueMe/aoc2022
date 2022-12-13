(ns aoc2022.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc2022.core :refer [split-words]]))

(deftest split-words-test
  (testing "Should split words correctly"
    (is (= (split-words "Hello test") ["Hello", "test"]))))

