package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.Dictionary;
import java.util.List;


public class Not extends Node {

    Node n;

    public Not(Node n) {
        this.n = n;
    }

    @Override
    public Boolean result(Dictionary<String, Boolean> d) {
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
