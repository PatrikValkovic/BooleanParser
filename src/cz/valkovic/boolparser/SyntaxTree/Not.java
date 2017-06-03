package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

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
    public String print(int previous, Boolean isLast) {
        return super.print(previous, isLast) +
                n.print(previous + 1, true);
    }

    @Override
    protected String symbol(){
        return "not";
    }

}
