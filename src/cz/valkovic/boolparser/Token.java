/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BooleanParser;

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
