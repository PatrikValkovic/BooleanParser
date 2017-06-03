package cz.valkovic.boolparser;/*
 * Created by Patrik Valkovic
 * 03.06.2017 17:50.
 * Part of BooleanParser
 */

import com.sun.org.apache.xpath.internal.operations.Bool;
import cz.valkovic.boolparser.SyntaxTree.Node;

import java.util.*;

public class Evaluator {

    private Node root;

    public Evaluator(Node root) {
        this.root = root;
    }

    public List<List<Boolean>> buildResults(String[] variables) {
        Map<String, Boolean> d = new TreeMap<>();
        List<List<Boolean>> lst = new ArrayList<>((int)Math.pow(2,variables.length));

        for (String v : variables)
            d.put(v,true);

        for(int i=0;i<Math.pow(2,variables.length);i++){

            Boolean res = root.result(d);
            List<Boolean> row = new ArrayList<>(variables.length+1);
            for (String variable : variables)
                row.add(d.get(variable));
            row.add(res);
            lst.add(row);

            //change values
            for(int j=0;j<variables.length;j++)
                if(!d.get(variables[j]))
                    d.put(variables[j],true);
                else {
                    d.put(variables[j], false);
                    break;
                }
        }
        return lst;
    }

    public String tranformResults(List<List<Boolean>> r, String[] variables) {
        StringBuilder b = new StringBuilder();
        int[] width = new int[variables.length + 1];

        for(int i=0;i<variables.length;i++)
            width[i] = variables[i].length() < 5 ? 7 : variables[i].length()+2;
        width[variables.length] = 8;

        b.append('|');
        for(int i=0;i<variables.length;i++)
            b.append(" ").append(variables[i]).append(String.join("",Collections.nCopies(width[i]-1-variables[i].length()," "))).append('|');
        b.append(" Result |");
        b.append('\n');

        for(List<Boolean> row: r){
            b.append('|');
            for(int i=0;i<row.size();i++)
                b.append(" ").append(row.get(i) ? "true " : "false").append(String.join("",Collections.nCopies(width[i]-6," "))).append('|');
            b.append('\n');
        }
        return b.toString();
    }

}
