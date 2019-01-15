package tasks.lab13.student;

public class Student{
    private int recordBookNumber;
    private String secondName;
    private int year;
    private int group;

    public Student(){}

    public Student(int recordBookNumber, String secondName, int year, int group) {
        this.recordBookNumber = recordBookNumber;
        this.secondName = secondName;
        setYear(year);
        this.group = group;
    }

    public int getRecordBookNumber() {
        return recordBookNumber;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getYear() {
        return year;
    }

    public int getGroup() {
        return group;
    }

    public void setRecordBookNumber(int recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setYear(int year) {
        if(year >= 0) {
            this.year = year;
        }
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return (secondName + ", year: " + year + ", group: " + group + ", book number: " + recordBookNumber);
    }
}
