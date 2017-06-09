package com.github.cocojumbo.calc;

import com.github.cocojumbo.calc.operators.AppendOperator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import com.github.cocojumbo.calc.Operators;

public class ParceTreeTest {
    @Test
    public void findPriorityOperatorNodeTest() {

        //assertArrayEquals();
    }

    @Test
    public void testBuildInit(){
        Node head = new ParceTree().build("1+2");
        assertTrue(head.operator instanceof AppendOperator);
        assertEquals(head.getlNode().value, new Long(1));
        assertEquals(head.getrNode().value, new Long(2));
    }

}
