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
public class TreeVisitor implements Visitor{

    String name;
    double value;

    /**
     * Constructorul
     * @param name numele feed-ului
     * @param value valoarea feed-ului
     */
    public TreeVisitor(String name, double value) {
        this.name = name;
        this.value = value;
    }
    
    /**
     * 
     * @param v nod te tip "and"
     * @return - true daca feed-ul este acceptat si la stanga, si la dreapta
     *         - false daca nu este acceptat intr-unul din cazuri
     */
    @Override
    public boolean visit(And v) {
        return v.left.accept(this) && v.right.accept(this);
    }

    /**
     * 
     * @param v nod de tip "or"
     * @return - true daca feed-ul este acceptat la stanga sau la dreapta
     *         - false daca nu este acceptat in niciunul din cazuri
     */
    @Override
    public boolean visit(Or v) {
        return v.left.accept(this) || v.right.accept(this);
    }

    /**
     * 
     * @param v nod de tip "eq name"
     * @return - true daca numele coincide cu cel din Tree
     *         - false altfel
     */
    @Override
    public boolean visit(EqName v) {
        return v.name.equals(name);
    }

    /**
     * 
     * @param v nod de tip "ne name"
     * @return - true daca numele nu coincide cu cel din Tree
     *         - false altfel
     */
    @Override
    public boolean visit(NeName v) {
        return !(v.name.equals(name));
    }

    /**
     * 
     * @param v nod de tip "eq value"
     * @return - true daca valoarea este egala cu cea din Tree
     *         - false altfel
     */
    @Override
    public boolean visit(EqValue v) {
        return v.value == value;
    }

    /**
     * 
     * @param v nod de tip "ne value"
     * @return - true daca valoarea nu este egala cu cea din Tree
     *         - false altfel
     */
    @Override
    public boolean visit(NeValue v) {
        return v.value != value;
    }

    /**
     * 
     * @param v nod de tip "le value"
     * @return - true daca valoarea este mai mica sau egala cu cea din Tree
     *         - false altfel
     */
    @Override
    public boolean visit(LeValue v) {
        return v.value >= value;
    }

    /**
     * 
     * @param v nod de tip "lt value"
     * @return - true daca valoarea este mai mica decat cea din Tree
     *         - false altfel
     */
    @Override
    public boolean visit(LtValue v) {
        return v.value > value;
    }

    /**
     * 
     * @param v nod de tip "ge value"
     * @return - true daca valoarea este mai mare sau egala cu cea din Tree
     *         - false altfel
     */
    @Override
    public boolean visit(GeValue v) {
        return v.value <= value;
    }

    /**
     * 
     * @param v nod de tip "gt value"
     * @return - true daca valoarea este mai mare decat cea din Tree
     *         - false altfel
     */
    @Override
    public boolean visit(GtValue v) {
        return v.value < value;
    }
    
}
