package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Random;

@ToString
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
        this.assignmentId = "A" + String.format("%04d", nextId++);
        this.assignmentAverage = 0;
    }

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
}
