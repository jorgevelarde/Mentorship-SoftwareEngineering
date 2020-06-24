public class Main {
    public static void main(String[] args) {
        String username = args[0];
        App app = new App(new ConsoleInteractor(), new TimeUtils(), new Echo());
        app.run(username);
    }
}
