package pw.cryow0lf.lovegood;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FailedCommandResultTest {

    @Test
    void success() {
        assertFalse(new FailedCommandResult("").success());
    }
}