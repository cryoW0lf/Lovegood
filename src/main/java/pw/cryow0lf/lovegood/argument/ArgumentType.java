package pw.cryow0lf.lovegood.argument;

public abstract class ArgumentType<T> {
    private final Class<T> typeClass;

    protected ArgumentType(final Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public abstract ArgumentParseResult<T> parse(int position, String input);

    public Class<T> typeClass() {
        return typeClass;
    }
}
