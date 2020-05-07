package helper;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayCompareTest {

    @Test
    public void testArraySorting() {
        int[] numbers = {34, 56, 1, 6};
        int[] expectedNumbers = {1, 6, 34, 56};
        Arrays.sort(numbers);
        assertArrayEquals(expectedNumbers, numbers);

    }
}
