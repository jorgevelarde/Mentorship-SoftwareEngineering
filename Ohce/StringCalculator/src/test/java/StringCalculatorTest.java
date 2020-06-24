import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void testAddReturnZeroWhenInputIsEmpty() {

        // When
        int result = stringCalculator.add("");

        // Then
        assertEquals(0, result);
    }

    @Test
    public void testAddReturnsSumOfOneNumber() {
        // When
        int result = stringCalculator.add("2");

        // Then
        assertEquals(2, result);

        result = stringCalculator.add("3");
        assertEquals(3, result);
        result = stringCalculator.add("5");
        assertEquals(5, result);

    }

    @Test
    public void testAddReturnsSumOfTwoNumbers() {
        // When
        int result = stringCalculator.add("3,5");

        // Then
        assertEquals(8, result);
    }

    @Test
    public void testAddReturnsSumOfThreeNumbers() {
        // When
        int result = stringCalculator.add("3,5,7");

        // Then
        assertEquals(15, result);
    }

    @Test
    public void testAddReturnsSumOfThreeNumbersWithNewlineDelimiter() {
        // When
        int result = stringCalculator.add("3\n5,7");

        // Then
        assertEquals(15, result);
    }

    @Test
    public void testAddReturnsSumOfThreeNumbersWithCustomDelimiter() {
        // When
        int result = stringCalculator.add("//;\n3;5;7");

        // Then
        assertEquals(15, result);
    }

    @Test
    public void testAddReturnsSumOfThreeNumbersWithLongCustomDelimiter() {
        // When
        int result = stringCalculator.add("//[---]\n3---5---7");

        // Then
        assertEquals(15, result);
    }

    @Test
    public void testAddReturnsSumOfThreeNumbersWithSeveralLongCustomDelimiter() {
        // When
        int result = stringCalculator.add("//[---][@@][!]\n3!5@@7---11");

        // Then
        assertEquals(26, result);
    }

    @Test
    public void testAddThrowsWhenInputHasANegativeNumber() {
        // When
        Exception exception =  assertThrows(Exception.class, () -> stringCalculator.add("//[;]\n3;-5;7"));

        // Then
        assertEquals("negatives not allowed: -5", exception.getMessage());
    }

    @Test
    public void testAddThrowsWhenInputHasNegativeNumbers() {
        // When
        Exception exception =  assertThrows(Exception.class, () -> stringCalculator.add("//[;]\n3;-5;7;-11"));

        // Then
        assertEquals("negatives not allowed: -5,-11", exception.getMessage());
    }

    @Test
    public void testGetCalledCountReturnsNumberOfTimesAddHasBeenCalled() {
        // When
        stringCalculator.add("3,5,7");
        stringCalculator.add("3,5,7");
        stringCalculator.add("3,5,7");

        // Then
        assertEquals(3, stringCalculator.getCalledCount());

        stringCalculator.add("3,5,7");

        assertEquals(4, stringCalculator.getCalledCount());
    }

    @Test
    public void testAddIgnoresNumbersGreaterThan1000() {
        // When
        int result = stringCalculator.add("3,5,1001,7,1065");

        // Then
        assertEquals(15, result);
    }
}
