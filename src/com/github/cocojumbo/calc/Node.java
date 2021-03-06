package com.github.cocojumbo.calc;
import java.math.BigDecimal;

public class Node {
    public BigDecimal value       = null;
    public Operator operator    = null;
    private Node    rNode       = null;
    private Node    lNode       = null;
    private Node    parentNode  = null;

    public Node(BigDecimal value) {
        this.value = value;
    }

    public Node(Operator operator) {
        this.operator = operator;
    }


    public Node(Operator operator, Node lNode, Node rNode) {
        this.operator = operator;
        this.rNode = rNode;
        this.lNode = lNode;
    }

    public Node getrNode() {
        return rNode;
    }

    public void setrNode(Node rNode) {
        this.rNode = rNode;
        rNode.parentNode = this;
    }

    public Node getlNode() {
        return lNode;
    }

    public void setlNode(Node lNode) {
        this.lNode = lNode;
        lNode.parentNode = this;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public boolean hasRightNode(){ return rNode != null; }

    public void calcResult() {
        this.value = operator.apply(lNode.value, rNode.value);
    }

    public boolean isOperand() {
        return value != null;
    }

    public boolean isOperator() {
        return operator != null;
    }
}
