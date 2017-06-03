package cz.valkovic.boolparser.SyntaxTree;/*
 * Created by Patrik Valkovic
 * 03.06.2017 17:05.
 * Part of BooleanParser
 */

import java.util.List;
import java.util.Set;

public class BinaryNode extends Node {

    protected Node l;
    protected Node r;

    public BinaryNode(Node l, Node r){
        this.l = l;
        this.r = r;
    }

    @Override
    public Set<String> result() {
        return null;
    }

    @Override
    public String print(int previous, List<Integer> defined, Boolean isLast) {
        StringBuilder b = new StringBuilder();
        b.append(super.print(previous, defined, isLast));
        defined.add(previous);
        b.append(l.print(previous+1,defined,false));
        defined.remove(defined.size()-1);
        b.append(r.print(previous+1,defined,true));
        return b.toString();
    }
}
