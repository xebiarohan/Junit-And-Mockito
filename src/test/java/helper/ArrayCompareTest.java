package helper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
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

    public int divide(int a ,int b) {
        return 0;
    }

    @Test
    public void testExceptionScenaario() {
        assertThrows(NullPointerException.class,() -> {
            int[] numbers = null;
            Arrays.sort(numbers);
        });
    }

    @Test
    public void testPerformance() {
        assertTimeout(Duration.ofMillis(100), () -> {
            int[] array = {34,5,1,36};
            for(int i=0;i<1000000;i++) {
                array[0] = i;
                Arrays.sort(array);
            }
        });

    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    void shouldPassNonNullMessageAsMethodParameter(String message) {
        assertNotNull(message);
    }
}
