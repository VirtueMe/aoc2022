(ns aoc2022.day08
  "
   --- Day 8: Treetop Tree House ---
   
   The expedition comes across a peculiar patch of tall trees all planted carefully in a grid. The Elves explain that a previous expedition planted these trees as a reforestation effort. Now, they're curious if this would be a good location for a tree house.

   First, determine whether there is enough tree cover here to keep a tree house hidden. To do this, you need to count the number of trees that are visible from outside the grid when looking directly along a row or column.

   The Elves have already launched a quadcopter to generate a map with the height of each tree (your puzzle input). For example:

   30373
   25512
   65332
   33549
   35390

   Each tree is represented as a single digit whose value is its height, where 0 is the shortest and 9 is the tallest.

   A tree is visible if all of the other trees between it and an edge of the grid are shorter than it. Only consider trees in the same row or column; that is, only look up, down, left, or right from any given tree.

   All of the trees around the edge of the grid are visible - since they are already on the edge, there are no trees to block the view. In this example, that only leaves the interior nine trees to consider:

   - The top-left 5 is visible from the left and top. (It isn't visible from the right or bottom since other trees of height 5 are in the way.)
   - The top-middle 5 is visible from the top and right.
   - The top-right 1 is not visible from any direction; for it to be visible, there would need to only be trees of height 0 between it and an edge.
   - The left-middle 5 is visible, but only from the right.
   - The center 3 is not visible from any direction; for it to be visible, there would need to be only trees of at most height 2 between it and an edge.
   - The right-middle 3 is visible from the right.
   - In the bottom row, the middle 5 is visible, but the 3 and 4 are not.

   With 16 trees visible on the edge and another 5 visible in the interior, a total of 21 trees are visible in this arrangement.

   Consider your map; how many trees are visible from outside the grid?"
  (:gen-class)
  (:require [aoc2022.core :refer [get-lines]]))

(def ^:private input (get-lines "resources/08.txt"))

(def ^:private left [0 -1])
(def ^:private right [0 1])
(def ^:private up [-1 0])
(def ^:private down [1 0])

(def ^:private directions [left right up down])

(defn parse
  [input]
  (->> input
       (mapv seq)
       (mapv #(mapv str %))
       (mapv #(mapv read-string %))))

(defn check-direction
  [input current direction]
  (let [value (get-in input current)]
    (loop [[y x] (mapv + current direction)]
      (if (or (< x 0) (< y 0) (= x (count (input 0))) (= y (count input)))
        true
        (if (>= (get-in input [y x]) value)
          false
          (recur (mapv + [y x] direction)))))))

(defn traverse
  [input]
  (let [height (count input) length (count (first input))]
    (loop [items (for [y (range height) x (range length)] [y x]) hits []]
      (if (empty? items)
        hits
        (let [current (first items) hit (some true? (map #(check-direction input current %) directions))]
          (recur (rest items) (if (true? hit) (conj hits current) hits)))))))

(defn find-visible-trees
  [input]
  (->> input
       (parse)
       (traverse)
       (count)))

(defn count-direction
  [input current direction]
  (let [value (get-in input current)]
    (loop [[y x] (mapv + current direction) total 0]
      (if (or (< x 0) (< y 0) (= x (count (input 0))) (= y (count input)))
        total
        (if (>= (get-in input [y x]) value)
          (inc total)
          (recur (mapv + [y x] direction) (inc total)))))))

(defn traverse-scenic
  [input]
  (let [height (count input) length (count (first input))]
    (loop [items (for [y (range height) x (range length)] [y x]) hits []]
      (if (empty? items)
        hits
        (let [current (first items) scenic (map #(count-direction input current %) directions)]
          (recur (rest items) (conj hits (apply * scenic))))))))

(defn find-highest-scenic-score
  [input]
  (->> input
       (parse)
       (traverse-scenic)
       (apply max)))

(defn solution-08-01
  "Find how many trees are visible from outside the grid"
  ([] (solution-08-01 input))
  ([input] (find-visible-trees input)))

(defn solution-08-02
  "Find the highest scenic score possible for any tree"
  ([] (solution-08-02 input))
  ([input] (find-highest-scenic-score input)))