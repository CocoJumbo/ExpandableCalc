package com.github.cocojumbo.calc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParceTree {
    private final static String NUMERIC = "\\d+";
    private final static Pattern required = Pattern.compile(NUMERIC + "|" + Operators.RAWPATTERN);

    private Node head = null;
    private Node bufferedOperand  = null;
    private Node bufferedOperator = null;

    private Node findPriorityOperatorNode(Operator operator){
        Node iterator = head;
        while(iterator.operator.getPriority() < operator.getPriority()){
            if(iterator.getrNode().isOperator()){
                iterator = iterator.getrNode();
            } else {
                break;
            }
        }
        return iterator;
    }

    public Long calculate(String expression) throws NullPointerException{
        Matcher mRequired = required.matcher(expression);
        while(mRequired.find()) {
            System.out.println("step was executed 1");
            String group = mRequired.group();
            if(group.matches(Operators.RAWPATTERN)) {
                Node operatorNode = new Node(Operators.getBySymbol(group));
                Operator operator = operatorNode.operator;
                if(bufferedOperator == null) {
                    bufferedOperator = operatorNode;
                }
                if(head == null) {
                    head = bufferedOperator;
                }
                if(head.operator.getPriority() < operator.getPriority()){
                    Node priorityOperatorNode = findPriorityOperatorNode(operator);
                    operatorNode.setlNode(priorityOperatorNode.getrNode());
                    priorityOperatorNode.setrNode(operatorNode);
                    bufferedOperator = operatorNode;
                }else{
                    operatorNode.setlNode(head);
                    head = operatorNode;
                }
            }
            if(group.matches(NUMERIC)) {
                Node value = new Node(Long.parseLong(group));

                if(bufferedOperand != null){
                    bufferedOperator.setlNode(bufferedOperand);
                    bufferedOperand = null;
                }
                if (head == null){
                    bufferedOperand = value;
                }

                if(bufferedOperator != null && !bufferedOperator.hasRightNode()) {
                    bufferedOperator.setrNode(value);
                    bufferedOperand = null;
                }
            }
        }
        return null;
    }
}
