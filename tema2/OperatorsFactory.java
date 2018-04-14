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
public class OperatorsFactory {
    
    
    /**
     * Metoda primeste caracterul obtinut dupa parsare si in functie de acesta
     * returneaza nodul bun cu valoarea corespunzatoare ca parametru.
     * @param op caracterul obtinut dupa parsare
     * @param str sirul ce urmeaza dupa operator
     * @return tipul de nod
     */
    public static Node getOperator(char op, String str) {
        switch (op) {
            case '-':
                return new LeValue(Double.parseDouble(str));
            case '+':
                return new GeValue(Double.parseDouble(str));
            case '<':
                return new LtValue(Double.parseDouble(str));
            case '>':
                return new GtValue(Double.parseDouble(str));
            case '!':
                return new NeValue(Double.parseDouble(str));
            case '=':
                return new EqValue(Double.parseDouble(str));
            case 'n':
                return new NeName(str);
            case 'e':
                return new EqName(str);
        }
        return null;
    }
}
