package tasks.lab9.series;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {
    protected int mMaxNumber;
    protected float mFirstMember;


    public Series(int n, float firstMember){
        if(n > 0) mMaxNumber = n;
        mFirstMember = firstMember;
    }

    /**
     * Returns specified element of series
     * @param j
     * @return
     */
    public abstract float calculateJMember(int j);

    /**
     * Returns sum of series
     * @return
     */
    public float getSum(){
        float res = mFirstMember;
        for(int i=2; i<=mMaxNumber; i++){
            res += calculateJMember(i);
        }return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(mFirstMember);
        for(int i=2; i<=mMaxNumber; i++){

            float memb = calculateJMember(i);
            sb.append(" " + memb);
        }
        return sb.toString();
    }

    /**
     * Writes all the series (from first to last element)
     * to the specified file
     * @param file
     * @throws IOException
     */
    public void writeToFile(File file) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(this.toString());
        writer.close();
    }



    //getters
    /**
     * Returns the last element of series
     * @return
     */
    public int getN() {
        return mMaxNumber;
    }

    public float getFirstMember() {
        return mFirstMember;
    }

    public abstract float getStep();

    //setters
    public abstract Series setN(int n);
    public abstract Series setFirstMember(float firstMember);
    public abstract Series setStep(float step);
}
