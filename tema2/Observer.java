/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import static tema2.ObserverList.verifyPrint;

/**
 *
 * @author Cristi
 * @version 1.0
 */
public class Observer {
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    
    /**
     * Am creat TreeMap-ul care memoreaza numele feed-urilor si valorile acestuia,
     * un HashMap care retine numarul unui feed neacceptat initial de catre un 
     * observer (numarul creste pana cand acesta este acceptat si atribuit unui
     * obiect de tipul ObserverValue, in campul count) si o lista cu aceste 
     * feed-uri neacceptate.
     */
    
    static TreeMap<String, ObserverValue> tm = new TreeMap<>();
    ObserverList observer = new ObserverList();
    Node nod;
    Map<CountFeedsNotAccepted, Integer> counter = new HashMap<>();
    ArrayList<CountFeedsNotAccepted> listCounter = new ArrayList<>();

    /**
     * Constructorul
     * Creez un nou obiect de tipul PrintStock pentru noul observer si il adaug
     * in lista de verificare a printarii, apoi apelez metoda "attach". In cazul
     * in care exista feed-uri declarate anterior crearii observer-ului le parcurg
     * si verific daca acesta accepta un feed. Daca accepta verific daca exista 
     * in TreeMap pentru a face modificarile aferente.
     * @param id id-ul observer-ului
     * @param observer observer-ul
     * @param nod nodul radacina al Tree-ului
     * @param feeds lista de feed-uri
     */
    public Observer (int id, ObserverList observer, Node nod, ArrayList<Feed> feeds) {
        this.observer = observer;
        this.nod = nod;
        
        int index = verifyPrint.size();

        PrintStock stock = new PrintStock(0, new String[10000], new boolean[10000], new boolean[10000]);
        verifyPrint.add(stock);
        
        for (Feed i : feeds) {
            if (nod.accept(new TreeVisitor(i.nume, i.value))) {
                if (tm.containsKey(i.nume)) {
                    ObserverValue obs = tm.get(i.nume);
                    obs.currentValue[id] = i.value;
                    obs.lastValue[id] = i.value;
                    obs.count[id] = 0;
                    tm.replace(i.nume, obs);
                }
                else {
                    double[] currentVal;
                    currentVal = new double[1000];
                    double[] lastVal;
                    lastVal = new double[1000];
                    int[] cnt;
                    cnt = new int[1000];
                    currentVal[id] = i.value;
                    lastVal[id] = i.value;
                    cnt[id] = 0;
                    ObserverValue obs = new ObserverValue(currentVal, lastVal, cnt);
                    tm.put(i.nume, obs);
                }
                if (!Arrays.asList(stock.name).contains(i.nume)) {
                    stock.id++;
                    stock.name[stock.id-1] = i.nume;
                    stock.print[stock.id-1] = true;
                    stock.firstPrint[stock.id-1] = true;
                }
                else {
                    int j = Arrays.asList(stock.name).indexOf(i.nume);
                    stock.print[j] = true;
                    stock.firstPrint[j] = true;
                } 
            }
            else {
                if (!Arrays.asList(stock.name).contains(i.nume)) {
                    stock.id++;
                    stock.name[stock.id-1] = i.nume;
                    stock.print[stock.id-1] = false;
                    stock.firstPrint[stock.id-1] = true;
                }
                else {
                    int j = Arrays.asList(stock.name).indexOf(i.nume);
                    stock.print[j] = false;
                    stock.firstPrint[j] = true;
                }  
                
                if (tm.containsKey(i.nume)) {
                    ObserverValue obs = tm.get(i.nume);
                    if (obs.count[id] != 0)
                        obs.count[id]++;
                    tm.replace(i.nume, obs);
                }
                else {
                    double[] currentVal;
                    currentVal = new double[1000];
                    double[] lastVal;
                    lastVal = new double[1000];
                    int[] cnt;
                    cnt = new int[1000];
                    currentVal[id] = i.value;
                    lastVal[id] = i.value;
                    cnt[id] = 0;
                    ObserverValue obs = new ObserverValue(currentVal, lastVal, cnt);
                    tm.put(i.nume, obs);
                }
            }
        }
        verifyPrint.set(index, stock);
        this.observer.attach(id, this);
    }

    
    /**
     * Verific daca numele feed-ului se afla deja in TreeMap. Daca se afla setez 
     * printarea pe false, urmand sa o trec true daca valoarea feed-ului a fost 
     * acceptata (adica, diferita de -1). Parcurg feed-urile neacceptate si 
     * verific daca feed-ul se afla in acea lista. Daca se afla pun in obiectul
     * de tipul ObserverValue la campul "count" valoarea din counter, crescuta 
     * cu 1. Altfel, il incrementez, iar apoi il adaug inapoi in TreeMap. Daca 
     * nu se afla in TreeMap verific daca feed-ul a fost sau nu acceptat. Daca 
     * a fost acceptat parcurg feed-urile neacceptate ca mai devreme, iar apoi 
     * il adaug in counter. Creez si obiectul cu valorile, pun valorile si le 
     * adaug in TreeMap. Daca nu-l accepta setez printarea pe false si parcurg 
     * din nou feed-urile neacceptate pentru a face modificarile necesare.
     * @param name numele feed-ului
     * @param value valoarea feed-ului
     * @param id id observer
     */
    public void replace (String name, double value, int id) {

        int index = observer.ids.indexOf(id);
        PrintStock stock = verifyPrint.get(index);
        int i = Arrays.asList(stock.name).indexOf(name);
        if (tm.containsKey(name)) {
            stock.print[i] = false;
            ObserverValue obs = tm.get(name);

           	CountFeedsNotAccepted count = null;
            int verify = 0;
            for (CountFeedsNotAccepted c : listCounter) {
                if (c.id == id && c.name.equals(name)) {
                    verify = 1;
                    count = c;
                    break;
                }
            }
            if (verify == 1) {
            	counter.replace(count, counter.get(count)+1);
            	obs.count[id] = counter.get(count);
            	listCounter.remove(listCounter.indexOf(count));
            }
            else
            	obs.count[id]++;

                
            if (value != -1) {
                obs.currentValue[id] = value;
                stock.print[i] = true;
            }
            tm.replace(name, obs);
            verifyPrint.set(index, stock);
        }
        else {
            if (value != -1) {
                CountFeedsNotAccepted count = null;
                int verify = 0;
                for (CountFeedsNotAccepted c : listCounter) {
                    if (c.id == id && c.name.equals(name)) {
                        verify = 1;
                        count = c;
                        break;
                    }
                }
                if (verify == 0) {
                    count = new CountFeedsNotAccepted(id, name);
                    counter.put(count, 1);
                }
                else {
                    counter.replace(count, counter.get(count)+1);
                }  
                
                double[] currentVal;
                currentVal = new double[1000];
                double[] lastVal;
                lastVal = new double[1000];
                int[] cnt;
                cnt = new int[1000];
                currentVal[id] = value;
                lastVal[id] = value;
                cnt[id] = counter.get(count);
                ObserverValue obs = new ObserverValue(currentVal, lastVal, cnt);
                tm.put(name, obs);
            }
            else {
                boolean ok = true;
                stock.print[i] = false;
                for (CountFeedsNotAccepted c : listCounter)
                    if (c.id == id && c.name.equals(name)) {
                        counter.replace(c, counter.get(c)+1);
                        ok = false;
                    }
                if (ok) {
                    CountFeedsNotAccepted count = new CountFeedsNotAccepted(id, name);
                    listCounter.add(count);
                    counter.put(count, 1);
                }
            }
        }
    }
    
