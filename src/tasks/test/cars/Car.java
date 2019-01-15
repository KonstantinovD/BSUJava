package tasks.test.cars;

public abstract class Car implements Comparable<Car>{
    protected String name;
    protected String color;
    protected Fuel fuel;

    public Car(){}

    public Car(String name, String color, Fuel fuel) {
        this.name = name;
        this.color = color;
        this.fuel = fuel;
    }

    public static enum Fuel{
        PETROL,
        DIESEL
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    @Override
    public int compareTo(Car other) {
        int res = name.compareTo(other.name);
        if(res == 0){
            res = other.fuel.compareTo(fuel);
        }return res;
    }

    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" , color: ");
        sb.append(color);
        sb.append(" , fuel: ");
        sb.append(fuel);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Car){
            Car c = (Car)obj;
            if(fuel == c.fuel){
                if(color == c.color){
                    if(name == c.name) return true;
                }
            }
        }return false;
    }
}
