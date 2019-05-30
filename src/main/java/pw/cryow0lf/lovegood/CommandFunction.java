package pw.cryow0lf.lovegood;

import java.util.function.Function;

public interface CommandFunction<S> extends Function<CommandContext<S>, CommandResult> {
}
