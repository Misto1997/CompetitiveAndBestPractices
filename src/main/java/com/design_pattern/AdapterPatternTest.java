package com.design_pattern;

interface MarksAdapter {
    StudentMarks getAGradeStudents();

    StudentMarks getBGradeStudents();

    StudentMarks getCGradeStudents();

    StudentMarks getDGradeStudents();
}

class StudentMarks {
    int marks;

    StudentMarks(int marks) {
        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "StudentMarks{" +
                "marks=" + marks +
                '}';
    }
}

class TotalMarks {

    StudentMarks getMarks() {
        return new StudentMarks(100);
    }
}

public class AdapterPatternTest implements MarksAdapter {

    private TotalMarks totalMarks = new TotalMarks();

    public static void main(String[] args) {
        AdapterPatternTest test = new AdapterPatternTest();
        System.out.println(test.getCGradeStudents());
    }

    @Override
    public StudentMarks getAGradeStudents() {
        return totalMarks.getMarks();
    }

    @Override
    public StudentMarks getBGradeStudents() {
        return convertGrade(totalMarks.getMarks(), 'B');
    }

    @Override
    public StudentMarks getCGradeStudents() {
        return convertGrade(totalMarks.getMarks(), 'C');
    }

    @Override
    public StudentMarks getDGradeStudents() {
        return convertGrade(totalMarks.getMarks(), 'D');
    }

    private StudentMarks convertGrade(StudentMarks studentMarks, char gradeType) {

        if (gradeType == 'B')
            return new StudentMarks(studentMarks.getMarks() / 2);
        else if (gradeType == 'C')
            return new StudentMarks(studentMarks.getMarks() / 3);
        else
            return new StudentMarks(studentMarks.getMarks() / 4);
    }
}
