package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.HashSet;
import java.util.Set;


public class Terminal extends Node {

    private String value;

    public Terminal(String value) {
        this.value = value;
    }

    @Override
    public Set<String> result() {
        return new HashSet<>();
    }

}