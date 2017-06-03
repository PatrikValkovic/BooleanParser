package cz.valkovic.boolparser;

/*
 * Created by Patrik Valkovic
 * 01.06.2017 12:44.
 * Part of BooleanParser
 */


import cz.valkovic.boolparser.SyntaxTree.Node;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        LexAnalyze l = new LexAnalyze();
        l.load(args.length == 0 ? "first or second and (third xor not first)" : args[1]);

        SyntaxAnalyze a = new SyntaxAnalyze(l);
        Node n = a.results();
        String[] variables = a.usedVariables();
        Evaluator e = new Evaluator(n);
        List<List<Boolean>> table = e.buildResults(variables);

        System.out.println(n.print());
        System.out.print("Used variables: ");
        System.out.println(String.join(",",a.usedVariables()));
        System.out.println();
        System.out.println(e.tranformResults(table,variables));
    }
}
