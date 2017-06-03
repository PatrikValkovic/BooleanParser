package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.List;
import java.util.Map;


public class Not extends Node {

    Node n;

    public Not(Node n) {
        this.n = n;
    }

    @Override
    public Boolean result(Map<String, Boolean> d) {
        return !n.result(d);
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
