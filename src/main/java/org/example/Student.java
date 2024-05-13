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
    private Department department;
    private ArrayList<Course> registeredCourses;
    private static int nextId = 1;
}
