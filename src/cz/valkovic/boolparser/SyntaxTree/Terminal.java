package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.Map;


public class Terminal extends Node {

    private String value;

    public Terminal(String value) {
        this.value = value;
    }

    @Override
    public Boolean result(Map<String, Boolean> d) {
        return d.get(value);
    }

    @Override
    protected String symbol(){
        return this.value();
    }

    public String value(){
        return this.value;
    }
}
