package BooleanParser.SyntaxTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Předek NODE, reprezentace operandu NOT
 *
 * @author ondrej
 */
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
