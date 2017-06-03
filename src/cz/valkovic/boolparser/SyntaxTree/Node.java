package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public abstract class Node {

    public abstract Set<String> result();

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
