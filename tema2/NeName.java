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
public class NeName extends Node implements Visitable{
    String name;

    /**
     * Constructorul
     * @param name numele feed-ului
     */
    public NeName(String name) {
        this.name = name;
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
