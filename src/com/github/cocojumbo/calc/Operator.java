package com.github.cocojumbo.calc;

import java.math.BigDecimal;

public interface Operator {
    BigDecimal apply(BigDecimal o1, BigDecimal o2);
    int getPriority();
}
