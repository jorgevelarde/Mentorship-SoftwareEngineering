import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void testMainReceivesOneParameter() {
        Main.main(new String[]{"Name"});
    }
}
