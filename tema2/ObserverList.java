/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

import java.util.ArrayList;
import java.util.Arrays;
import static tema2.Observer.tm;

/**
 *
 * @author Cristi
 * @version 1.0
 */
public class ObserverList {
    
    /**
     * Am creat o lista de observeri si una de id-uri in care se adauga si din 
     * care se scoat mereu in acelasi timp elementele pentru a sti ce id are 
     * fiecare observer in orice moment de timp. Cred ca as fi putut folosi si un 
     * Map, dar am realizat tarziu si nu am mai modificat.
     * 
     * Am creat o lista cu elemente de tip PrintStock pe care o folosesc pentru a
     * memora date despre observeri (numele feed-ului, daca ultimul feed a fost 
     * bun si observer-ul poate fi printat, dar si daca a mai fost printat pana
     * la acel moment sau se afla la prima printare.
     */
    public ArrayList<Observer> observers = new ArrayList<>();
    public ArrayList<Integer> ids = new ArrayList<>();
    static ArrayList<PrintStock> verifyPrint = new ArrayList<>();
    
    /**
     * Metoda de adaugare a observer-ului si a id-ului acestuia
     * @param id id observer
     * @param obs  observer
     */
    public void attach (int id, Observer obs) {
        observers.add(obs);
        ids.add(id);
    }
    
    /**
     * Metoda de eliminare a observer-ului, id-ului si datelor legate de acel 
     * observer (feed-urile catre acel observer sunt modificate si ele)...
     * @param id id observer
     * @param feeds lista de feed-uri
     */
    public void delete (int id, ArrayList<Feed> feeds) {
        int index = ids.indexOf(id);
        observers.remove(index);
        ids.remove(index);
        verifyPrint.remove(index);
        
        for (Feed f : feeds) {
            if (tm.containsKey(f.nume)) {
                ObserverValue obs = tm.get(f.nume);
                obs.currentValue[id] = -1;
                obs.lastValue[id] = -1;
                obs.count[id] = 0;
                tm.replace(f.nume, obs);
            }
        }
    }
    
    /**
     * Metoda de modificare...
     * Parcurge pas cu pas observer-ii si adauga in "verifyPrint" informatiile 
     * necesare (cele de la inceput). Apoi verifica daca feed-ul este acceptat de 
     * acel observer si apeleaza metoda "replace".
     * @param name numele feed-ului
     * @param value valoarea feed-ului
     */
    public void modify (String name, double value) {
        for (Observer obs : observers) {
            int index = observers.indexOf(obs);
            int id = ids.get(index);
            PrintStock stock = verifyPrint.get(index);
            if (!Arrays.asList(verifyPrint.get(index).name).contains(name)) {
                stock.id++;
                stock.name[stock.id-1] = name;
                stock.print[stock.id-1] = true;
                stock.firstPrint[stock.id-1] = true;
                verifyPrint.set(index, stock);
            }
            else {
                int i = Arrays.asList(stock.name).indexOf(name);
                stock.print[i] = true;
                verifyPrint.set(index, stock);
            }
            
            if (obs.nod.accept(new TreeVisitor(name, value))) {
                obs.replace(name, value, id);
            }     
            else {
                obs.replace(name, -1, id);
            }
        }
            
    }
    
    /**
     * Metoda de afisare...
     * Apeleaza metoda "print" din clasa Observer.
     * @param id id-ul observer-ului
     */
    public void show (int id) {
        int index = ids.indexOf(id);
        Observer obs = observers.get(index);
        obs.print(id);
    }
}
