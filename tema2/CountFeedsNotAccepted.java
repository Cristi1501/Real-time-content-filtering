/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

/**
 * Clasa pe baza careia se creeaza obiecte care nu sunt acceptate in Tree din 
 * cauza valorii, nu a numelui feed-ului...
 * @author Cristi
 * @version 1.0
 */
public class CountFeedsNotAccepted {
    int id;
    String name;

    /**
     * Constructorul
     * @param id id observer
     * @param name nume feed
     */
    public CountFeedsNotAccepted(int id, String name) {
        this.id = id;
        this.name = name;
    }

    
    
}
