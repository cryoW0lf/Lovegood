package pw.cryow0lf.lovegood;

import pw.cryow0lf.lovegood.argument.ArgumentNode;
import pw.cryow0lf.lovegood.argument.ArgumentParseResult;
import pw.cryow0lf.lovegood.parser.ParseResult;
import pw.cryow0lf.lovegood.parser.SuccessfulParseResult;

import java.util.*;

import static java.util.Locale.ROOT;

public final class Lovegood<S> {
    private String prefix;
    private final Map<String, CommandNode<S>> rootNodes = new HashMap<>();

    public Lovegood<S> prefix(final String prefix) {
        this.prefix = prefix;
        return this;
    }

    public CommandNode<S> command(String name) {
        name = name.toLowerCase(ROOT);
        return rootNodes.computeIfAbsent(name, CommandNode::new);
    }

    public ParseResult parse(String commandString) {
        //TODO TEST and cleanup
        if (prefix != null) {
            if (!commandString.startsWith(prefix))
                return ParseResult.MISSING_PREFIX;
            commandString = commandString.substring(prefix.length());
        }
        commandString = commandString.trim();

        int whitespace = commandString.indexOf(' ');
        AbstractNode<S> node = rootNodes.get(
                (whitespace == -1 ? commandString : commandString.substring(0, whitespace)).toLowerCase(ROOT)
        );

        if (node == null) {
            return ParseResult.NOT_FOUND;
        }
        if (whitespace == -1) {
            return ParseResult.success(node.function(), Collections.emptyMap());
        }

        int index = whitespace + 1;
        final Map<String, Object> arguments = new HashMap<>();
        String substring;
        while (index < commandString.length()) {
            whitespace = commandString.indexOf(' ', index);
            if (whitespace == -1) whitespace = commandString.length();

            if (index - whitespace >= -1) {
                index = whitespace + 1;
                continue;
            }
            substring = commandString.substring(index, whitespace).toLowerCase(ROOT);

            if (node instanceof CommandNode && ((CommandNode<S>) node).children().containsKey(substring)) {
                node = ((CommandNode<S>) node).children().get(substring);
                index = whitespace + 1;
                continue;
            }

            ArgumentParseResult<?> result = null;
            int validEntries = 0;
            for (final ArgumentNode<S, ?> entry : node.arguments.values()) {
                try {
                    result = entry.type().parse(index, commandString);
                    node = entry;
                    validEntries++;
                } catch (final Exception ignored) {
                    System.out.printf("Test ArgumentType %s for %s failed%n", entry.type().typeClass(), entry.name());
                }
            }

            if (validEntries == 0) {
                return ParseResult.NOT_FOUND;
            }
            if (validEntries > 1) {
                return ParseResult.AMBIOUS_PATH;
            }
            arguments.put(((ArgumentNode) node).name(), result.getResult());
            index = result.getPosition() + 1;
        }
        return ParseResult.success(node.function(), arguments);
    }

    public CommandResult execute(final String command, final S source) {
        final ParseResult parseResult = parse(command);
        if (!(parseResult instanceof SuccessfulParseResult)) {
            return new FailedCommandResult("Not parsed: " + parseResult.toString());
        }
        @SuppressWarnings("unchecked")
        final SuccessfulParseResult<S> successfulParseResult = (SuccessfulParseResult<S>) parseResult;
        return successfulParseResult.function().apply(new CommandContext<>(source, successfulParseResult.arguments()));
    }
}
