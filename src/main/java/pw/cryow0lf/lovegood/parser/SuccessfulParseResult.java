package pw.cryow0lf.lovegood.parser;

import pw.cryow0lf.lovegood.CommandFunction;

import java.util.Map;

public class SuccessfulParseResult<S> implements ParseResult {
    private final CommandFunction<S> function;
    private final Map<String, Object> arguments;

    public SuccessfulParseResult(final CommandFunction<S> function, final Map<String, Object> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public boolean success() {
        return true;
    }

    public CommandFunction<S> function() {
        return function;
    }

    public Map<String, Object> arguments() {
        return arguments;
    }
}
