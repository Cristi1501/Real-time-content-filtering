/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

/**
 * Interfata cu declaratiile metodelor din TreeVisitor - vezi explicatii acolo
 * @author Cristi
 * @version 1.0
 */
public interface Visitor {
    public boolean visit(And v);
    public boolean visit(Or v);
    public boolean visit(EqName v);
    public boolean visit(NeName v);
    public boolean visit(EqValue v);
    public boolean visit(NeValue v);
    public boolean visit(LeValue v);
    public boolean visit(LtValue v);
    public boolean visit(GeValue v);
    public boolean visit(GtValue v);
}
