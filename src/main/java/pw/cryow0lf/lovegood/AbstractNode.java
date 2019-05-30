package pw.cryow0lf.lovegood;

import pw.cryow0lf.lovegood.argument.ArgumentNode;
import pw.cryow0lf.lovegood.argument.ArgumentType;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class AbstractNode<S> {
    protected CommandFunction<S> function;
    protected Map<Class<?>, ArgumentNode<S, ?>> arguments = new HashMap<>();

    public CommandFunction<S> function() {
        return function;
    }

    public <T> ArgumentNode<S, T> argument(String name, final ArgumentType<T> type) {
        //TODO TEST
        name = name.toLowerCase(Locale.ROOT);
        final Class<T> typeClass = type.typeClass();

        if (arguments.containsKey(typeClass)) {
            final ArgumentNode<S, ?> existing = arguments.get(typeClass);
            if (existing.name().equals(name)) {
                return (ArgumentNode<S, T>) existing;
            } else {
                throw new IllegalStateException(
                        String.format("Duplicated ArgumentType(%s): %s and %s", typeClass, existing.name(), name)
                );
            }
        }
        final ArgumentNode<S, T> node = new ArgumentNode<>(name, type);
        arguments.put(typeClass, node);
        return node;
    }

    public Map<Class<?>, ArgumentNode<S, ?>> arguments() {
        return arguments;
    }
}
