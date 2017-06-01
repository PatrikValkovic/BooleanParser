package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * 01.06.2017 14:04.
 * Part of BooleanParser
 */

import java.util.HashSet;
import java.util.Set;

public class Xor extends Node {

    private Node l;
    private Node r;

    public Xor(Node l, Node r){
        this.l = l;
        this.r = r;
    }

    @Override
    public Set<String> result() {
        return new HashSet<>();
    }
}
