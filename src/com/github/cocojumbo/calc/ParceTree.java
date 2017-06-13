package com.github.cocojumbo.calc;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParceTree {
    private final static String NUMERIC = "\\d+";
    private final static Pattern required = Pattern.compile(NUMERIC + "|" + Operations.RAWPATTERN);

    private Node head = null;
    private Node bufferedOperand  = null;
    private Node bufferedOperator = null;

    public Node findPriorityPositionNode(Operator operator){
        Node iterator = head;
        while(iterator.operator.getPriority() < operator.getPriority()){
            iterator = iterator.getrNode();
            if(iterator.operator == null){
                break;
            }
        }
        return iterator;
    }

    public Node build(String expression){
        clear();
        Matcher mRequired = required.matcher(expression);
        while(mRequired.find()) {
            String group = mRequired.group();
            if(group.matches(Operations.RAWPATTERN)) {
                Node operatorNode = new Node(Operations.getBySymbol(group));

                if(bufferedOperator == null) {
                    bufferedOperator = operatorNode;
                }
                if(head == null) {
                    head = bufferedOperator;
                }/* else {

                }*/
            }
            if(group.matches(NUMERIC)) {
                Node value = new Node(new BigDecimal(group));

                if(bufferedOperand != null){
                    bufferedOperator.setlNode(bufferedOperand);
                    bufferedOperand = null;
                }
                if (head == null){
                    bufferedOperand = value;
                }
                if(bufferedOperator != null){
                    bufferedOperator.setrNode(value);
                }
            }
        }
        return head;
    }

    private void clear(){
        head = null;
        bufferedOperand  = null;
        bufferedOperator = null;
    }

    public Node getHead(){
        return head;
    }
}
