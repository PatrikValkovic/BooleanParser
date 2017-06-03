package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * 01.06.2017 14:04.
 * Part of BooleanParser
 */

import java.util.Dictionary;
import java.util.Set;

public class Xor extends BinaryNode {

    public Xor(Node l, Node r) {
        super(l, r);
    }

    @Override
    public Boolean result(Dictionary<String, Boolean> d) {
        return !l.result(d).equals(r.result(d));
    }

    @Override
    protected String symbol(){
        return "xor";
    }
}
