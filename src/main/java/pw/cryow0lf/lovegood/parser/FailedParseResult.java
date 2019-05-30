package pw.cryow0lf.lovegood.parser;

public class FailedParseResult implements ParseResult {
    private final String reason;

    public FailedParseResult(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean success() {
        return false;
    }

    @Override
    public String toString() {
        return "FailedParseResult{" +
                "reason='" + reason + '\'' +
                '}';
    }
}
