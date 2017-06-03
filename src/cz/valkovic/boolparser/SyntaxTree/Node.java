package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.Set;


public abstract class Node {

    public abstract Set<String> result();

    public String print(int previous) {
        return print(previous, false);
    }

    public String print() {
        return print(0);
    }

    public String print(int previous, Boolean isLast) {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<previous;i++)
            builder.append("|  ");

        builder.append(isLast ? "`--" : "|--");
        builder.append(this.symbol());
        builder.append('\n');
        return builder.toString();
    }

    protected String symbol(){
        return "Unknown";
    }
}
