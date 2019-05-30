package pw.cryow0lf.lovegood;

public interface CommandResult {
    CommandResult OK = () -> true;
    CommandResult FAILED = new FailedCommandResult("Failed");

    boolean success();
}
