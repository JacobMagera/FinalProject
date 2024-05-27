package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private int maxScore;
    private double assignmentAverage;
    private ArrayList<Integer> scores;
    private static int nextId = 1;

    public Assignment(String assignmentName, double weight, int maxScore) {
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.maxScore = maxScore;
        this.scores = new ArrayList<>();
        this.assignmentId = String.format("A%04d", nextId++);
        this.assignmentAverage = 0;
    }

    /**
     * Calculates the average score for one assignment.
     */
    public void calcAssignmentAvg() {
        int sum = 0;
        int count = 0;
        for (Integer score : scores) {
            if (score != null) {
                sum += score;
                count++;
            }
        }
        this.assignmentAverage = count == 0 ? 0 : (double) sum / count;
    }

    /**
     * generates random scores for all students in an assignment, the scores are generated with the following rule:
     * Firstly generates a random number in range [0, 10], then
     *
     * if the number is 0, then generates a random score in range [0, 60) for the student
     * if the number is 1, 2, then generates a random score in range [60, 70) for the student
     * if the number is 3, 4, then generates a random score in range [70, 80) for the student
     * if the number is 5, 6, 7, 8, then generates a random score in range [80, 90) for the student
     * if the number is 9, 10, then generates a random score in range [90, 100] for the student
     */
    public void generateRandomScore() {
        Random rand = new Random();
        for (int i = 0; i < scores.size(); i++) {
            int rangeSelector = rand.nextInt(11); // generates a number between 0 and 10
            int score;
            if (rangeSelector == 0) {
                score = rand.nextInt(60); // 0 to 59
            } else if (rangeSelector == 1 || rangeSelector == 2) {
                score = 60 + rand.nextInt(10); // 60 to 69
            } else if (rangeSelector == 3 || rangeSelector == 4) {
                score = 70 + rand.nextInt(10); // 70 to 79
            } else if (rangeSelector <= 8) {
                score = 80 + rand.nextInt(10); // 80 to 89
            } else {
                score = 90 + rand.nextInt(11); // 90 to 100
            }
            scores.set(i, score);
        }
    }

    /**
     ** Generates a string to represent an assignment, with assignmentId, assignmentName, weight and maxScore.
     */
    public String toString() {
        return String.format("Assignment(Assignment Id: %s, Assignment Name: %s, Weight: %.1f, Max Score: %d)",
                assignmentId, assignmentName, weight, maxScore);
    }
}
