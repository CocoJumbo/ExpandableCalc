package com.github.cocojumbo.calc;

import com.github.cocojumbo.calc.operators.AppendOperator;
import com.github.cocojumbo.calc.operators.MultiplicationOperator;
import com.github.cocojumbo.calc.operators.SubstractOperator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ParceTreeTest {
    private ParceTree parceTree;

    /*@Before
    public void prepare(){
        this.parceTree = new ParceTree();
    }*/

    @Test
    public void findPriorityPositionNodeTest() {
        ParceTree parceTree = new ParceTree();
        parceTree.build("1+2");
        assertEquals(parceTree.findPriorityPositionNode(new MultiplicationOperator()),
                        parceTree.getHead().getrNode());
        parceTree = new ParceTree();
        parceTree.build("1*2");
        assertEquals(parceTree.findPriorityPositionNode(
               new SubstractOperator()), parceTree.getHead());
    }

    @Test
    public void testBuildInit(){
        ParceTree parceTree = new ParceTree();
        Node head = parceTree.build("1+2");
        assertTrue(head.operator instanceof AppendOperator);
        assertEquals(head.getlNode().value, new BigDecimal(1));
        assertEquals(head.getrNode().value, new BigDecimal(2));
    }

}
