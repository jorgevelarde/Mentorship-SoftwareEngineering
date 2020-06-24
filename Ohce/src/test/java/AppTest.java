import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AppTest {
    @Mock
    ConsoleInteractor consoleInteractor;

    @Mock
    Echo echo;

    @Mock
    TimeUtils timeUtils;
    private App app;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        app = new App(consoleInteractor, timeUtils, echo);
        when(timeUtils.getCurrentTime()).thenReturn(LocalTime.of(23, 0));
        when(consoleInteractor.getInputText()).thenReturn("Stop!");
    }

    @Test
    public void testRunGreetsUsingName() {
        app.run("Jorge");

        verify(consoleInteractor).print("Buenas noches, Jorge!");
    }

    @Test
    public void testRunGreetsBuenosDiasAtMorningUsingName() {
        when(timeUtils.getCurrentTime()).thenReturn(LocalTime.of(11, 0));

        app.run("Jorge");

        verify(consoleInteractor).print("Buenos días, Jorge!");
    }

    @Test
    public void testRunGreetsBuenasTardesAtAfternoonUsingName() {
        when(timeUtils.getCurrentTime()).thenReturn(LocalTime.of(17, 0));

        app.run("Jorge");

        verify(consoleInteractor).print("Buenas tardes, Jorge!");
    }

    @Test
    public void testRunWaitsForInput() {
        app.run("Name");

        verify(consoleInteractor).getInputText();
    }

    @Test
    public void testRunCallsEchoProcess() {
        when(consoleInteractor.getInputText()).thenReturn("orion").thenReturn("Stop!");

        app.run("Name");

        verify(echo).process("orion");
    }

    @Test
    public void testRunPrintsEchoResult() {
        when(consoleInteractor.getInputText()).thenReturn("orion").thenReturn("Stop!");
        when(echo.process(anyString())).thenReturn("processedString");

        app.run("Name");

        verify(consoleInteractor).print("processedString");
    }

    @Test
    public void testRunWaitsForInputAgain() {
        when(consoleInteractor.getInputText()).thenReturn("orion").thenReturn("Stop!");

        app.run("Name");

        InOrder inOrder = Mockito.inOrder(consoleInteractor, echo);
        inOrder.verify(consoleInteractor).getInputText();
        inOrder.verify(echo).process(anyString());
        inOrder.verify(consoleInteractor).getInputText();

    }

    @Test
    public void testRunSkipsWhenStopCommandIsPassed() {
        when(consoleInteractor.getInputText()).thenReturn("Stop!");

        app.run("Name");

        InOrder inOrder = Mockito.inOrder(consoleInteractor, echo);
        inOrder.verify(consoleInteractor).getInputText();
        inOrder.verify(echo, never()).process(anyString());
        inOrder.verify(consoleInteractor, never()).getInputText();

    }

    @Test
    public void testRunExitsWhenStopCommandIsPassed() {
        when(consoleInteractor.getInputText()).thenReturn("some phrase").thenReturn("some phrase").thenReturn("some phrase").thenReturn("Stop!");

        app.run("Name");

        InOrder inOrder = Mockito.inOrder(consoleInteractor, echo);
        inOrder.verify(consoleInteractor).getInputText();
        inOrder.verify(echo).process(anyString());
        inOrder.verify(consoleInteractor).getInputText();
        inOrder.verify(echo).process(anyString());
        inOrder.verify(consoleInteractor).getInputText();
        inOrder.verify(echo).process(anyString());
        inOrder.verify(consoleInteractor).getInputText();

        inOrder.verify(echo, never()).process(anyString());
        inOrder.verify(consoleInteractor, never()).getInputText();

    }
}