/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author Cristi
 * @version 1.0
 */
public class Tema2 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * 
     * Imi declar o lista de feed-uri (aceasta memoreaza absolut toate feed-urile
     * pentru a le putea adauga si observer-ilor creati mai tarziu) si citesc 
     * primul element (begin). Dupa aceea citesc prima comanda si verific de ce fel
     * este aceasta. Daca trebuie creat un observer citesc id-ul si expresia, apoi
     * aplic Shunting Yard pe aceasta, imi creez Tree-ul si adaug observerul. Daca
     * trebuie sters/printat un observer citesc id-ul acestuia si apelez metoda de
     * eliminare/printare. Daca trebuie adaugat un feed citesc numele si valoarea
     * acestuia, creez un obiect de tipul feed pe care il adaug in lista de feed-uri
     * si apelez metoda de modificare.
     * 
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);
        ObserverList observers = new ObserverList();
        ArrayList<Feed> feeds = new ArrayList<>();
        String input = s.next();
        
        input = s.next();
        while (!input.equals("end")) {
            if (input.equals("create_obs")) {
                int id = s.nextInt();
                String expression = s.nextLine();
                expression = expression.substring(1);
                
                Stack<Node> stack = ObserversFactory.shuntingYard(expression);
                Node tree = TreeConstruction.create(stack);
                Observer observer = new Observer(id, observers, tree, feeds);     
            }
            
            if (input.equals("delete_obs")) {
                int id = s.nextInt();
                observers.delete(id, feeds);
            }
            
            if (input.equals("print")) {
                int id = s.nextInt();
                observers.show(id);
            }
            
            if (input.equals("feed")) {
                String name = s.next();
                double value = s.nextDouble();
                Feed feed = new Feed(name, value);
                feeds.add(feed);
                observers.modify(name, value);
            }
            
            input = s.next();
        }
    }
    
}
