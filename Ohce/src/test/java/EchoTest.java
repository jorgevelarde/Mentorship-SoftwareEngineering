import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoTest {

    private Echo echo;

    @BeforeEach
    void setUp() {
        echo = new Echo();
    }

    @Test
    public void testProcessReturnsTextBackwards() {
        assertEquals("atina", echo.process("anita"));
        assertEquals("aeiou", echo.process("uoiea"));
        assertEquals("orion", echo.process("noiro"));
    }

    @Test
    public void testProcessAddCongratulationWhenTextIsAPalindrome() {
        assertEquals("radar\nNice phrase!", echo.process("radar"));
        assertEquals("anit al aval atinA\nNice phrase!", echo.process("Anita lava la tina"));
    }

}