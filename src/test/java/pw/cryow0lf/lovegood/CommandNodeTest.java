package pw.cryow0lf.lovegood;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandNodeTest {

    @Test
    void name() {
        final CommandNode<String> commandNode = new CommandNode<>("commandNodeTest");

        assertEquals("commandNodeTest", commandNode.name(), "CommandNode name is not equal");
    }

    @Test
    void function() {
        final CommandFunction<String> testFunction = context -> CommandResult.FAILED;
        final CommandNode<String> commandNode = new CommandNode<>("commandNodeTest");

        final CommandNode<String> result = commandNode.function(testFunction);

        assertEquals(commandNode, result, "Returned CommandNode isn't the source");
        assertEquals(testFunction, commandNode.function(), "Function isn't set");
    }

    @Test
    void command() {
        final CommandNode<String> commandNode = new CommandNode<>("commandNodeTest");

        final CommandNode<String> subCommand = commandNode.command("sub");
        final CommandNode<String> sameCommand = commandNode.command("sub");

        assertEquals(subCommand, sameCommand, "Command was created against");
    }

    @Test
    void commandIgnoresCase() {
        final CommandNode<String> commandNode = new CommandNode<>("commandNodeTest");

        final CommandNode<String> subCommand = commandNode.command("sub");
        final CommandNode<String> sameCommand = commandNode.command("suB");

        assertEquals(subCommand, sameCommand, "Command was created against");
    }

    @Test
    void children() {
        final CommandNode<String> commandNode = new CommandNode<>("commandNodeTest");
        final CommandNode<String> sub = commandNode.command("sub");
        final CommandNode<String> sub2 = commandNode.command("sub2");
        final Map<String, CommandNode<String>> map = new HashMap<>();
        map.put("sub", sub);
        map.put("sub2", sub2);

        final Map<String, CommandNode<String>> children = commandNode.children();

        assertEquals(map, children, "Chilren aren't equal");
    }

    @Test
    void apply() {
        final CommandNode<String> commandNode = new CommandNode<>("commandNodeTest");

        final CommandNode<String> result = commandNode.apply(node ->
                assertEquals(commandNode, node, "Function node isn't equal")
        );
        assertEquals(commandNode, result, "Result isn't source");
    }

    @Test
    void equals() {
        final CommandFunction<String> testFunction = context -> CommandResult.FAILED;
        final CommandNode<String> commandNode = new CommandNode<>("commandNodeTest");
        commandNode.function(testFunction);
        final CommandNode<String> commandNode2 = new CommandNode<>("commandNodeTest");
        commandNode2.function(testFunction);

        assertEquals(commandNode, commandNode2);
    }

    @Test
    void hashCodeTest() {
        final CommandFunction<String> testFunction = context -> CommandResult.FAILED;
        final CommandNode<String> commandNode = new CommandNode<>("commandNodeTest");
        commandNode.function(testFunction);
        final CommandNode<String> commandNode2 = new CommandNode<>("commandNodeTest");
        commandNode2.function(testFunction);

        assertEquals(commandNode.hashCode(), commandNode2.hashCode());
    }
}