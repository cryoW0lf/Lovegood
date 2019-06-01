package pw.cryow0lf.lovegood;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandContextTest {

    @Test
    void source() {
        final CommandContext<String> context = new CommandContext<>("test", null);

        assertEquals("test", context.source(), "Source isn't equal");
    }

    @Test
    void arguments() {
        final Map<String, Object> arguments = Collections.singletonMap("testArgument", 12321);
        final CommandContext<String> context = new CommandContext<>(null, arguments);

        assertEquals(arguments, context.arguments(), "Argumentmap isn't equal");
    }

    @Test
    void argument() {
        final Map<String, Object> arguments = Collections.singletonMap("testArgument", 12321);
        final CommandContext<String> context = new CommandContext<>(null, arguments);

        assertEquals(12321, (int) context.argument("testArgument", Integer.class).orElse(-1), "Int Argument should be equal");
    }
}