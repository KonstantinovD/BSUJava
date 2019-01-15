package employee;

public class Employee implements Comparable<Employee>{
    private String mName;
    private int mAge;

    public Employee(){}

    public Employee(String name, int age){
        mAge = age;
        mName = name;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setAge(int age) {
        if(age >= 0) {
            mAge = age;
        }
    }

    public String getName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }

    @Override
    public String toString() {
        return mName.concat(", age: ") + mAge;
    }

    @Override
    public int compareTo(Employee o) {
        int result = mName.compareTo(o.mName);
        if(result == 0){
            return Integer.compare(mAge, o.mAge);
        } return result;
    }
}