package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.HashSet;
import java.util.Set;


public class Not extends Node {

    Node n;

    public Not(Node n) {
        this.n = n;
    }

    @Override
    public Set<String> result() {
        Set<String> all = new HashSet<>(Node.all.size());
        all.addAll(Node.all);
        all.removeAll(this.n.result());
        return all;
    }

    @Override
    public String print(int previous, Boolean isLast) {
        StringBuilder b = new StringBuilder();
        b.append(super.print(previous, isLast));
        b.append(n.print(previous+1,true));
        return b.toString();
    }

    @Override
    protected String symbol(){
        return "not";
    }

}
