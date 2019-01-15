package tasks.test.cars;

public class PassengerCar extends Car {

    private SeatMaterial seatMaterial;

    public PassengerCar() {
        super();
    }

    public PassengerCar(String name, String color, Fuel fuel, SeatMaterial seatMaterial) {
        super(name, color, fuel);
        this.seatMaterial = seatMaterial;
    }

    public static enum SeatMaterial{
        LEATHER,
        CLOTH,
        LEATHERETTE
    }

    public SeatMaterial getSeatMaterial() {
        return seatMaterial;
    }
    public void setSeatMaterial(SeatMaterial seatMaterial) {
        this.seatMaterial = seatMaterial;
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder(super.getInfo());
        sb.append(", seat material: ");
        sb.append(seatMaterial);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PassengerCar){
            PassengerCar pc = (PassengerCar)obj;
            if(pc.name == name){
                if(pc.color == color){
                    if(pc.fuel == fuel){
                        if(pc.seatMaterial == seatMaterial);
                        return true;
                    }
                }
            }
        }return false;
    }
}
