import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquivalencePartitioningTest {

    @Test
    public void shouldThrowNPEIfCheatArgumentIsNull() {
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
    public void shouldReturnErrorResponseIfNIsBad() {
        int[] result = Exam.solve(4, new int[]{0, 1, 2, 3});
        assertArrayEquals(result, new int[]{-1});
    }

    @Test
    public void shouldReturnGoodResponse() {
        int[] result = Exam.solve(9, new int[]{11, 4, 6, 8, 10, 12, 14, 16, 9});
        int[] expected = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(result, expected);
    }

    @Test
    public void shouldThrowIAEIfNIsTooGreat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Exam.solve(100_001, new int[100_001]));
        assertEquals(exception.getMessage(), "the N argument is not in the required interval - 4 <= N <= 100.000");
    }


}
