import java.time.LocalTime;

public class App {
    private final Echo echo;
    private ConsoleInteractor interactor;
    private TimeUtils timeUtils;
    private final LocalTime morningStart = LocalTime.of(6, 0);
    private final LocalTime morningEnd = LocalTime.of(12, 0);
    private final LocalTime afternoonStart = LocalTime.of(12, 0);
    private LocalTime afternoonEnd = LocalTime.of(20, 0);

    public App(ConsoleInteractor interactor, TimeUtils timeUtils, Echo echo) {
        this.interactor = interactor;
        this.timeUtils = timeUtils;
        this.echo = echo;
    }

    public void run(String username) {
        printGreetingForCurrentTime(username);
        String text = interactor.getInputText();
        while (!"Stop!".equals(text)) {
            interactor.print(echo.process(text));
            text = interactor.getInputText();
        }
    }

    private void printGreetingForCurrentTime(String username) {
        if (isItMorning())
            interactor.print(String.format("Buenos días, %s!", username));
        if (isItAfternoon())
            interactor.print(String.format("Buenas tardes, %s!", username));
        else
            interactor.print(String.format("Buenas noches, %s!", username));
    }

    private boolean isItMorning() {
        return isCurrentTimeInRange(morningStart, morningEnd);
    }

    private boolean isItAfternoon() {
        return isCurrentTimeInRange(afternoonStart, afternoonEnd);
    }

    private boolean isCurrentTimeInRange(LocalTime rangeStart, LocalTime rangeEnd) {
        return timeUtils.getCurrentTime().compareTo(rangeStart) > 0 && timeUtils.getCurrentTime().compareTo(rangeEnd) < 0;
    }
}
