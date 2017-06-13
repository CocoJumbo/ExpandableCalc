package com.github.cocojumbo.calc.operators;

import java.math.*;
import com.github.cocojumbo.calc.Operator;

public class PowOperator implements Operator {
    public BigDecimal apply(BigDecimal o1, BigDecimal o2) { return o1.pow(o2.intValueExact()); }
    public int getPriority() { return  300; }
}