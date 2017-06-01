package cz.valkovic.boolparser;

/**
 * Struktura pro LexAnalyzu, uchovává typ boolean a hodnotu String
 *
 * @author ondrej
 */
class Token {

    boolean op;
    String data = "";

    public Token(String input) {
        this(input, false);
    }

    public Token(String input, boolean op) {
        this.op = op;
        data = input;
    }

}
