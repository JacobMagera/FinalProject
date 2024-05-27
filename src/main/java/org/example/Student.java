package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.util.Util;

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
        this.studentName = Util.toTitleCase(studentName);
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.studentId = String.format("S%06d", nextId++);
        this.registeredCourses = new ArrayList<>();
    }

    /**
     *  registers a course, this method
     *  (1) adds the course to the student's registeredCourses list,
     *  (2) adds the student to the course's registeredStudents list,
     *  (3) appends a null for the scores of each assignment of the course.
     * @param course the input course
     * @return False if the course is already registered, True otherwise.
     */
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

    /**
     * drops a course, remove the course from the student's registeredCourses list,
     * and remove the student from the course's registeredStudents list.
     * @param course the input course
     * @return False if the course is not yet registered, True otherwise.
     */
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

    /**
     * Converts a student to a simple String with only the studentId, the studentName, and departmentName.
     * @return the simple String.
     */
    public String toSimplifiedString() {
        return String.format("%s: %s, %s", studentId, studentName, department.getDepartmentName());
    }

    @Override
    public String toString() {
        return String.format("%s: %s, %s, %s, %s, Courses: %s",
                studentId, studentName, gender, address, department, registeredCourses);
    }
}
