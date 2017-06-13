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
                bufferedOperator = new Node(Operations.getBySymbol(group));
                if(head == null) {
                    head = bufferedOperator;
                } else {
                    Node targetNode = findPriorityPositionNode(bufferedOperator.operator);
                    if(targetNode.isOperator()){
                        if(targetNode == head){
                            head = bufferedOperator;
                            head.setlNode(targetNode);
                        } else {
                            targetNode.setlNode(bufferedOperator);
                        }
                    }
                    if(targetNode.isOperand()){
                        targetNode.getParentNode().setrNode(bufferedOperator);
                        bufferedOperator.setlNode(targetNode);
                    }
                }
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
        bufferedOperand  = null;
        bufferedOperator = null;
        head = null;
    }

    public Node getHead(){
        return head;
    }
}
