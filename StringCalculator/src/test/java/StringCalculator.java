import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCalculator {
    private int numberOfAddCalls = 0;

    public int getCalledCount() {
        return numberOfAddCalls;
    }

    public int add(String input) {
        registerAddCall();
        if (input.isEmpty())
            return 0;

        StringCalculatorInput parsedInput = parseInput(input);
        String[] numbers = parsedInput.numbers.split(parsedInput.delimiter);
        throwIfHasNegativeNumbers(numbers);
        return createNumbersStream(numbers).sum();
    }

    private void throwIfHasNegativeNumbers(String[] numbers) {
        if (hasNegativeNumbers(createNumbersStream(numbers))) {
            String negativeNumbers = createNumbersStream(numbers)
                    .filter(n -> n < 0)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(","));
            throw new RuntimeException("negatives not allowed: " + negativeNumbers);
        }
    }

    private void registerAddCall() {
        numberOfAddCalls++;
    }

    private StringCalculatorInput parseInput(String input) {
        if (hasCustomDelimiter(input)) {
            String[] parameters = input.split("\n");
            return new StringCalculatorInput(extractCustomDelimiter(parameters[0]), parameters[1]);
        }
        else {
            return new StringCalculatorInput(",|\n", input);
        }
    }

    private String extractCustomDelimiter(String input) {
        if (hasLongCustomDelimiter(input)) {
            return extractLongCustomDelimiter(input);
        }
        return input.substring(2);
    }

    private boolean hasLongCustomDelimiter(String input) {
        return input.startsWith("//[");
    }

    private String extractLongCustomDelimiter(String input) {
        String substring = input.substring(3, input.length() - 1);
        String[] delimiters = substring.split("]\\[");
        return String.join("|", delimiters);
    }

    private boolean hasNegativeNumbers(IntStream numbersStream) {
        return numbersStream.anyMatch(n -> n < 0);
    }

    private IntStream createNumbersStream(String[] numbers) {
        return Stream.of(numbers).mapToInt(Integer::parseInt).filter(n -> n <= 1000);
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith("//");
    }

    class StringCalculatorInput {
        public String delimiter;
        public String numbers;

        public StringCalculatorInput(String delimiter, String numbers) {
            this.delimiter = delimiter;
            this.numbers = numbers;
        }
    }
}
