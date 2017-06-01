package BooleanParser.SyntaxTree;

import java.util.HashSet;
import java.util.Set;
import pkgboolean.BooleanTable;

/**
 * Předek NODE, reprezentace operandu Termu s hodnotou String a vysledkem z
 * BooleanTable
 *
 * @author ondrej
 */
public class Terminal extends Node {

    private String value;
    private BooleanTable search;

    public Terminal(String value, BooleanTable search) {
        this.value = value;
        this.search = search;
    }

    @Override
    public Set<String> result() {
        return new HashSet<>(this.search.search(this.value));
    }

}
