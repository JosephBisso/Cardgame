package Cardgame;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

public class TestUtil {
    public static void assertArrayEqualsUnordered(Object[] expected, Object[] actual) {
        String description = String.format("Expected: %s - got: %s%n", Arrays.toString(expected), Arrays.toString(actual));
        if (expected != null) {
            Arrays.sort(expected);
        }
        if (actual != null) {
            Arrays.sort(actual);
        }
        assertArrayEquals(expected, actual, description);
    }
}