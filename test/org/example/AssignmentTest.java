package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {

    @Test
    public void testCalcAssignmentAvg() {
        Assignment assignment = new Assignment("Assignment 1", 0.2, 100);
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(80);
        scores.add(90);
        scores.add(70);
        assignment.setScores(scores);
        assignment.calcAssignmentAvg();
        assertEquals(80.0, assignment.getAssignmentAverage(), 0.01);
    }
}