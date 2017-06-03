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

    public String tranformResults(List<List<Boolean>> r) {
        return "";
    }


}
