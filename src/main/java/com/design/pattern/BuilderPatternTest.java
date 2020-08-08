package com.design.pattern;

public class BuilderPatternTest {
    public static void main(String[] args) {
        Student student = new Student.StudentBuilder().setAge(5).setJobStatus("jobless").build();
        System.out.println(student.toString());
    }
}

class Student {
    int age;
    String name;
    String status;
    String jobStatus;

    Student(StudentBuilder builder) {
        this.age = builder.age;
        this.name = builder.name;
        this.status = builder.status;
        this.jobStatus = builder.jobStatus;
    }

    public static class StudentBuilder {
        int age;
        String name;
        String status;
        String jobStatus;

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public StudentBuilder setJobStatus(String jobStatus) {
            this.jobStatus = jobStatus;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                '}';
    }
}
