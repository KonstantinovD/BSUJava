import java.util.InputMismatchException;
import java.util.List;

class Matrix {

    private float[][] mArray;

    public Matrix(int columns, int rows) {
        if (columns < 1 || rows < 1) {
            throw new IllegalArgumentException("Matrix dimensions should be positive values");
        }
        mArray = new float[columns][rows];
    }

    public Matrix(Matrix other){
        mArray = new float[other.sizeM()][other.sizeN()];
        for(int i = 0; i < sizeM(); i++){
            for(int j = 0; j < sizeN(); j++){
                mArray[i][j] = other.mArray[i][j];
            }
        }
    }

    public float get(int column, int row) {
        if (column < 1 || column > mArray.length)
            throw new IllegalArgumentException("Matrix index is out of bounds");
        if (row < 1 || row > mArray[0].length)
            throw new IllegalArgumentException("Matrix index is out of bounds");
        return mArray[column - 1][row - 1];
    }

    public void set(float value, int column, int row) {
        if (column < 1 || column > mArray.length)
            throw new IllegalArgumentException("Matrix index is out of bounds");
        if (row < 1 || row > mArray[0].length)
            throw new IllegalArgumentException("Matrix index is out of bounds");
        mArray[column - 1][row - 1] = value;
    }

    public int getNumberOfRows() {
        return mArray.length;
    }

    public int getNumberOfColumns() {
        return mArray[0].length;
    }

    public int sizeM(){
        return mArray.length;
    }

    public int sizeN(){
        return mArray[0].length;
    }

    public Matrix multiply(Matrix other){
        int m1 = sizeM();
        int n1 = sizeN();
        int m2 = other.sizeM();
        int n2 = other.sizeN();
        if (n1 != m2) return null;
        Matrix res = new Matrix(m1, n2);

        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    res.mArray[i][j] += this.mArray[i][k] * other.mArray[k][j];
        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<this.sizeM(); i++) {
            for (int j = 0; j < this.sizeN(); j++) {
                sb.append(mArray[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean isSquare(){
        return(sizeM() == sizeN());
    }

    //builders
    public static Matrix squareMatrix(List<? extends Number> list){
        int size = (int)Math.sqrt(list.size());
        if(size*size < list.size()){
            throw new InputMismatchException("ERROR: Invalid number of matrix elements");
        }
        Matrix matrix = new Matrix(size, size);
        int k = 0;
        for(int i=1; i<=size; i++){
            for(int j=1; j<=size; j++){
                matrix.set(list.get(k).floatValue(), i, j);
                k++;
            }
        }
        return matrix;
    }

    public static Matrix identityMatrix(int columns){
        Matrix res = new Matrix(columns, columns);
        for(int i=0; i<res.sizeN(); i++){
            res.mArray[i][i] = 1;
        }
        return res;
    }
}