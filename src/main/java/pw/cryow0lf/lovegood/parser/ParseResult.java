package pw.cryow0lf.lovegood.parser;

import pw.cryow0lf.lovegood.CommandFunction;

import java.util.Map;

public interface ParseResult {
    ParseResult FAILED = new FailedParseResult("Failed");
    ParseResult MISSING_PREFIX = new FailedParseResult("Missing prefix");
    ParseResult NOT_FOUND = new FailedParseResult("Not found");
    ParseResult AMBIOUS_PATH = new FailedParseResult("Ambious Path");

    boolean success();

    static <S> ParseResult success(final CommandFunction<S> function, final Map<String, Object> arguments) {
        return new SuccessfulParseResult<>(function, arguments);
    }
}
