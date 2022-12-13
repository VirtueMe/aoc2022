(ns aoc2022.day02
  "
   --- Day 2: Rock Paper Scissors ---

   The Elves begin to set up camp on the beach. To decide whose tent gets to be closest to the snack storage, a giant Rock Paper Scissors tournament is already in progress.

   Rock Paper Scissors is a game between two players. Each game contains many rounds; in each round, the players each simultaneously choose one of Rock, Paper, or Scissors using a hand shape. Then, a winner for that round is selected: Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock. If both players choose the same shape, the round instead ends in a draw.

   Appreciative of your help yesterday, one Elf gives you an encrypted strategy guide (your puzzle input) that they say will be sure to help you win. 
   \"The first column is what your opponent is going to play: A for Rock, B for Paper, and C for Scissors. The second column--\"  Suddenly, the Elf is called away to help with someone's tent.

   The second column, you reason, must be what you should play in response: X for Rock, Y for Paper, and Z for Scissors. Winning every time would be suspicious, so the responses must have been carefully chosen.

   The winner of the whole tournament is the player with the highest score. Your total score is the sum of your scores for each round. The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors) plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).

   Since you can't be sure if the Elf is trying to help you or trick you, you should calculate the score you would get if you were to follow the strategy guide.


   For example, suppose you were given the following strategy guide:

   A Y
   B X
   C Z

   This strategy guide predicts and recommends the following:


   - In the first round, your opponent will choose Rock (A), and you should choose Paper (Y). This ends in a win for you with a score of 8 (2 because you chose Paper + 6 because you won).

   - In the second round, your opponent will choose Paper (B), and you should choose Rock (X). This ends in a loss for you with a score of 1 (1 + 0).

   - The third round is a draw with both players choosing Scissors, giving you a score of 3 + 3 = 6.
   
   In this example, if you were to follow the strategy guide, you would get a total score of 15 (8 + 1 + 6).

   What would your total score be if everything goes exactly according to your strategy guide?"
  (:gen-class)
  (:require [aoc2022.core :refer [get-lines split-words]]))

(def ^:private input
  "Fetch the numbers from the input given. The input might not be the same as you have, 
   but the solution to solve it will be the same."
  (map split-words (get-lines "resources/02.txt")))

(def values {"X" 1 "Y" 2 "Z" 3})
(def values-2 {"X" 0 "Y" 3 "Z" 6})
;1
; (def rock "A")

;2
;(def paper "B")

;3
;(def scissor "C")


(defn evalute
  [[elf you]]
  (case elf
    "A" (+ (case you
             "X" 3
             "Y" 6
             "Z" 0)
           (values you))
    "B" (+ (case you
             "X" 0
             "Y" 3
             "Z" 6)
           (values you))
    "C" (+ (case you
             "X" 6
             "Y" 0
             "Z" 3)
           (values you))))

(defn evalute-2
  [[elf you]]
  (case elf
    "A" (+ (case you
             "X" 3
             "Y" 1
             "Z" 2)
           (values-2 you))
    "B" (+ (case you
             "X" 1
             "Y" 2
             "Z" 3)
           (values-2 you))
    "C" (+ (case you
             "X" 2
             "Y" 3
             "Z" 1)
           (values-2 you))))

(defn calculate-score
  ([] (calculate-score input))
  ([input] (calculate-score input evalute))
  ([input method] (reduce + (map method input))))

(defn solution-02-01
  ([] (solution-02-01 input))
  ([input] (calculate-score input)))

(defn solution-02-02
  ([] (solution-02-02 input))
  ([input] (calculate-score input evalute-2)))
