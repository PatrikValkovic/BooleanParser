package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.Set;


public class And extends BinaryNode {

    public And(Node l, Node r) {
        super(l, r);
    }

    @Override
    public Set<String> result() {
        return null;
    }

    @Override
    protected String symbol(){
        return "and";
    }
}
