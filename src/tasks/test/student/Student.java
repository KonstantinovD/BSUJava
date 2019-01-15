package tasks.test.student;

public class Student implements Comparable<Student>{
    private String mSecondName;
    private int mYear;
    private double mAverageMark;
    private final double DEFAULT_MARK = 0.0;

    public Student(){}
    public Student(String secondName, int year, double averageMark){
        this.mSecondName = secondName;
        this.mYear = year;
        this.mAverageMark = averageMark;
    }

    public String getSecondName() {
        return mSecondName;
    }

    public int getYear() {
        return mYear;
    }

    public double getAverageMark() {
        return mAverageMark;
    }

    public void setSecondName(String mSecondName) {
        this.mSecondName = mSecondName;
    }

    public void setYear(int mYear) {
        this.mYear = mYear;
    }

    public void setAverageMark(double mAverageMark) {
        this.mAverageMark = mAverageMark;
    }

    @Override
    public int compareTo(Student other) {
        int res = Integer.compare(this.mYear, other.mYear);
        if(res == 0){
            res = this.mSecondName.compareTo(other.mSecondName);
            if(res == 0){
                res = Double.compare(this.mAverageMark, other.mAverageMark);
            }
        }return res;
    }
}
