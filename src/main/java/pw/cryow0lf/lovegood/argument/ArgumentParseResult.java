package pw.cryow0lf.lovegood.argument;

public final class ArgumentParseResult<T> {
    private final T result;
    private final int position;

    public ArgumentParseResult(final T result, final int position) {
        this.result = result;
        this.position = position;
    }

    public T getResult() {
        return result;
    }

    public int getPosition() {
        return position;
    }
}
