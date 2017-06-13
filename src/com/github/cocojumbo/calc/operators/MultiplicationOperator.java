package com.github.cocojumbo.calc.operators;

import com.github.cocojumbo.calc.Operator;

import java.math.BigDecimal;

public class MultiplicationOperator implements Operator {
    public BigDecimal apply(BigDecimal o1, BigDecimal o2) { return o1.multiply(o2); }
    public int getPriority() { return  200; }
}