package tasks.lab9.series;

public class Liner extends Series {

    private float mDifference;

    public Liner(int n, float firstMember, float difference){
        super(n, firstMember);
        mDifference = difference;
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
                mPrevElem += mDifference;
            }return mPrevElem;
        }
        else{
            float resMemb = mFirstMember;
            for(int i=2; i<=j; i++){
                resMemb += mDifference;
            }
            mPrevElem = resMemb;
            mPrevJ = j;
            return resMemb;
        }
    }

    @Override
    public Liner setN(int n) {
        return new Liner(n, mFirstMember, mDifference);
    }

    @Override
    public Liner setFirstMember(float firstMember) {
        return new Liner(mMaxNumber, firstMember, mDifference);
    }

    @Override
    public Liner setStep(float step) {
        return new Liner(mMaxNumber, mFirstMember, step);
    }

    @Override
    public float getStep() {
        return mDifference;
    }

    //getters and setters
    public float getDifference() {
        return mDifference;
    }

}
