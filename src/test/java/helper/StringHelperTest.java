package helper;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringHelperTest {

    @Test
    public void test() {
        assertEquals("abc","abc");
        // Expected , actual
    }

    @Test
    public void stringHelper() {
        StringHelper helper = new StringHelper();
        String actualValue  = helper.truncateAInFirst2Positions("AACD");
        assertEquals("CD",actualValue);
    }

}