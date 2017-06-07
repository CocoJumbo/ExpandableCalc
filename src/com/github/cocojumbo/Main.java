package com.github.cocojumbo;

import com.github.cocojumbo.calc.ParceTree;

public class Main {

    public static void main(String[] args) {
        new ParceTree().calculate("4123+331*3-1");
        //new ParceTree().calculate("4123+331");
    }
}