(ns aoc2022.day05
  "
   --- Day 5: Supply Stacks ---

   The expedition can depart as soon as the final supplies have been unloaded from the ships. Supplies are stored in stacks of marked crates, but because the needed supplies are buried under many other crates, the crates need to be rearranged.

   The ship has a giant cargo crane capable of moving crates between stacks. To ensure none of the crates get crushed or fall over, the crane operator will rearrange them in a series of carefully-planned steps. After the crates are rearranged, the desired crates will be at the top of each stack.

   The Elves don't want to interrupt the crane operator during this delicate procedure, but they forgot to ask her which crate will end up where, and they want to be ready to unload them as soon as possible so they can embark.
   
   They do, however, have a drawing of the starting stacks of crates and the rearrangement procedure (your puzzle input). For example:


       [D]    
       [N] [C]    
   [Z] [M] [P]
    1   2   3 

   move 1 from 2 to 1
   move 3 from 1 to 3
   move 2 from 2 to 1
   move 1 from 1 to 2

   In this example, there are three stacks of crates. Stack 1 contains two crates: crate Z is on the bottom, and crate N is on top. Stack 2 contains three crates; from bottom to top, they are crates M, C, and D. Finally, stack 3 contains a single crate, P.


   Then, the rearrangement procedure is given. In each step of the procedure, a quantity of crates is moved from one stack to a different stack. In the first step of the above rearrangement procedure, one crate is moved from stack 2 to stack 1, resulting in this configuration:

   [D]        
   [N] [C]    
   [Z] [M] [P]
    1   2   3 

   In the second step, three crates are moved from stack 1 to stack 3. Crates are moved one at a time, so the first crate to be moved (D) ends up below the second and third crates:

           [Z]
           [N]
       [C] [D]
       [M] [P]
    1   2   3
   
   Then, both crates are moved from stack 2 to stack 1. Again, because crates are moved one at a time, crate C ends up below crate M:

           [Z]
           [N]
   [M]     [D]
   [C]     [P]
    1   2   3

   Finally, one crate is moved from stack 1 to stack 2:
   
           [Z]
           [N]
           [D]
   [C] [M] [P]
    1   2   3

   The Elves just need to know which crate will end up on top of each stack; in this example, the top crates are C in stack 1, M in stack 2, and Z in stack 3, so you should combine these together and give the Elves the message CMZ.

   After the rearrangement procedure completes, what crate ends up on top of each stack?"
  (:gen-class)
  (:require [aoc2022.core :refer [get-lines]]
            [clojure.string :refer [trim]]))

(def ^:private input (get-lines "resources/05.txt"))

(defn format-moves
  [input]
  (let [move-regex #"move (?<count>[\d]+) from (?<from>[\d]+) to (?<to>[\d]+)"]
    (let [matcher (re-matcher move-regex input)]
      (if (.matches matcher)
        {:count (Integer. (.group matcher "count")) :from (.group matcher "from") :to (.group matcher "to")}
        nil))))

(defn make-element
  [item]
  ; (println (nth item))
  (if (= item "")
    nil
    (str (nth item 1))))

(defn make-line
  [line]
  ; (println line (nth (partition 4 line) 2))
  (map make-element (map trim (map #(apply str %) (partition 4 line)))))

(defn create-board
  [rows blocks]
  (reduce #(merge-with concat %1 %2) {} (map #(zipmap rows %) blocks)))

(defn make-board
  [[board _ moves]]
  (let [items (take (dec (count board)) board) container (map trim (map #(apply str %) (partition 4 (last board))))]
    {:board (create-board container (map make-line items)) :moves (map format-moves moves)}))

(defn crane-9000
  [count input]
  (take count input))

(defn crane-9001
  [count input]
  (reverse (crane-9000 count input)))

(defn do-move
  [board input method]
  ; (println board)
  ; (println input)
  (-> board
      (update-in [(input :from)] #(drop (input :count) %))
      (update-in [(input :to)] into (method (input :count) (board (input :from))))))

(defn move-stacks
  [method stacks]
  (loop [board (stacks :board) moves (stacks :moves)]
    (if (empty? moves)
      board
      (recur (do-move board (first moves) method) (rest moves)))))

(defn get-message
  [stacks]
  ; (println stacks)
  (->> stacks
       (into (sorted-map))
       (vals)
       (map first)
       (apply str)))

(defn solution-05-01
  ([] (solution-05-01 input))
  ([input]
  (->> input
       (partition-by empty?)
       (make-board)
       (move-stacks crane-9000)
       (get-message))))

(defn solution-05-02
  ([] (solution-05-02 input))
  ([input]
  (->> input
       (partition-by empty?)
       (make-board)
       (move-stacks crane-9001)
       (get-message))))