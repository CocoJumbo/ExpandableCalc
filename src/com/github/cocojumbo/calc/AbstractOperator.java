package com.github.cocojumbo.calc;

abstract class AbstractOperator implements Operator {
    protected final int PRIORITY = -1;
    public int getPriority() { return PRIORITY; }
}
