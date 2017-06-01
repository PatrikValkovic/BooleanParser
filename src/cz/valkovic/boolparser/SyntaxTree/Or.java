package BooleanParser.SyntaxTree;

import java.util.HashSet;
import java.util.Set;

/**
 * Předek NODE, reprezentace operandu OR
 *
 * @author ondrej
 */
public class Or extends Node {

    private Node l;
    private Node r;

    public Or(Node l, Node r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public Set<String> result() {
        Set<String> lr = this.l.result();
        Set<String> rr = this.r.result();
        Set<String> s = new HashSet<>(lr.size() + rr.size());
        s.addAll(lr);
        s.addAll(rr);
        return s;
    }
}
