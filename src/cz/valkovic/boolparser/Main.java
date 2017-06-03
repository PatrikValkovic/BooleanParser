package cz.valkovic.boolparser;

/*
 * Created by Patrik Valkovic
 * 01.06.2017 12:44.
 * Part of BooleanParser
 */


import cz.valkovic.boolparser.SyntaxTree.Node;

public class Main {
    public static void main(String[] args) throws Exception {
        LexAnalyze l = new LexAnalyze();
        l.load("pet and jedna xor dva and (dva or tri xor ctyri) and (pet and sest)");

        SyntaxAnalyze a = new SyntaxAnalyze(l);
        Node n = a.results();
        System.out.println(n.print());
    }
}
