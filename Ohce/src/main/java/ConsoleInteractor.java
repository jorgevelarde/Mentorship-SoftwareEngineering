import java.util.Scanner;

public class ConsoleInteractor {

    public void print(String text) {
        System.out.println(text);
    }

    public String getInputText() {
        Scanner sc = new Scanner(System.in).useDelimiter("\\s");
        return sc.next();
    }
}
