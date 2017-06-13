package com.github.cocojumbo.calc;

import com.github.cocojumbo.calc.operators.AppendOperator;
import com.github.cocojumbo.calc.operators.*;

import java.util.HashMap;

public class Operations {

    public static final String RAWPATTERN           = "\\+|-|\\*|^";
    public static final Operator APPEND             = new AppendOperator();
    public static final Operator MINUS              = new SubstractOperator();
    public static final Operator MULTIPLICATION     = new MultiplicationOperator();
    public static final Operator POW                = new PowOperator();

    private static HashMap<String, Operator> stringMap = new HashMap<>();

    static {
        stringMap.put("+", Operations.APPEND);
        stringMap.put("-", Operations.MINUS);
        stringMap.put("*", Operations.MULTIPLICATION);
        stringMap.put("^", Operations.POW);
    }

    public static Operator getBySymbol(String symbol) {
        return stringMap.get(symbol);
    }
}
