package cz.valkovic.boolparser.SyntaxTree;

/*
 * Created by Patrik Valkovic
 * Part of BooleanParser
 */

import java.util.Set;


public abstract class Node {

    public static Set<String> all;

    public abstract Set<String> result();

}
