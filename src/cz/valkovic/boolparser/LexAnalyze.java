package cz.valkovic.boolparser;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.ArrayList;
import java.util.List;


public class LexAnalyze {

    private List<Token> tokens;
    private int position;

    /**
     * Naplni strukturu příslušnými Tokeny
     *
     * @param data
     * @return
     */
    public boolean load(String data) {

        this.tokens = new ArrayList<>();
        this.position = 0;

        char[] input = data.toCharArray();
        int index = 0;

        if (!controlBrackets(data)) {
            return false;
        }

        while (index < input.length) {

            if (input[index] == ' ') {
                index++;
            } else if (input[index] == ')') {
                this.tokens.add(new Token(")", true));
                index++;
            } else if (input[index] == '(') {
                this.tokens.add(new Token("(", true));
                index++;
            } else if (index + 2 <= input.length && input[index] == 'n' && input[index + 1] == 'o' && input[index + 2] == 't') {
                this.tokens.add(new Token("not", true));
                index += 3;
            } else if (index + 2 <= input.length && input[index] == 'a' && input[index + 1] == 'n' && input[index + 2] == 'd') {
                this.tokens.add(new Token("and", true));
                index += 3;
            } else if (index + 2 <= input.length && input[index] == 'x' && input[index + 1] == 'o' && input[index + 2] == 'r') {
                this.tokens.add(new Token("xor", true));
                index += 3;
            } else if (index + 1 <= input.length && input[index] == 'o' && input[index + 1] == 'r') {
                this.tokens.add(new Token("or", true));
                index += 2;
            } else {
                StringBuilder b = new StringBuilder();
                while (index < input.length && input[index] != ' ' && input[index] != ')') {
                    if(!Character.isLowerCase(input[index]))
                        return false;
                    b.append(input[index++]);
                }
                this.tokens.add(new Token(b.toString(), false));
            }
        }

        return true;
    }

    /**
     * Kontroluje správnost závorek
     *
     * @param data
     * @return
     */

    private boolean controlBrackets(String data) {
        int countOfLeftBrackets = 0;

        for (char c : data.toCharArray()) {
            if (c == '(') {
                countOfLeftBrackets++;
            } else if (c == ')' && countOfLeftBrackets > 0) {
                countOfLeftBrackets--;
            } else if (c == ')') {
                return false;
            }
        }

        return countOfLeftBrackets == 0;
    }

    /**
     * Vrací konkrétní token struktury nebo vyjímku epsilon
     *
     * @return
     * @throws Exception
     */
    public Token current() throws Exception {
        if (isEpsilon()) {
            throw new Exception("Reached end of sequence");
        }

        return this.tokens.get(this.position);
    }

    /**
     * Vrací zda je currnet epsilon
     *
     * @return
     */
    public boolean isEpsilon() {
        return this.tokens.size() == this.position;
    }

    /**
     *
     * Posouvá ukazatel ve struktuře
     *
     * @return
     */
    public boolean move() {
        if (this.tokens.size() <= this.position) {
            return false;
        }

        this.position++;
        return true;
    }

}
