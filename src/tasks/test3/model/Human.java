package tasks.test3.model;

public abstract class Human{
    private String secondName;

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "Human: " + secondName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Human){
            Human h = (Human)obj;
            if(secondName.equals(h.secondName)) return true;
        }
        return false;
    }


    public abstract double calculateQualification();
}