    /**
     * Parcurg tot TreeMap-ul si verific daca observer-ul a acceptat un feed de 
     * acolo, dar si daca poate fi printat sau nu. Afisez o parte din output, 
     * apoi verific daca ma aflu la prima printare. Daca da, setez fluctuatia la 
     * 0 si firstPrint la false. La final afisez restul, modific valorile (ultima
     * devine prima) si numarul de aparitii (count).
     * @param id id-ul observer-ului
     */
    public void print (int id) {
        int index = observer.ids.indexOf(id);
        tm.entrySet().forEach((_item) -> {
            PrintStock stock = verifyPrint.get(index);
            String[] nameStocks = stock.name;
            int i = Arrays.asList(nameStocks).indexOf(_item.getKey());
            if (Arrays.asList(nameStocks).contains(_item.getKey())
                    && stock.print[i]) {
                System.out.print("obs " + id + ": " + _item.getKey() + " ");
                ObserverValue obs = _item.getValue();
                double percent;
                if (stock.firstPrint[i]) {
                    percent = 0.00;
                    stock.firstPrint[i] = false;
                    verifyPrint.set(index, stock);
                }
                else
                    percent = (obs.currentValue[id] - obs.lastValue[id]) / obs.lastValue[id] * 100;
                System.out.println(decimalFormat.format(obs.currentValue[id]) + " " + 
                        decimalFormat.format(percent) + "% " + obs.count[id]);
                obs.lastValue[id] = obs.currentValue[id];
                obs.count[id] = 0;
            } 
        });
        
    }
}
