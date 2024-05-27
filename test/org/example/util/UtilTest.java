package org.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    public void testToTitleCaseWithTwoWords() {
        String input = "java programming";
        String expected = "Java Programming";
        String actual = Util.toTitleCase(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testToTitleCaseWithMixedCase() {
        String input = "jAvA pROGramming";
        String expected = "Java Programming";
        String actual = Util.toTitleCase(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testToTitleCaseWithUpperCase() {
        String input = "JAVA JAVA";
        String expected = "Java Java";
        String actual = Util.toTitleCase(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testToTitleCaseWithEmptyString() {
        String input = "";
        String expected = "";
        String actual = Util.toTitleCase(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testToTitleCaseWithNull() {
        String input = null;
        String expected = null;
        String actual = Util.toTitleCase(input);
        assertEquals(expected, actual);
    }

}