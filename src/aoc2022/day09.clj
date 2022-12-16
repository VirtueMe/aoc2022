(ns aoc2022.day09
  "
   --- Day 9: Rope Bridge ---
   
   This rope bridge creaks as you walk along it. You aren't sure how old it is, or whether it can even support your weight.

   It seems to support the Elves just fine, though. The bridge spans a gorge which was carved out by the massive river far below you.

   You step carefully; as you do, the ropes stretch and twist. You decide to distract yourself by modeling rope physics; maybe you can even figure out where not to step.

   Consider a rope with a knot at each end; these knots mark the head and the tail of the rope. If the head moves far enough away from the tail, the tail is pulled toward the head.

   Due to nebulous reasoning involving Planck lengths, you should be able to model the positions of the knots on a two-dimensional grid. Then, by following a hypothetical series of motions (your puzzle input) for the head, you can determine how the tail will move.

   Due to the aforementioned Planck lengths, the rope must be quite short; in fact, the head (H) and tail (T) must always be touching (diagonally adjacent and even overlapping both count as touching):

   ....
   .TH.
   ....

   ....
   .H..
   ..T.
   ....


   ...
   .H. (H covers T)
   ...

   If the head is ever two steps directly up, down, left, or right from the tail, the tail must also move one step in that direction so it remains close enough:

   .....    .....    .....
   .TH.. -> .T.H. -> ..TH.
   .....    .....    .....

   ...    ...    ...
   .T.    .T.    ...
   .H. -> ... -> .T.
   ...    .H.    .H.
   ...    ...    ...

   Otherwise, if the head and tail aren't touching and aren't in the same row or column, the tail always moves one step diagonally to keep up:

   .....    .....    .....
   .....    ..H..    ..H..
   ..H.. -> ..... -> ..T..
   .T...    .T...    .....
   .....    .....    .....

   .....    .....    .....
   .....    .....    .....
   ..H.. -> ...H. -> ..TH.
   .T...    .T...    .....
   .....    .....    .....

   You just need to work out where the tail goes as the head follows a series of motions. Assume the head and the tail both start at the same position, overlapping.

   For example:

   R 4
   U 4
   L 3
   D 1
   R 4
   D 1
   L 5
   R 2

   This series of motions moves the head right four steps, then up four steps, then left three steps, then down one step, and so on. After each step, you'll need to update the position of the tail if the step means the head is no longer adjacent to the tail. Visually, these motions occur as follows (s marks the starting position as a reference point)
   
   After simulating the rope, you can count up all of the positions the tail visited at least once. In this diagram, s again marks the starting position (which the tail also visited) and # marks other positions the tail visited:

   ..##..
   ...##.
   .####.
   ....#.
   s###..

   So, there are 13 positions the tail visited at least once.

   Simulate your complete hypothetical series of motions. How many positions does the tail of the rope visit at least once?
   "
(:gen-class)
  (:require [aoc2022.core :refer [get-lines split-words]]))

(def ^:private input (get-lines "resources/09.txt"))

(def ^:private steps {"R" [0 1]
                      "L" [0 -1]
                      "D" [1 0]
                      "U" [-1 0]})

(defn parse
  [input]
  (->> input
       (map #(split-words % #" "))
       (map #(vector (first %) (read-string (second %))))))

(defn find-step
  [head tail]
  (let [[y x] (mapv - head tail)]
    ; (println "find-step: " head tail y x)
    (if (and (>= 1 (Math/abs y)) (>= 1 (Math/abs x)))
      [0 0]
      (if (= x 0)
        [(if (< (first tail) (first head)) 1 -1) x]
        (if (= y 0)
          [y (if (> (second tail) (second head)) -1 1)]
          [(if (> (first tail) (first head)) -1 1) (if (> (second tail) (second head)) -1 1)])))))

(defn move
  [rope step]
  (loop [nexthead (mapv + (first rope) step) items (rest rope) newrope [nexthead]]
    (if (empty? items)
      newrope
      (let [knot (first items) nextknot (mapv + knot (find-step nexthead knot))]
        (recur nextknot (rest items) (conj newrope nextknot))))))

(defn traverse
  [length input]
  (loop [items (apply concat (map #(repeat (second %) (steps (first %))) input)) rope (vec (repeat length [0 0])) visited #{[0 0]}]
    (if (empty? items)
      (set visited)
      (let [result (move rope (first items))]
        (recur (rest items) result (conj visited (last result)))))))

(defn calculate-visited
  [input length]
  (->> input
     (parse)
     (traverse length)
     (count)))

(defn solution-09-01
  "Find how many positions does the tail of the rope visit at least once"
  ([] (solution-09-01 input))
  ([input] (calculate-visited input 2)))

(defn solution-09-02
  "Find how many positions does the tail of the longer rope visit at least once"
  ([] (solution-09-02 input))
  ([input] (calculate-visited input 10)))