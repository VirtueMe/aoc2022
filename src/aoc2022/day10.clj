(ns aoc2022.day10
  "
   --- Day 10: Cathode-Ray Tube ---
   
   You avoid the ropes, plunge into the river, and swim to shore.

   The Elves yell something about meeting back up with them upriver, but the river is too loud to tell exactly what they're saying. They finish crossing the bridge and disappear from view.

   Situations like this must be why the Elves prioritized getting the communication system on your handheld device working. You pull it out of your pack, but the amount of water slowly draining from a big crack in its screen tells you it probably won't be of much immediate use.

   Unless, that is, you can design a replacement for the device's video system! It seems to be some kind of cathode-ray tube screen and simple CPU that are both driven by a precise clock circuit. The clock circuit ticks at a constant rate; each tick is called a cycle.

   Start by figuring out the signal being sent by the CPU. The CPU has a single register, X, which starts with the value 1. It supports only two instructions:

   - addx V takes two cycles to complete. After two cycles, the X register is increased by the value V. (V can be negative.)
   - noop takes one cycle to complete. It has no other effect.

   The CPU uses these instructions in a program (your puzzle input) to, somehow, tell the screen what to draw.

   Consider the following small program:

   noop
   addx 3
   addx -5

   Execution of this program proceeds as follows:

   - At the start of the first cycle, the noop instruction begins execution. During the first cycle, X is 1. After the first cycle, the noop instruction finishes execution, doing nothing.
   - At the start of the second cycle, the addx 3 instruction begins execution. During the second cycle, X is still 1.
   - During the third cycle, X is still 1. After the third cycle, the addx 3 instruction finishes execution, setting X to 4.
   - At the start of the fourth cycle, the addx -5 instruction begins execution. During the fourth cycle, X is still 4.
   - During the fifth cycle, X is still 4. After the fifth cycle, the addx -5 instruction finishes execution, setting X to -1.
   
   Maybe you can learn something by looking at the value of the X register throughout execution. For now, consider the signal strength (the cycle number multiplied by the value of the X register) during the 20th cycle and every 40 cycles after that (that is, during the 20th, 60th, 100th, 140th, 180th, and 220th cycles)."
  (:gen-class)
  (:require [aoc2022.core :refer [get-lines split-words]]
            [clojure.string :refer [join]]))

(def ^:private input (get-lines "resources/10.txt"))

(defn noop
  [input]
  (conj input 0))

(defn addx
  [input value]
  (-> input
      (conj 0)
      (conj value)))

(def ^:private cycles
  [20, 60, 100, 140 180 220])

(defn calculate
  [cycle input]
  (let [x  (apply + (take cycle input))]
    ; (println cycle x)
    (*  x cycle)))

(defn calculate-cycles
  [input]
  (map #(calculate % input) cycles))

(defn parse
  [input]
  (loop [items input values [1]]
    (if (empty? items)
      values
      (let [item (first items) instruction (split-words item #" ") operation (ns-resolve 'aoc2022.day10 (symbol (first instruction)))]
        (recur (rest items) (if (= (count instruction) 2) (operation values  (read-string (second instruction))) (operation values)))))))

(defn cycle-timer
  [input]
  (loop [timer (range 240) values []]
    (if (empty? timer)
      values
      (let [current (first timer)]
        (recur (rest timer) (conj values [(quot current 40) (rem current 40) (apply + (take (inc current) input))]))))))

(defn draw-pixels
  [input]
  (loop [items input screen (vec (repeat 6 []))]
    (if (empty? items)
      screen
      (let [[line position x] (first items)]
        ; (when (= line 0) (println line position x (>= (+ x 2) (inc position) x)))
        (recur (rest items) (update screen line #(conj % (if (>= (+ x 2) (inc position) x) "#" "."))))))))

(defn display
  [input]
  (->> input
     (parse)
     (cycle-timer)
     (draw-pixels)
     (map #(apply str %))
     (join "\n")))


(defn solution-10-01
  "Find the sum of given six signal strengths"
  ([] (solution-10-01 input))
  ([input] (->> input
                (parse) 
                (calculate-cycles)
                (apply +))))

(defn solution-10-02
  "Find the sum of given six signal strengths"
  ([] (solution-10-02 input))
  ([input] (->> input
                (display))))
