/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

/**
 * Clasa pe baza careia se creeaza obiecte ce retin informatiile de printare
 * despre feed-urile primite la input... Aceste obiecte se actualizeaza in 
 * functie de id-ul observer-ului si pe baza lor verific daca un observer
 * poate fi printat sau nu (daca ultimul feed a fost bun pentru acel observer
 * sau nu), dar si daca printarea acestuia se intampla pentru prima oara 
 * (asta ma ajuta sa separ cele doua cazuri, cel in care fluctuatia e 0.00% si
 * cel in care trebuie sa o calculez).
 * 
 * @author Cristi
 * @version 1.0
 */
public class PrintStock {
    int id;
    String[] name;
    boolean[] print;
    boolean[] firstPrint;

    /**
     * Constructorul
     * @param id id-ul observer-ului
     * @param name vectorul cu numele feed-urilor 
     * @param print vectorul cu informatiile de printare a feed-urilor
     * @param firstPrint vectorul cu informatiile de printare pentru prima oara a feed-urilor
     */
    public PrintStock(int id, String[] name, boolean[] print, boolean[] firstPrint) {
        this.id = id;
        this.name = name;
        this.print = print;
        this.firstPrint = firstPrint;
    }
}
