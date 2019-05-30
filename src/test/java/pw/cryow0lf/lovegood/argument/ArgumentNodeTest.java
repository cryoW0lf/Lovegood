package pw.cryow0lf.lovegood.argument;

import org.junit.jupiter.api.Test;
import pw.cryow0lf.lovegood.CommandContext;
import pw.cryow0lf.lovegood.CommandFunction;
import pw.cryow0lf.lovegood.CommandResult;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

final class ArgumentNodeTest {

    @Test
    void nameTest() {
        final ArgumentNode<String, String> argumentNode = new ArgumentNode<>("test", DefaultArgumentTypes.STRING);

        assertEquals("test", argumentNode.name(), "Name isn't equal");
    }

    @Test
    void functionTest() {
        final ArgumentNode<String, String> argumentNode = new ArgumentNode<>("test", DefaultArgumentTypes.STRING);
        final CountDownLatch latch = new CountDownLatch(1);
        argumentNode.function(context -> {
            if (context.source().equals("customHandler")) {
                latch.countDown();
                return CommandResult.OK;
            }
            return CommandResult.FAILED;
        });

        final boolean success = argumentNode.function()
                .apply(new CommandContext<>("customHandler", Collections.emptyMap())).success();
        boolean down = false;
        try {
            down = latch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(success, "CommandResult isn't OK");
        assertTrue(down, "Latch isn't on zero");
    }

    @Test
    void typeTest() {
        final ArgumentNode<String, String> argumentNode = new ArgumentNode<>("test", DefaultArgumentTypes.STRING);

        assertEquals(DefaultArgumentTypes.STRING, argumentNode.type(), "ArgumentType isn't equal");
    }

    @Test
    void applyTest() {
        final ArgumentNode<String, String> argumentNode = new ArgumentNode<>("test", DefaultArgumentTypes.STRING);

        final ArgumentNode<String, String> result = argumentNode.apply(
                node -> assertEquals(argumentNode, node, "Node supplied by Consumer isn't equal")
        );

        assertEquals(argumentNode, result, "Node returned by apply isn't equal");
    }

    @Test
    void equalsTest() {
        final ArgumentNode<String, String> argumentNode0 = new ArgumentNode<>("test", DefaultArgumentTypes.STRING);
        final ArgumentNode<String, String> argumentNode1 = new ArgumentNode<>("test", DefaultArgumentTypes.STRING);
        final CommandFunction<String> function = context -> CommandResult.OK;
        argumentNode0.function(function);
        argumentNode1.function(function);

        assertEquals(argumentNode0, argumentNode1, "Equals nodes aren't returning equal");
    }

    @Test
    void hashCodeTest() {
        final ArgumentNode<String, String> argumentNode0 = new ArgumentNode<>("test", DefaultArgumentTypes.STRING);
        final ArgumentNode<String, String> argumentNode1 = new ArgumentNode<>("test", DefaultArgumentTypes.STRING);
        final CommandFunction<String> function = context -> CommandResult.OK;
        argumentNode0.function(function);
        argumentNode1.function(function);

        assertEquals(argumentNode0.hashCode(), argumentNode1.hashCode(), "Equals nodes aren't returning equal");
    }
}