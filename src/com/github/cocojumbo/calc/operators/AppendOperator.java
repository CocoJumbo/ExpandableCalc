package com.github.cocojumbo.calc.operators;

import com.github.cocojumbo.calc.Operator;

public class AppendOperator implements Operator {
        protected final int  PRIORITY = 100;
        public Long apply(Long o1, Long o2) { return o1 + o2; }
        public int getPriority() { return  PRIORITY; }
}
