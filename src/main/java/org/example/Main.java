package org.example;

public class Main {
    public static void main(String[] args) {
        Department department = new Department("Science baby");
        Address address = new Address(1, "meow meow", "Montreal", "Quebec", "H4L3J8", "Canada");
        Course course = new Course("Computer Science", 4, department);
        Student student = new Student("Jacob MAgera", Gender.MALE, address, department);
        Student student2 = new Student("Jacob MOgera", Gender.MALE, address, department);
        course.addAssignment("Womp womp", 0.2, 100);
        course.addAssignment("Womp wump", 0.3, 100);
        course.addAssignment("Womp wimp", 0.1, 100);
        course.addAssignment("Womp wemp", 0.2, 100);
        course.addAssignment("Womp wamp", 0.2, 100);
        course.registerStudent(student);
        course.registerStudent(student2);
        course.generateScores();
        course.displayScores();
    }
}
