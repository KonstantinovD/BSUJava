import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Container<Car> cont = new Container<>();
            Scanner reader = new Scanner(new File("in.txt"));

            Bus lastBus = null;
            while(reader.hasNext()){
                Bus bus = new Bus();
                bus.setName(reader.next());
                bus.setColor(reader.next());
                int fuel = reader.nextInt();
                if(fuel == 1){
                    bus.setFuel(Car.Fuel.PETROL);
                }else{
                    bus.setFuel(Car.Fuel.DIESEL);
                }
                bus.setNumberOfSeats(reader.nextInt());
                bus.setNumberOfDoors(reader.nextInt());
                cont.add(bus);
                lastBus = bus;
            }

            cont.print();
            if(lastBus != null){
                System.out.println("number of " + lastBus.getInfo() + ":");
                System.out.println(cont.numberOfCars(lastBus));
            }

            System.out.println("max is: " + cont.getMax().getInfo());
            


        }catch (FileNotFoundException fnex){
            System.out.println("Unable to found first ");
        }catch (Exception ex){
            System.out.println("APP ERROR!");
        }
    }
}