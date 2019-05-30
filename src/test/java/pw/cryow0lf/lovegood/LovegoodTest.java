package pw.cryow0lf.lovegood;

import org.junit.jupiter.api.Test;
import pw.cryow0lf.lovegood.argument.DefaultArgumentTypes;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class LovegoodTest {

    @Test
    void simpleRootCommand() throws InterruptedException {
        final Lovegood<String> lovegood = new Lovegood<>();
        final CountDownLatch latch = new CountDownLatch(1);

        lovegood.command("test").function(context -> {
            latch.countDown();
            return context.source().equals("handler") ? CommandResult.OK : CommandResult.FAILED;
        });

        assertTrue(lovegood.execute("test", "handler").success());
        assertTrue(latch.await(1, TimeUnit.SECONDS));
    }

    @Test
    void simpleRootCommandFail() throws InterruptedException {
        final Lovegood<String> lovegood = new Lovegood<>();
        final CountDownLatch latch = new CountDownLatch(1);

        lovegood.command("test").function(context -> {
            latch.countDown();
            return context.source().equals("handler") ? CommandResult.OK : CommandResult.FAILED;
        });

        assertFalse(lovegood.execute("test", "notHandler").success());
        assertTrue(latch.await(1, TimeUnit.SECONDS));
    }

    @Test
    void simpleChildCommand() throws InterruptedException {
        final Lovegood<String> lovegood = new Lovegood<>();
        final CountDownLatch latch = new CountDownLatch(1);

        lovegood.command("test").command("me").function(context -> {
            latch.countDown();
            return context.source().equals("handler") ? CommandResult.OK : CommandResult.FAILED;
        });

        assertTrue(lovegood.execute("test me", "handler").success());
        assertTrue(latch.await(1, TimeUnit.SECONDS));
    }

    @Test
    void simpleChildCommandFail() throws InterruptedException {
        final Lovegood<String> lovegood = new Lovegood<>();
        final CountDownLatch latch = new CountDownLatch(1);

        lovegood.command("test").command("me").function(context -> {
            latch.countDown();
            return context.source().equals("handler") ? CommandResult.OK : CommandResult.FAILED;
        });

        assertFalse(lovegood.execute("test me", "notHandler").success());
        assertTrue(latch.await(1, TimeUnit.SECONDS));
    }

    @Test
    void simpleRootCommandWithArgument() throws InterruptedException {
        final Lovegood<String> lovegood = new Lovegood<>();
        final CountDownLatch latch = new CountDownLatch(1);

        lovegood.command("test").argument("who", DefaultArgumentTypes.STRING).function(context -> {
            latch.countDown();
            final Optional<String> who = context.argument("who", String.class);
            return who.isPresent() && who.get().equals("me") ? CommandResult.OK : CommandResult.FAILED;
        });

        assertTrue(lovegood.execute("test me", "handler").success());
        assertTrue(latch.await(1, TimeUnit.SECONDS));
    }

    @Test
    void simpleRootCommandWithArgumentFail() throws InterruptedException {
        final Lovegood<String> lovegood = new Lovegood<>();
        final CountDownLatch latch = new CountDownLatch(1);

        lovegood.command("test").argument("who", DefaultArgumentTypes.STRING).function(context -> {
            latch.countDown();
            final Optional<String> who = context.argument("who", String.class);
            return who.isPresent() && who.get().equals("me") ? CommandResult.OK : CommandResult.FAILED;
        });

        assertFalse(lovegood.execute("test you", "handler").success());
        assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
}
