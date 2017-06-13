package com.github.cocojumbo.calc;

import com.github.cocojumbo.calc.operators.AppendOperator;
import com.github.cocojumbo.calc.operators.MultiplicationOperator;
import com.github.cocojumbo.calc.operators.PowOperator;
import com.github.cocojumbo.calc.operators.SubstractOperator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ParceTreeTest {
    private ParceTree parceTree;

    @Before
    public void prepare(){
        this.parceTree = new ParceTree();
    }

    @Test
    public void findPriorityPositionNodeTest() {
        parceTree.build("1+2");
        assertEquals(parceTree.findPriorityPositionNode(new MultiplicationOperator()),
                        parceTree.getHead().getrNode());
        parceTree.build("1*2");
        assertEquals(parceTree.findPriorityPositionNode(
               new SubstractOperator()), parceTree.getHead());
    }

    @Test
    public void testBuildInit(){
        Node head = parceTree.build("1+2");
        assertTrue(head.operator instanceof AppendOperator);
        assertEquals(head.getlNode().value, new BigDecimal(1));
        assertEquals(head.getrNode().value, new BigDecimal(2));
    }

    @Test
    public void testBuild1(){
        Node head = parceTree.build("1+2*3^4");
        assertTrue(head.operator instanceof AppendOperator);
        assertEquals(head.getlNode().value.intValue(), 1);
        Node headR = head.getrNode();
        assertTrue(headR.operator instanceof MultiplicationOperator);
        assertEquals(headR.getlNode().value.intValue(), 2);
        assertTrue(headR.getrNode().operator instanceof PowOperator);
        Node headRR = headR.getrNode();
        assertEquals(headRR.getlNode().value.intValue(), 3);
        assertEquals(headRR.getrNode().value.intValue(), 4);
    }

    @Test
    public void testBuild2(){
        Node head = parceTree.build("1*2-3^4-5");
        assertTrue(head.operator instanceof SubstractOperator);
        assertEquals(head.getrNode().value.intValue(), 5);
        assertTrue(head.getlNode().operator instanceof SubstractOperator);
        Node headL = head.getlNode();
        assertTrue(headL.getlNode().operator instanceof MultiplicationOperator);
        assertTrue(headL.getrNode().operator instanceof PowOperator);
        Node headLL = headL.getlNode();
        assertEquals(headLL.getlNode().value.intValue(), 1);
        assertEquals(headLL.getrNode().value.intValue(), 2);
        Node headLR = headL.getrNode();
        assertEquals(headLR.getlNode().value.intValue(), 3);
        assertEquals(headLR.getrNode().value.intValue(), 4);
    }
}
