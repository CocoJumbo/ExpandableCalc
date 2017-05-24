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
            if(iterator.getrNode().operator != null){
                iterator = iterator.getrNode();
            }
        }
        return iterator;
    }

    public Long calculate(String expression) {
        Matcher mRequired = required.matcher(expression);

        while(mRequired.find()) {
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
                }

            }
            if(group.matches(NUMERIC)) {
                System.out.println(group);
                Node value = new Node(Long.parseLong(group));
                if(bufferedOperand == null) {
                    bufferedOperand = value;
                } else {
                    bufferedOperator.setlNode(bufferedOperand);
                    bufferedOperator.setrNode(value);
                    bufferedOperator = null;
                    //System.out.println(head.operator);
                    //System.out.println(head.lNode.value);
                    //System.out.println(head.rNode.value);
                }
            }

        }

        Node n = head;
        while((n = n.getrNode()) != null) {
            if(n.operator != null)
                System.out.println(n.operator);
            if(n.getrNode() != null)
                System.out.println(n.getlNode().value);
            if(n.getlNode() != null)
                System.out.println(n.getrNode().value);
        }

        return null;
    }
}
