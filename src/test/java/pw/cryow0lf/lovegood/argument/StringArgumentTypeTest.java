package pw.cryow0lf.lovegood.argument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringArgumentTypeTest {
    final StringArgumentType stringArgumentType = new StringArgumentType();

    @Test
    void parseNormalString() {
        final ArgumentParseResult<String> result = stringArgumentType.
                parse(12, "unimportant relevant other");

        assertEquals(20, result.getPosition(), "Returned position isn't right");
        assertEquals("relevant", result.getResult(), "Returned result isn't right");
    }
}