package cz.valkovic.boolparser;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import cz.valkovic.boolparser.SyntaxTree.*;

import java.util.Set;


public class SyntaxAnalyze {


    private static String mess = "Parser error";
    private static boolean debug = true;

    private LexAnalyze lex;

    private Node root;

    /**
     * Příjmá LexAnalyze a BooleanTable pro vyhledávání
     *
     * @param l
     */
    public SyntaxAnalyze(LexAnalyze l) {
        this.lex = l;
        this.root = null;
    }

    /**
     * Vrací výsledek Celeho výrazu s výsledkama z booleanTable
     *
     * @return
     * @throws Exception
     */
    public Set<String> results() throws Exception {
        if (this.root == null) {
            this.parse();
        }

        return this.root.result();
    }

    /**
     * Spouští parsrování výrazu
     *
     * @throws Exception
     */
    public void parse() throws Exception {
        this.root = Or();
    }

    private Node Or() throws Exception {
        if (!lex.current().op ||
                lex.current().data == "(" ||
                lex.current().data == "not") {
            if (debug)
                System.out.println("O->XO'");
            Node l = Xor();
            return OrRest(l);
        }
        throw new Exception(mess);
    }

    private Node OrRest(Node left) throws Exception {
        if (lex.isEpsilon() || lex.current().data == ")") {
            if (debug)
                System.out.println("O'->eps");
            return left;
        }
        if (lex.current().data == "or") {
            if (debug)
                System.out.println("O'->or XO'");
            lex.move();
            Node right = Xor();
            Node or = new Or(left, right);
            return OrRest(or);
        }
        throw new Exception(mess);
    }

    private Node Xor() throws Exception {
        if (!lex.current().op ||
                lex.current().data == "(" ||
                lex.current().data == "not") {
            if (debug)
                System.out.println("X->AX'");
            Node left = And();
            return XorRest(left);
        }
        throw new Exception(mess);
    }

    private Node XorRest(Node left) throws Exception {
        if (lex.isEpsilon() ||
                lex.current().data == ")" ||
                lex.current().data == "or") {
            if (debug)
                System.out.println("X'->eps");
            return left;
        }
        if (lex.current().data == "xor") {
            if (debug)
                System.out.println("X'->xor AX'");
            lex.move();
            Node right = And();
            Node xor = new Xor(left, right);
            return XorRest(xor);
        }
        throw new Exception(mess);
    }

    private Node And() throws Exception {
        if (!lex.current().op ||
                lex.current().data == "(" ||
                lex.current().data == "not") {
            if (debug)
                System.out.println("A->TA'");
            Node left = Term();
            return AndRest(left);
        }
        throw new Exception(mess);
    }

    private Node AndRest(Node left) throws Exception {
        if (lex.isEpsilon() ||
                lex.current().data == ")" ||
                lex.current().data == "or" ||
                lex.current().data == "xor") {
            if (debug)
                System.out.println("A'->eps");
            return left;
        }
        if (lex.current().data == "and") {
            if (debug)
                System.out.println("A'->and TA'");
            lex.move();
            Node right = Term();
            Node xor = new And(left, right);
            return AndRest(xor);
        }
        throw new Exception(mess);
    }

    private Node Term() throws Exception {
        if(!lex.current().op) {
            if (debug)
                System.out.println("T->[a-zA-Z]");
            return new Terminal(lex.current().data);
        }
        if(lex.current().data == "("){
            if (debug)
                System.out.println("T->( O )");
            lex.move();
            Node o = Or();
            lex.move();
            return o;
        }
        if(lex.current().data == "not"){
            if (debug)
                System.out.println("T->not T");
            lex.move();
            Node n = Term();
            Node not = new Not(n);
            return not;
        }
        throw new Exception(mess);
    }
}
