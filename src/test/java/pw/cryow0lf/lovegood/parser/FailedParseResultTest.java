package pw.cryow0lf.lovegood.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class FailedParseResultTest {

    @Test
    void success() {
        assertFalse(new FailedParseResult("").success(), "Failed should not succeed");
    }
}