package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.HashSet;
import java.util.Set;


public class And extends Node {

    private Node l;
    private Node r;

    public And(Node l, Node r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public Set<String> result() {
        Set<String> lr = this.l.result();
        Set<String> rr = this.r.result();
        int capacity = lr.size() > rr.size() ? rr.size() : lr.size();
        Set<String> s = new HashSet<>(capacity);
        s.addAll(lr);
        s.retainAll(rr);
        return s;
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
