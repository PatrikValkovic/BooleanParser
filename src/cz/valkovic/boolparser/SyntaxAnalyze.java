package cz.valkovic.boolparser;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import cz.valkovic.boolparser.SyntaxTree.*;
import java.util.Set;


public class SyntaxAnalyze {

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
        this.root = Expresion();
    }

    private Node Or() throw Exception {

    }

    private Node OrRest(Node left) throw Exception {

    }

    private Node Xor() throw Exception {

    }

    private Node XorRest() throw Exception {

    }

    private Node And() throw Exception {

    }

    private Node AndRest() throw Exception {

    }

    private Node Term() throw Exception {

    }

    private Node Expresion() throws Exception {
        if (this.lex.current().op == false) {
            if (debug) {
                System.out.println("E->TE'");
            }
            Node t = Term();
            return ZbExpresion(t);
        } else if (this.lex.current().data == "(") {
            if (debug) {
                System.out.println("E->TE'");
            }
            Node t = Term();
            return ZbExpresion(t);
        } else if (this.lex.current().data == "not") {
            if (debug) {
                System.out.println("E->TE'");
            }
            Node t = Term();
            return ZbExpresion(t);
        } else {
            throw new Exception("Chyba parseru -> Expresion");
        }
    }

    private Node ZbExpresion(Node left) throws Exception {
        if (this.lex.isEpsilon()) {
            if (debug) {
                System.out.println("E'->epsilon");
            }
            return left;
        } else if (this.lex.current().data == "or") {
            if (debug) {
                System.out.println("E'->or TE'");
            }
            this.lex.move();
            Node right = Term();
            Node or = new Or(left, right);
            return ZbExpresion(or);
        } else if (this.lex.current().data == ")") {
            if (debug) {
                System.out.println("E'->epsilon");
            }
            return left;
        } else {
            throw new Exception("Chyba parseru -> ZbExpresion");
        }
    }

    private Node Term() throws Exception {
        if (this.lex.current().op == false) {
            if (debug) {
                System.out.println("T->FT'");
            }
            Node f = Factor();
            return ZbTerm(f);
        } else if (this.lex.current().data == "(") {
            if (debug) {
                System.out.println("T->FT'");
            }
            Node f = Factor();
            return ZbTerm(f);
        } else if (this.lex.current().data == "not") {
            if (debug) {
                System.out.println("T->FT'");
            }
            Node f = Factor();
            return ZbTerm(f);
        } else {
            throw new Exception("Chyba parseru -> Term");
        }
    }

    private Node ZbTerm(Node left) throws Exception {
        if (this.lex.isEpsilon()) {
            if (debug) {
                System.out.println("T'->epsilon");
            }
            return left;
        } else if (this.lex.current().data == "or") {
            if (debug) {
                System.out.println("T'->epsilon");
            }
            return left;
        } else if (this.lex.current().data == "and") {
            if (debug) {
                System.out.println("T'->and FT'");
            }
            this.lex.move();
            Node right = Factor();
            Node and = new And(left, right);
            return ZbTerm(and);
        } else if (this.lex.current().data == ")") {
            if (debug) {
                System.out.println("T'->epsilon");
            }
            return left;
        } else {
            throw new Exception("Chyba parseru -> ZbTerm");
        }

    }

    private Node Factor() throws Exception {
        if (this.lex.current().op == false) {
            if (debug) {
                System.out.println("F->a");
            }
            Node t = new Terminal(this.lex.current().data);
            this.lex.move();
            return t;
        } else if (this.lex.current().data == "(") {
            if (debug) {
                System.out.println("F->( E )");
            }
            this.lex.move();
            Node e = Expresion();
            this.lex.move();
            return e;
        } else if (this.lex.current().data == "not") {
            if (debug) {
                System.out.println("F->not F");
            }
            this.lex.move();
            Node f = Factor();
            Node not = new Not(f);
            return not;
        } else {
            throw new Exception("Chyba parseru -> Factor");
        }

    }

}
