package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode

public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;
    private static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.studentId = String.format("S%06d", nextId++);
        this.registeredCourses = new ArrayList<>();
    }

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.add(course);
        course.getRegisteredStudents().add(this);
        for (Assignment assignment : course.getAssignments()) {
            assignment.getScores().add(null);
        }
        course.getFinalScores().add(null);
        return true;
    }

    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);
        for (Assignment assignment : course.getAssignments()) {
            assignment.getScores().remove(this);
        }
        course.getFinalScores().remove(this);
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s: %s, %s, %s, %s, Courses: %s",
                studentId, studentName, gender, address, department, registeredCourses);
    }

    public String toSimplifiedString() {
        return String.format("%s: %s, %s", studentId, studentName, department.getDepartmentName());
    }


}
