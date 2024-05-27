package org.example;

import org.example.util.Util;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@EqualsAndHashCode
@Getter
@Setter

public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Double> finalScores;
    private static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d",department.getDepartmentId(), nextId++);
        this.courseName = Util.toTitleCase(courseName);
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }

    /**
     * Checks if the sum of weights of all assignments of that course equals to 1 (100%).
     * @return True if total weight is 1, false otherwise.
     */
    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;
        for (Assignment assignment : assignments) {
            totalWeight += assignment.getWeight();
        }
        return totalWeight == 1.0;
    }

    /**
     *  Adds a new assignment to the course.
     * @param assignmentName the input name
     * @param weight the input weight
     * @param maxScore the input Max Score
     * @return False if adding the assignment makes the total weight exceed 100%, true otherwise.
     */
    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        if (isAssignmentWeightValid() || weight + getCurrentTotalWeight() > 1.0) {
            return false; // Reject if adding this assignment exceeds 100%
        }
        Assignment assignment = new Assignment(Util.toTitleCase(assignmentName), weight, maxScore);
        assignments.add(assignment);
        for (int i = 0; i < registeredStudents.size(); i++) {
            assignment.getScores().add(null); // Add null for each student in the scores list
        }
        return true;
    }

    private double getCurrentTotalWeight() {
        double totalWeight = 0;
        for (Assignment assignment : assignments) {
            totalWeight += assignment.getWeight();
        }
        return totalWeight;
    }

    /**
     * Adds a student to the student list of the course,
     * also adds a new null element to each assignment of this course, and add a new null element for the finalScores
     * @param student the input student
     * @return False if the student is already registered, True otherwise.
     */
    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }
        registeredStudents.add(student);
        for (Assignment assignment : assignments) {
            assignment.getScores().add(null); // Add null for this student's score in each assignment
        }
        finalScores.add(null); // Add null for this student's final score
        return true;
    }

    /**
     * Calculates the weighted average score of each student.
     * @return the weighted average score of each student.
     */
    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            double totalScore = 0;
            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                if (score != null) {
                    totalScore += score * assignment.getWeight();
                }
            }
            averages[i] = (int) Math.round(totalScore);
            finalScores.set(i, totalScore);
        }
        return averages;
    }

    /**
     * generates random scores for each assignment and student,
     * and calculates the final score for each student.
     */
    public void generateScores() {
        for (Assignment assignment : assignments) {
            assignment.generateRandomScore();
        }
        calcStudentsAverage();
    }

    /**
     * Displays the scores of a course in a table, with the assignment averages and student weighted average.
     */
    public void displayScores() {
        // Print course information
        System.out.println("Course: " + courseName + " (" + courseId + ")");

        // Print header row
        System.out.printf("%-20s", "");
        for (Assignment assignment : assignments) {
            System.out.printf("%-15s", assignment.getAssignmentName());
        }
        System.out.println("Final Score");

        double totalFinalScores = 0;
        double registeredFinalScores = 0;

        // Print student scores
        for (Student student : registeredStudents) {
            System.out.printf("%-20s", student.getStudentName());

            double finalScore = 0;
            registeredFinalScores++;
            for (int i = 0; i < assignments.size(); i++) {
                Assignment assignment = assignments.get(i);
                Integer score = assignment.getScores().get(registeredStudents.indexOf(student));
                System.out.printf("%-15s", (score != null ? score.toString() : "N/A"));
                if (score != null) {
                    finalScore += score * assignment.getWeight();
                    totalFinalScores += score * assignment.getWeight();
                }
            }
            System.out.printf("%.0f\n", finalScore);
        }

        // Print average scores
        System.out.printf("\n%-20s", "Average");
        for (Assignment assignment : assignments) {
            assignment.calcAssignmentAvg();
            System.out.printf("%-15s", String.format("%.0f", assignment.getAssignmentAverage()));
        }
        System.out.printf("%-15s", String.format("%.0f", totalFinalScores / registeredFinalScores));
        System.out.println();
    }

    /**
     * Converts a course to a simple string with only the courseId, courseName, credits, and departmentName.
     * @return
     */
    public String toSimplifiedString() {
        return String.format("%s: %s, Credits: %s, Department: %s",
                courseId, courseName, credits, department.getDepartmentName());
    }

    @Override
    public String toString() {
        String result = String.format("%s: Course Name: %s, Credits: %s, Department: %s\n",
                courseId, courseName, credits, department.getDepartmentName());
        result += "Assignments:\n";
        for (Assignment assignment : assignments) {
            result += String.format("  %s\n", assignment.toString());
        }
        result += "Registered Students:\n";
        for (Student student : registeredStudents) {
            result += String.format("  %s: %s, Department: %s\n",
                    student.getStudentId(), student.getStudentName(), student.getDepartment().getDepartmentName());
        }
        return result;
    }
}
