package pw.cryow0lf.lovegood;

public class FailedCommandResult implements CommandResult {
    private final String reason;

    public FailedCommandResult(final String reason) {
        this.reason = reason;
    }

    @Override
    public boolean success() {
        return false;
    }

    @Override
    public String toString() {
        return "FailedCommandResult{" +
                "reason='" + reason + '\'' +
                '}';
    }
}
