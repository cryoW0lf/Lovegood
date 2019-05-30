package pw.cryow0lf.lovegood.parser;

import org.junit.jupiter.api.Test;
import pw.cryow0lf.lovegood.CommandFunction;
import pw.cryow0lf.lovegood.CommandResult;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class SuccessfulParseResultTest {

    @Test
    void success() {
        final SuccessfulParseResult<String> result = new SuccessfulParseResult<>(null, null);

        assertTrue(result.success(), "Success should succeed");
    }

    @Test
    void function() {
        final CommandFunction<String> function = context -> CommandResult.OK;
        final SuccessfulParseResult<String> result = new SuccessfulParseResult<>(function, null);

        assertEquals(function, result.function(), "Function isn't the same");
    }

    @Test
    void arguments() {
        final Map<String, Object> map = new HashMap<>();
        map.put("test", "me");
        final SuccessfulParseResult<String> result = new SuccessfulParseResult<>(null, map);

        assertEquals(map, result.arguments(), "Arguments aren't the same");
    }
}