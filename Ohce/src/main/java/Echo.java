public class Echo {
    public String process(String text) {
        String reversedText = reverse(text);
        return isPalindrome(text) ? reversedText + "\nNice phrase!" : reversedText;
    }

    public boolean isPalindrome(String text) {
        String parsedText = text.toLowerCase().replaceAll(" ", "");
        return parsedText.equals(reverse(parsedText));
    }

    private String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }
}
