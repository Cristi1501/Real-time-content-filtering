/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

/**
 * Clasa pe baza careia se creeaza obiecte ce memoreaza informatii despre valoarea
 * curenta, ultima valoare si numarul de modificari ale fiecarui observer. Aceste
 * obiecte sunt folosite intr-un Treemap (vezi clasa Observer) care are o cheie
 * de tip String ce memoreaza numele feed-ului. Asadar, in functie de numele feed-ului
 * vom completa vectorii pentru fiecare observer.
 * @author Cristi
 * @version 1.0
 */
public class ObserverValue {
    double[] currentValue;
    double[] lastValue;
    int[] count;

    /**
     * Constructorul
     * @param currentValue vectorul ce memoreaza valoarea curenta 
     * @param lastValue vectorul ce memoreaza ultima valoare inainte de printare
     * @param count vectorul ce memoreaza modificarile feed-ului respectiv
     */
    public ObserverValue(double[] currentValue, double[] lastValue, int[] count) {
        this.currentValue = currentValue;
        this.lastValue = lastValue;
        this.count = count;
    }
    
    
}
