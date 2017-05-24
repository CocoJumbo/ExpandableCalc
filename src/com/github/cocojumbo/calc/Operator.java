package com.github.cocojumbo.calc;

public interface Operator {
    Long apply(Long o1, Long o2);
    int getPriority();
}
