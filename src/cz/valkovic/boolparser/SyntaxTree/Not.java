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

}
