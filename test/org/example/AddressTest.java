package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

        @Test
        public void testIsPostalCodeValid_Valid6CharacterFormat() {
            assertTrue(Address.isPostalCodeValid("A1B2C3"));
        }

        @Test
        public void testIsPostalCodeValid_Valid7CharacterFormat() {
            assertTrue(Address.isPostalCodeValid("A1B 2C3"));
        }

        @Test
        public void testIsPostalCodeValid_InvalidFormat() {
            assertFalse(Address.isPostalCodeValid("123 456"));
        }

        @Test
        public void testIsPostalCodeValid_TooShort() {
            assertFalse(Address.isPostalCodeValid("A1B2C"));
        }

        @Test
        public void testIsPostalCodeValid_TooLong() {
            assertFalse(Address.isPostalCodeValid("A1B2C3D"));
        }

        @Test
        public void testIsPostalCodeValid_LowerCase() {
            assertTrue(Address.isPostalCodeValid("a1b 2c3"));
        }

}