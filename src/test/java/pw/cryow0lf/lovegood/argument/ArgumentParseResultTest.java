package pw.cryow0lf.lovegood.argument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class ArgumentParseResultTest {

    @Test
    void dataTest() {
        final ArgumentParseResult<String> result = new ArgumentParseResult<>("testResult", 42);

        assertEquals(42, result.getPosition(), "Position isn't the same");
        assertEquals("testResult", result.getResult(), "Result isn't 'testResult'");
    }

}