/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

import java.util.Stack;

/**
 *
 * @author Cristi
 * @version 1.0
 */
public class TreeConstruction {
    
    /**
     * Metoda de creare a arborelui binar.
     * @param stack stiva cu elementele ordonate conform algoritmului Shunting Yard
     * @return nodul radacina al arborelui
     */
    public static Node create(Stack<Node> stack) {
        Node nod = stack.pop();
        Node nod1;
        Node nod2;
        if (nod instanceof And || nod instanceof Or) {
            if (stack.peek() instanceof And || stack.peek() instanceof Or)
                nod1 = create(stack);
            else {
                nod1 = stack.pop();
            }
            if (stack.peek() instanceof And || stack.peek() instanceof Or)
                nod2 = create(stack);
            else {
                nod2 = stack.pop();
            }
            nod.left = nod1;
            nod.right = nod2;
            return nod;
        }
        else
            return nod;
    } 
}
