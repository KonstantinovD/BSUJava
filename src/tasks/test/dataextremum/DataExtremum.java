package tasks.test.dataextremum;

import java.util.ArrayList;
import java.util.List;

public class DataExtremum<T extends Comparable> extends ArrayList<T>{
    public T getMax(){
        if(this.isEmpty()) throw new UnsupportedOperationException("ERROR: DataExtremum doesn't contain any object!");
        T max = this.get(0);
        for(int i=1; i<this.size(); i++){
            if(max.compareTo(this.get(i)) < 0){
                max = this.get(i);
            }
        }return max;
    }

    public T getMin(){
        if(this.isEmpty()) throw new UnsupportedOperationException("ERROR: DataExtremum doesn't contain any object!");
        T min = this.get(0);
        for(int i=1; i<this.size(); i++){
            if(min.compareTo(this.get(i)) > 0){
                min = this.get(i);
            }
        }return min;
    }




}
