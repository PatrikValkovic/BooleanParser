package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.Map;


public class And extends BinaryNode {

    public And(Node l, Node r) {
        super(l, r);
    }

    @Override
    public Boolean result(Map<String, Boolean> d) {
        return l.result(d) && r.result(d);
    }

    @Override
    protected String symbol(){
        return "and";
    }
}
