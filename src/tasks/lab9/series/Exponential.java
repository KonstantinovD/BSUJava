package tasks.lab9.series;

public class Exponential extends Series {

    private float mDenominator;

    public Exponential(int n, float firstMember, float denominator){
        super(n, firstMember);
        mDenominator = denominator;
        //init optimization variables
        mPrevElem = mFirstMember;
        mPrevJ = n+1;
    }

    private int mPrevJ;
    private float mPrevElem;
    @Override
    public float calculateJMember(int j) {
        if(j < 1) throw new IllegalArgumentException("Series has no elements before the first one");
        if(j > mMaxNumber) throw new IllegalArgumentException("Series has no elements after the last one");

        if(j >= mPrevJ){//optimization
            while(mPrevJ < j){
                mPrevJ++;
                mPrevElem *= mDenominator;
            }return mPrevElem;
        }
        else{
            float resMemb = mFirstMember;
            for(int i=2; i<=j; i++){
                resMemb *= mDenominator;
            }
            mPrevElem = resMemb;
            mPrevJ = j;
            return resMemb;
        }
    }

    @Override
    public Exponential setN(int n) {
        return new Exponential(n, mFirstMember, mDenominator);
    }

    @Override
    public Exponential setFirstMember(float firstMember) {
        return new Exponential(mMaxNumber, firstMember, mDenominator);
    }

    @Override
    public Exponential setStep(float step) {
        return new Exponential(mMaxNumber, mFirstMember, step);
    }

    @Override
    public float getStep() {
        return mDenominator;
    }

    //getters and setters
    public float getDenominator() {
        return mDenominator;
    }

}
