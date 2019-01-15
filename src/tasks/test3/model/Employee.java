package tasks.test3.model;

public class Employee extends Human implements Comparable<Employee>{
    private double salary;
    private Position position;

    static public enum Position{
        MANAGER,
        WORKER,
        TRAINEE;
    }


    public Employee() {}

    public Employee(String secondName, double salary, Position position) {
        setSecondName(secondName);
        this.salary = salary;
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return super.toString() + ", position: " + position + ", salary: " + salary + ", qualification: " + calculateQualification();
    }

    @Override
    public int compareTo(Employee o) {
        int res = this.position.compareTo(o.position);
        if(res == 0){
            res = this.getSecondName().compareTo(o.getSecondName());
        }return res;
    }

    @Override
    public boolean equals(Object obj) {
        if(!super.equals(obj)) return false;
        if(obj instanceof Employee){
            Employee e = (Employee) obj;
            if(position == e.position){
                if(salary == e.salary) return true;
            }
        }return false;
    }

    private final static int COEFFICIENT = 330;
    @Override
    public double calculateQualification() {
        return salary/COEFFICIENT;
    }
}
