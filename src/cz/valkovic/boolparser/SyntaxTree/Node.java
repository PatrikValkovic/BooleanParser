package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;


public abstract class Node {

    public abstract Boolean result(Dictionary<String,Boolean> d);

    public String print() {
        return print(0, new LinkedList<>(),false);
    }

    public String print(int previous, List<Integer> defined, Boolean isLast) {
        if(previous == 0)
            return this.symbol() + '\n';

        StringBuilder builder = new StringBuilder();

        for(int i=0;i<previous-1;i++)
            if(defined.contains(i))
                builder.append("|  ");
            else
                builder.append("   ");

        builder.append(isLast ? "`--" : "|--");
        builder.append(this.symbol());
        builder.append('\n');
        return builder.toString();
    }

    protected String symbol(){
        return "Unknown";
    }
}
