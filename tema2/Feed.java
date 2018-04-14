/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

/**
 * Clasa pe baza careia se creeaza un element de tip feed (pentru a fi memorat)
 * @author Cristi
 * @version 1.0
 */
public class Feed {
    String nume;
    double value;

    /**
     * Constructorul
     * @param nume numele feed-ului 
     * @param value valoarea feed-ului
     */
    public Feed(String nume, double value) {
        this.nume = nume;
        this.value = value;
    }
    
    
}
