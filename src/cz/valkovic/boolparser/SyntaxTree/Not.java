package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.List;
import java.util.Set;


public class Not extends Node {

    Node n;

    public Not(Node n) {
        this.n = n;
    }

    @Override
    public Set<String> result() {
        return null;
    }

    @Override
    public String print(int previous, List<Integer> defined, Boolean isLast) {
        return super.print(previous, defined, isLast) +
                n.print(previous + 1, defined, true);
    }

    @Override
    protected String symbol() {
        return "not";
    }

}
