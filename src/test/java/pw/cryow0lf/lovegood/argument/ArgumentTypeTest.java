package pw.cryow0lf.lovegood.argument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class ArgumentTypeTest {

    @Test
    void typeClass() {
        final ArgumentType<String> argumentType = new ArgumentType<String>(String.class) {
            @Override
            public ArgumentParseResult<String> parse(int position, String input) {
                return null;
            }
        };

        assertEquals(String.class, argumentType.typeClass(), "TypeClass isn't String");
    }
}