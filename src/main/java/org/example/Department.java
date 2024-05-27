package org.example;

import lombok.*;
import org.example.util.Util;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Department {
    private String departmentId;
    private String departmentName;
    private static int nextId = 1;

    /**
     * Checks if a department name is valid or not
     * @param departmentName input string
     * @return True if the format is followed, false otherwise
     */
    public static boolean validateDepartmentName(String departmentName) {
        for (int i = 0; i < departmentName.length(); i++) {
            if (!Character.isDigit(departmentName.charAt(i)) && !Character.isAlphabetic(departmentName.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public Department(String departmentName) {
        if (validateDepartmentName(departmentName)) {
            this.departmentId = String.format("D%02d", nextId++);
            this.departmentName = Util.toTitleCase(departmentName);
        }
    }
}
