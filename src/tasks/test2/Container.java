import java.util.ArrayList;

public class Container<T extends Car> extends ArrayList<T>{
    public void print(){
        for(Car c : this){
            System.out.println(c.getInfo());
        }
    }

    public int numberOfCars(T obj){
        int number = 0;
        for(T t: this){
            if(t.equals(obj)) number++;
        }return number;
    }

    public T getMax(){
        if(this.isEmpty()) throw new UnsupportedOperationException("ERROR: DataExtremum doesn't contain any object!");
        T max = this.get(0);
        for(int i=1; i<this.size(); i++){
            if(max.compareTo(this.get(i)) < 0){
                max = this.get(i);
            }
        }return max;
    }
}