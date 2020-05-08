package helper;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringHelperTest {
    StringHelper helper = new StringHelper();

    @Test
    public void test() {
        assertEquals("abc", "abc");
        // Expected , actual
    }

    @Test
    public void truncateAInFirst2Positions_AinFirst2Position() {
        assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
    }

    @Test
    public void truncateAinFirst2Positions_AinFirstPosition() {
        assertEquals("CD",helper.truncateAInFirst2Positions("ACD"));
    }

    @Test
    public void truncateAInnFirst2Positions_NoAPresent() {
        assertEquals("CDEF",helper.truncateAInFirst2Positions("CDEF"));
    }

    @Test
    public void truncateAInnFirst2Positions_NoAinFirst2Position() {
        assertEquals("CDAA",helper.truncateAInFirst2Positions("CDAA"));
    }

    @Test
    public void shouldReturnFalseWhenFirstTwoAndLastTwoCharactersAreNotSame() {
        assertFalse(helper.areFirstAndLastTwoCharactersTheSame("ABCD"));
    }

    @Test
    public void shouldReturnTrueWhenFirstTwoAndLastTwoCharactersAreSame() {
        assertTrue(helper.areFirstAndLastTwoCharactersTheSame("ABAB"));
    }

    @Test
    public void shouldReturnTrueWhenWePassOnlyTwoCharacters(){
        assertTrue(helper.areFirstAndLastTwoCharactersTheSame("AB"));
    }

    @Test
    public void shouldReturnFalseWhenASingleCharacterisPassed() {
        assertFalse(helper.areFirstAndLastTwoCharactersTheSame("A"));
    }
}