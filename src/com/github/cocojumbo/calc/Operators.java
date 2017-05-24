package com.github.cocojumbo.calc;

import java.util.HashMap;

public class Operators {

    public static final String RAWPATTERN           = "\\+|-|\\*";
    public static final Operator APPEND             = new AppendOperator();
    public static final Operator MINUS              = new MinusOperator();
    public static final Operator MULTIPLICATION     = new MultiplicationOperator();

    private static HashMap<String, Operator> stringMap = new HashMap<>();

    static {
        stringMap.put("+", Operators.APPEND);
        stringMap.put("-", Operators.MINUS);
        stringMap.put("*", Operators.MULTIPLICATION);
    }

    public static Operator getBySymbol(String symbol) {
        return stringMap.get(symbol);
    }

    private static class AppendOperator extends AbstractOperator {
        private final int PRIORITY = 100;
        public Long apply(Long o1, Long o2) { return o1 + o2; }
    }

    private static class MinusOperator extends AbstractOperator {
        private final int PRIORITY = 100;
        public Long apply(Long o1, Long o2) { return o1 - o2; }
    }

    private static class MultiplicationOperator extends AbstractOperator {
        private final int PRIORITY = 200;
        public Long apply(Long o1, Long o2) { return o1 * o2; }
    }
}
