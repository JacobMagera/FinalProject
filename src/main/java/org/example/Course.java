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
        this.courseId = "C-" + department.getDepartmentId() + "-" + String.format("%02d", nextId++);
        this.courseName = Util.toTitleCase(courseName);
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }

    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;
        for (Assignment assignment : assignments) {
            totalWeight += assignment.getWeight();
        }
        return totalWeight == 1.0;
    }

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

    public void generateScores() {
        for (Assignment assignment : assignments) {
            assignment.generateRandomScore();
        }
        calcStudentsAverage();
    }

    public void displayScores() {
        System.out.println("Course: " + courseName + " (" + courseId + ")");
        System.out.print("                    ");
        for (Assignment assignment : assignments) {
            System.out.print(assignment.getAssignmentName() + "   ");
        }
        System.out.println("Final Score");

        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            System.out.print(student.getStudentName() + "             ");
            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                System.out.print((score != null ? score : "N/A") + "             ");
            }
            System.out.println(finalScores.get(i));
        }

        System.out.print("Average             ");
        for (Assignment assignment : assignments) {
            System.out.print(assignment.getAssignmentAverage() + "             ");
        }
        System.out.println();
    }

    public String toSimplifiedString() {
        return String.format("%s: %s, Credits: %s, Department: %s",
                courseId, courseName, credits, department.getDepartmentName());
    }

    @Override
    public String toString() {
        String result = String.format("%s: %s, Credits: %s, Department: %s\n",
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
