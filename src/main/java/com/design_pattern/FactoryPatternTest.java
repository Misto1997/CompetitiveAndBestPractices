package com.design_pattern;

interface Employee {
    int salary();

    String id();

}

class SoftwareEngineer implements Employee {
    int salary;
    String id;

    SoftwareEngineer(int salary, String id) {
        this.salary = salary;
        this.id = id;
    }

    @Override
    public int salary() {
        return salary;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String toString() {
        return "SoftwareEngineer{" +
                "salary=" + salary +
                ", id='" + id + '\'' +
                '}';
    }
}

class HR implements Employee {
    int salary;
    String id;

    HR(int salary, String id) {
        this.salary = salary;
        this.id = id;
    }

    @Override
    public int salary() {
        return salary;
    }

    @Override
    public String id() {
        return id;
    }
}

public class FactoryPatternTest {
    public static Employee getEmployee(String role) {
        if (role.equals("SoftwareEngineer"))
            return new SoftwareEngineer(100000, "no one cares");
        else
            return new HR(10, "everyone cares");
    }

    public static void main(String[] args) {
        System.out.println(FactoryPatternTest.getEmployee("SoftwareEngineer"));
    }
}
