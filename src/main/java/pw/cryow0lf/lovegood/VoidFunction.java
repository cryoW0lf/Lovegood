package pw.cryow0lf.lovegood;

public interface VoidFunction<S> extends CommandFunction<S> {

    @Override
    default CommandResult apply(CommandContext<S> context) {
        try {
            voidApply(context);
            return CommandResult.OK;
        } catch (final Exception e) {
            return new FailedCommandResult("Exception: " + e.getMessage());
        }
    }

    void voidApply(CommandContext<S> context);
}
