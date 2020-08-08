package com.design.pattern;

abstract class Computer {
    public abstract void ram();

    public abstract void hardDisk();

    private void os() {
        System.out.println("Windows");
    }

    private void motherBoard() {
        System.out.println("Intel");
    }

    public final void buildSystem() {
        ram();
        hardDisk();
        os();
        motherBoard();
    }
}

class Lenovo extends Computer {

    @Override
    public void ram() {
        System.out.println("4 GB");
    }

    @Override
    public void hardDisk() {
        System.out.println("500 GB");
    }
}

class Dell extends Computer {

    @Override
    public void ram() {
        System.out.println("8 GB");
    }

    @Override
    public void hardDisk() {
        System.out.println("1 TB");
    }
}

public class TemplateMethodPatternTest {
    public static void main(String[] args) {
        Computer lenovoComputer = new Lenovo();
        lenovoComputer.buildSystem();
        System.out.println();
        Computer dellComputer = new Dell();
        dellComputer.buildSystem();
    }
}
