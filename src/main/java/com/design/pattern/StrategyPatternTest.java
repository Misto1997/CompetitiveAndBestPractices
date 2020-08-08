package com.design.pattern;

interface Walk {
    void walkingStyle();
}

class MoonWalk implements Walk {
    @Override
    public void walkingStyle() {
        System.out.println("moon walk");
    }
}

class SlowWalk implements Walk {
    @Override
    public void walkingStyle() {
        System.out.println("slow walk");
    }
}

class WalkSyleSelector{
    public void getWalkStyle(Walk walk){
        walk.walkingStyle();
    }
}

public class StrategyPatternTest {
    public static void main(String[] args) {
        WalkSyleSelector walkSyleSelector=new WalkSyleSelector();
        walkSyleSelector.getWalkStyle(new MoonWalk());
    }
}
