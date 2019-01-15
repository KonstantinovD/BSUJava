package tasks.test;

import tasks.test.cars.Bus;
import tasks.test.cars.Car;
import tasks.test.cars.PassengerCar;
import tasks.test.container.Container;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Container<Car> cont = new Container<>();
            Scanner reader = new Scanner(new File("tasks.lab12.resources\\in.txt"));


            ////////////////////////////////
            cont =  new Container<>();
            reader = new Scanner(new File("tasks.lab12.resources\\in.txt"));

            PassengerCar lastPC = null;
            while(reader.hasNext()){
                PassengerCar bus = new PassengerCar();
                bus.setName(reader.next());
                bus.setColor(reader.next());
                int fuel = reader.nextInt();
                if(fuel == 1){
                    bus.setFuel(Car.Fuel.PETROL);
                }else{
                    bus.setFuel(Car.Fuel.DIESEL);
                }
                int mater = reader.nextInt();
                switch (mater){
                    case 1: bus.setSeatMaterial(PassengerCar.SeatMaterial.LEATHER);
                    case 2: bus.setSeatMaterial(PassengerCar.SeatMaterial.CLOTH);
                    case 3: bus.setSeatMaterial(PassengerCar.SeatMaterial.LEATHERETTE);
                }
                cont.add(bus);
                lastPC = bus;
            }
            cont.print();
            if(lastPC != null){
                System.out.println("number of " + lastPC.getInfo() + ":");
                System.out.println(cont.numberOfCars(lastPC));
            }

            System.out.println("max is: " + cont.getMax().getInfo());


        }catch (FileNotFoundException fnex){
            System.out.println("Unable to found first ");
        }catch (Exception ex){
            System.out.println("APP ERROR!");
        }
    }
}
