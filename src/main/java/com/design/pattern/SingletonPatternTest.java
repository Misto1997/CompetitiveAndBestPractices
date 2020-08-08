package com.design.pattern;

public class SingletonPatternTest {
    //Bill Pugh Singleton Implementation
    private SingletonPatternTest() {
    }

    public SingletonPatternTest getInstance() {
        return SingleInstance.INSTANCE;
    }

    private static class SingleInstance {
        private static final SingletonPatternTest INSTANCE = new SingletonPatternTest();
    }
}

