/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

/**
 * Nodul de tip "and" al arborelui.
 * @author Cristi
 * @version 1.0
 */
public class And extends Node implements Visitable{

    /**
     * Constructorul cu parametri
     * @param left fiul stang al nodului parinte
     * @param right fiul drept al nodului parinte
     */
    public And(Node left, Node right) {
        super(left, right);
    }
    
    /**
     * Constructorul fara parametri.
     */
    public And() {
        
    }
    
    /**
     * Metoda de acceptare a visitor-ului
     * @param v visitor
     * @return metoda "visit" cu parametrul acestui nod
     */
    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }
}
