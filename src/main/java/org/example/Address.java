package org.example;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    /**
     * Checks if a postcode is valid or not
     * @param postalCode input string
     * @return True if the format is followed, false otherwise
     */
    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode.length() == 6) {
            return Character.isAlphabetic(postalCode.charAt(0)) &&
                    Character.isDigit(postalCode.charAt(1)) &&
                    Character.isAlphabetic(postalCode.charAt(2)) &&
                    Character.isDigit(postalCode.charAt(3)) &&
                    Character.isAlphabetic(postalCode.charAt(4)) &&
                    Character.isDigit(postalCode.charAt(5));
        }
        if (postalCode.length() == 7) {
            return Character.isAlphabetic(postalCode.charAt(0)) &&
                    Character.isDigit(postalCode.charAt(1)) &&
                    Character.isAlphabetic(postalCode.charAt(2)) &&
                    postalCode.charAt(3) == ' ' &&
                    Character.isDigit(postalCode.charAt(4)) &&
                    Character.isAlphabetic(postalCode.charAt(5)) &&
                    Character.isDigit(postalCode.charAt(6));
        }

        return false;
    }

    public Address(int streetNo, String street, String city, String province, String postalCode, String country) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
            this.country = country;
        }
    }
}
