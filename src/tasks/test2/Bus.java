public class Bus extends Car {
    private int numberOfSeats;
    private int numberOfDoors;

    public Bus() {}

    public Bus(String name, String color, Fuel fuel, int numberOfSeats, int numberOfDoors) {
        super(name, color, fuel);
        setNumberOfDoors(numberOfDoors);
        setNumberOfSeats(numberOfSeats);
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        if(numberOfSeats < 0) throw new IllegalArgumentException("ERROR: number of seats shouldn't be a negative value");
        this.numberOfSeats = numberOfSeats;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        if(numberOfDoors < 0) throw new IllegalArgumentException("ERROR: number of doors shouldn't be a negative value");
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder(super.getInfo());
        sb.append(", numberOfSeats: ");
        sb.append(numberOfSeats);
        sb.append(", numberOfDoors: ");
        sb.append(numberOfDoors);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Bus){
            Bus pc = (Bus)obj;
            if(pc.name == name){
                if(pc.color == color){
                    if(pc.fuel == fuel){
                        if(pc.numberOfDoors == numberOfDoors){
                            if(pc.numberOfSeats == numberOfSeats){
                                return true;
                            }
                        }
                    }
                }
            }
        }return false;
    }
}