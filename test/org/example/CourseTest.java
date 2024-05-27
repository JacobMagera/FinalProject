package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    public void testIsAssignmentWeightValid_ValidWeights() {
        Department department = new Department("Computer Science");
        Course course = new Course("Programming 1", 3.0, department);
        course.addAssignment("Assignment 1", 0.4, 100);
        course.addAssignment("Assignment 2", 0.6, 100);
        assertTrue(course.isAssignmentWeightValid());
    }

    @Test
    public void testIsAssignmentWeightValid_InvalidWeights() {
        Department department = new Department("Computer Science");
        Course course = new Course("Programming 1", 3.0, department);
        course.addAssignment("Assignment 1", 0.3, 100);
        course.addAssignment("Assignment 2", 0.8, 100);
        assertFalse(course.isAssignmentWeightValid());
    }

    @Test
    public void testCalcStudentsAverage() {
        Department department = new Department("Computer Science");
        Course course = new Course("Programming 1", 3.0, department);
        Assignment assignment1 = new Assignment("Assignment 1", 0.5, 100);
        Assignment assignment2 = new Assignment("Assignment 2", 0.5, 100);

        ArrayList<Assignment> assignments = new ArrayList<>();
        assignments.add(assignment1);
        assignments.add(assignment2);
        course.setAssignments(assignments);

        Student student = new Student("John Doe", Gender.MALE, new Address(123, "Main St", "City", "Province", "123 456", "Country"), department);
        course.registerStudent(student);
        assignment1.setScores(new ArrayList<>(Arrays.asList(80)));
        assignment2.setScores(new ArrayList<>(Arrays.asList(90)));

        int[] averages = course.calcStudentsAverage();
        assertEquals(85, averages[0]);
    }
}