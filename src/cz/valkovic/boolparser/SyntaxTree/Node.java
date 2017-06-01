package cz.valkovic.boolparser.SyntaxTree;

import java.util.Set;

/**
 * Node je základni prvek stromu pro SyntaxAnalyzu Potomci: AND, NOT, OR, Terminal
 *
 * @author ondrej
 */
public abstract class Node {

    public static Set<String> all;

    public abstract Set<String> result();

}
