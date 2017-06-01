package BooleanParser;

import java.util.Set;
import pkgboolean.BooleanTable;
import BooleanParser.SyntaxTree.And;
import BooleanParser.SyntaxTree.Node;
import BooleanParser.SyntaxTree.Not;
import BooleanParser.SyntaxTree.Or;
import BooleanParser.SyntaxTree.Terminal;

/*

LL1 gramatika:

(1)E->TE'
(2)E'->or TE'
(3)E'->epsilon
(4)T->FT'
(5)T'->and FT'
(6)T'->epsilon
(7)F->a
(8)F->not F
(9)F->( E )


Parsovací tabulka pro gramatiku:

    a   +   *   (   )   not epsilon
E   1           1       1
E'      2           3       3
T   4           4       4
T'      6   5       6       6
F   7           9       8

 *
 * @author ondrej
 */
public class SyntaxAnalyze {

    private static boolean debug = true;

    private LexAnalyze lex;
    private BooleanTable table;

    private Node root;

    /**
     * Příjmá LexAnalyze a BooleanTable pro vyhledávání
     *
     * @param l
     * @param t
     */
    public SyntaxAnalyze(LexAnalyze l, BooleanTable t) {
        this.lex = l;
        this.table = t;
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
            Node t = new Terminal(this.lex.current().data, this.table);
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
