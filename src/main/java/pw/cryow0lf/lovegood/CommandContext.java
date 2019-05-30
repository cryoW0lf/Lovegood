package pw.cryow0lf.lovegood;

import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unchecked")
public final class CommandContext<S> {
    private final S source;
    private final Map<String, Object> arguments;

    public CommandContext(final S source, final Map<String, Object> arguments) {
        this.source = source;
        this.arguments = arguments;
    }

    public S source() {
        return source;
    }

    public Map<String, Object> arguments() {
        return arguments;
    }

    public <T> Optional<T> argument(final String argument, final Class<T> type) {
        return Optional.ofNullable((T) arguments.get(argument));
    }
}
