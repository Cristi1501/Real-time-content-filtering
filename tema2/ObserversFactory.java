/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema2;

import java.util.Stack;

/**
 * 
 * @author Cristi
 * @version 1.0
 */
public class ObserversFactory {
    
    /**
     * 
     * Aceasta metoda parseaza toata expresia si inlocuieste termenii specifici
     * cu un singur caracter ("le value" devine "-"). La final elimin spatiile 
     * ramase si returnez expresia.
     * 
     * @param expression - expresia initiala citita, neparsata
     * @return expresia parsata
     */
    private static String filter(String expression) {
        expression = expression.replaceAll("\\( *le value ([0-9\\.]+) *\\)", "-$1");
        expression = expression.replaceAll("\\( *ge value ([0-9\\.]+) *\\)", "+$1");
        expression = expression.replaceAll("\\( *lt value ([0-9\\.]+) *\\)", "<$1");
        expression = expression.replaceAll("\\( *gt value ([0-9\\.]+) *\\)", ">$1");
        expression = expression.replaceAll("\\( *ne value ([0-9\\.]+) *\\)", "!$1");
        expression = expression.replaceAll("\\( *eq value ([0-9\\.]+) *\\)", "=$1");
        expression = expression.replaceAll("\\( *ne name ([a-zA-Z]+) *\\)", "n$1");
        expression = expression.replaceAll("\\( *eq name ([a-zA-Z]+) *\\)", "e$1");
        expression = expression.replaceAll("\\&\\&", "&");
        expression = expression.replaceAll("\\|\\|", "|");
        expression = expression.replaceAll(" +", "");
        return expression;
    }
    
    
    /**
     * 
     * Aceasta metoda apeleaza metoda de parsare, apoi pe baza pseudocodului din
     * link-ul oferit in enuntul temei am implementat algoritmul Shunting Yard.
     * 
     * @param expression - expresia initiala citita, neparsata
     * @return stiva cu elementele in ordine pentru crearea Tree-ului
     */
    public static Stack<Node> shuntingYard(String expression) {
        expression = filter(expression);
        int index = 0;
        Stack<Node> output = new Stack<>();
        Stack<Node> operator = new Stack<>();
        while (index < expression.length()) {
            switch (expression.charAt(index)) {
                case '(':
                    operator.push(new Bracket());
                    break;
                case ')':
                    while (!operator.isEmpty() && !(operator.peek() instanceof Bracket))
                        output.push(operator.pop());
                    operator.pop();
                    break;
                case '&':
                    while (!operator.isEmpty() && !(operator.peek() instanceof Bracket)) 
                        output.push(operator.pop());
                    operator.push(new And());
                    break;
                case '|':
                    operator.push(new Or());
                    break;
                default:
                    int index2 = index+1;
                    while (expression.length() != index2 && expression.charAt(index2) != '&' 
                            && expression.charAt(index2) != '|' && expression.charAt(index2) != ')')
                        index2++;
                    output.push(OperatorsFactory.getOperator(expression.charAt(index), 
                            expression.substring(index + 1, index2)));
                    index = index2 - 1;
                    break;
            }
            index++;
        }
        while (!operator.isEmpty())
            output.push(operator.pop());
        
        return output;
    }
}
