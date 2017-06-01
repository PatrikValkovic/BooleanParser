package cz.valkovic.boolparser.SyntaxTree;

import java.util.HashSet;
import java.util.Set;

/**
 * Předek NODE, reprezentace operandu Termu s hodnotou String a vysledkem z
 * BooleanTable
 *
 * @author ondrej
 */
public class Terminal extends Node {

    private String value;

    public Terminal(String value) {
        this.value = value;
    }

    @Override
    public Set<String> result() {
        return new HashSet<>();
    }

}
