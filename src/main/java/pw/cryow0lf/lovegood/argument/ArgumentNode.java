package pw.cryow0lf.lovegood.argument;

import pw.cryow0lf.lovegood.AbstractNode;
import pw.cryow0lf.lovegood.CommandFunction;

import java.util.Objects;
import java.util.function.Consumer;


public final class ArgumentNode<S, T> extends AbstractNode<S> {
    private final String name;
    private final ArgumentType<T> type;

    public ArgumentNode(final String name, final ArgumentType<T> type) {
        this.name = name;
        this.type = type;
    }

    public String name() {
        return name;
    }

    public ArgumentNode<S, T> function(final CommandFunction<S> handler) {
        this.function = handler;
        return this;
    }

    public ArgumentType<T> type() {
        return type;
    }

    public ArgumentNode<S, T> apply(final Consumer<ArgumentNode<S, T>> consumer) {
        consumer.accept(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArgumentNode)) return false;
        ArgumentNode<?, ?> that = (ArgumentNode<?, ?>) o;
        return name.equals(that.name) &&
                type.equals(that.type) &&
                Objects.equals(function, that.function) &&
                arguments.equals(that.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, function, arguments);
    }
}
