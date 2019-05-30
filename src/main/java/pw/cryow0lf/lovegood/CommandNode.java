package pw.cryow0lf.lovegood;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public final class CommandNode<S> extends AbstractNode<S> {
    private final String name;

    private Map<String, CommandNode<S>> childNodes = new HashMap<>();

    CommandNode(final String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public CommandNode<S> function(final CommandFunction<S> handler) {
        this.function = handler;
        return this;
    }

    public CommandNode<S> command(String name) {
        name = name.toLowerCase(Locale.ROOT);
        return childNodes.computeIfAbsent(name, CommandNode::new);
    }

    public Map<String, CommandNode<S>> children() {
        return childNodes;
    }

    public CommandNode<S> apply(final Consumer<CommandNode<S>> consumer) {
        consumer.accept(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandNode)) return false;
        CommandNode<?> that = (CommandNode<?>) o;
        return name.equals(that.name) &&
                Objects.equals(function, that.function) &&
                childNodes.equals(that.childNodes) &&
                arguments.equals(that.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, function, childNodes, arguments);
    }
}
