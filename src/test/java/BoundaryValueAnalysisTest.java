import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BoundaryValueAnalysisTest {

    @Test
    public void shouldThrowIAEIfCheatArgumentIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Exam.solve(4, null));
        assertEquals(exception.getMessage(), "the cheat argument is null or empty");
    }

    @Test
    public void shouldThrowIAEIfCheatArgumentIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Exam.solve(4, new int[0]));
        assertEquals(exception.getMessage(), "the cheat argument is null or empty");
    }

    @Test
    public void shouldThrowIAEIfCheatArgumentHasLesserElementsThanN() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Exam.solve(4, new int[]{1}));
        assertEquals(exception.getMessage(), "the cheat argument has a number of elements different than the N argument");
    }

    @Test
    public void shouldThrowIAEIfCheatArgumentHasMoreElementsThanN() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Exam.solve(4, new int[]{1, 2, 3, 4, 5}));
        assertEquals(exception.getMessage(), "the cheat argument has a number of elements different than the N argument");
    }

    @Test
    public void shouldThrowIAEIfNIsTooSmall() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Exam.solve(3, new int[10]));
        assertEquals(exception.getMessage(), "the N argument is not in the required interval - 4 <= N <= 100.000");
    }

    @Test
    public void shouldThrowIAEIfNIsTooGreat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Exam.solve(100_001, new int[100_001]));
        assertEquals(exception.getMessage(), "the N argument is not in the required interval - 4 <= N <= 100.000");
    }

    @Test
    public void shouldReturnErrorResponseIfNIsBad() {
        int[] result = Exam.solve(4, new int[]{0, 1, 2, 3});
        assertArrayEquals(result, new int[]{-1});
    }

    @Test
    public void shouldReturnErrorResponseIfNIsBad2() {
        int[] result = Exam.solve(8, new int[]{2, 2, 2, 2, 2, 2, 2, 2});
        assertArrayEquals(result, new int[]{-1});
    }

    @Test
    public void shouldReturnErrorResponseIfNIsBad3() {
        int[] result = Exam.solve(99400, new int[99400]);
        assertArrayEquals(result, new int[]{-1});
    }

    @Test
    public void shouldReturnGoodResponse() {
        int[] result = Exam.solve(11, new int[]{13, 4, 6, 8, 10, 12, 14, 16, 18, 20, 11});
        int[] expected = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        assertArrayEquals(result, expected);
    }

    @Test
    public void shouldThrowIAEIfCheatArgumentHasElementsWithAValueBelowTheInterval() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Exam.solve(5, new int[]{-1_000_000_001, 2, 3, 4, 5}));
        assertEquals(exception.getMessage(), "a value in the cheat argument array is greater than 1.000.000.000 or lesser than -1.000.000.000");
    }

    @Test
    public void shouldReturnGoodResultAtBoundaryValue() {
        int[] result = Exam.solve(5, new int[]{-1_000_000_000, 2, 3, 4, 5});
        assertArrayEquals(new int[]{-500000000, -500000002, 500000002, 500000005, -499999998}, result);
    }

    @Test
    public void shouldReturnGoodResultAtBoundaryValue2() {
        int[] result = Exam.solve(5, new int[]{1_000_000_000, 2, 3, 4, 5});
        assertArrayEquals(new int[]{500000000, 499999998, -499999998, -499999995, 500000002}, result);
    }

    @Test
    public void shouldThrowIAEIfCheatArgumentHasElementsWithAValueBeyondTheInterval() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Exam.solve(5, new int[]{1_000_000_001, 2, 3, 4, 5}));
        assertEquals(exception.getMessage(), "a value in the cheat argument array is greater than 1.000.000.000 or lesser than -1.000.000.000");
    }
}
