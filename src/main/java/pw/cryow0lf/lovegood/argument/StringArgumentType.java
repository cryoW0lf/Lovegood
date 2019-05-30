package pw.cryow0lf.lovegood.argument;

public final class StringArgumentType extends ArgumentType<String> {

    protected StringArgumentType() {
        super(String.class);
    }

    @Override
    public ArgumentParseResult<String> parse(int position, String input) {
        final int whitespace = input.indexOf(' ', position);
        final int endIndex = whitespace == -1 ? input.length() : whitespace;
        return new ArgumentParseResult<>(
                input.substring(position, endIndex),
                endIndex
        );
    }
}
