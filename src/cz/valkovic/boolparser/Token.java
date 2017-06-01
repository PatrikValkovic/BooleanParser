package cz.valkovic.boolparser;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
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
