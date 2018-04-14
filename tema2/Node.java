/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

/**
 *
 * @author Cristi
 * @version 1.0
 */
public class Node implements Visitable{
    
    /**
     * Nodul din stanga si cel din dreapta a tree-ului.
     */
    public Node left;
    public Node right;

    /**
     * Constructorul cu parametri
     * @param left copilul din stanga
     * @param right copilul din dreapta
     */
    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Constructorul gol
     */
    public Node() {
    }

    /**
     * Metoda de accept a visitor-ului
     * @param v visitor
     * @return 
     */
    @Override
    public boolean accept(Visitor v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
