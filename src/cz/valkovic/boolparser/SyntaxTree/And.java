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
    public String print(int previous, Boolean isLast) {
        StringBuilder b = new StringBuilder();
        b.append(super.print(previous, isLast));
        b.append(l.print(previous+1));
        b.append(r.print(previous+1,true));
        return b.toString();
    }

    @Override
    protected String symbol(){
        return "and";
    }
}
