package vector;

import exception.BadArgsException;
import exception.ImmutableException;
import matrix.Matrix;
import matrix.RectangularMatrix;

public class IdentityVector implements Vector {
    public static Vector getMutableInstance(int n) {
        double[] vector = new double[n];
        for (int i = 0; i < n; i++) {
            vector[i] = 1;
        }
        return new LineVector(vector);
    }

    private int elementCount;

    private void indxChecker(int i) throws IndexOutOfBoundsException {
        if (i < 0 || elementCount <= i) {
            throw new IndexOutOfBoundsException(String.format("vector size(%d) less then %d", elementCount, i));
        }
    }

    public IdentityVector(int n) {
        elementCount = n;
    }

    @Override
    public double get(int i) throws IndexOutOfBoundsException {
        indxChecker(i);
        return 1;
    }

    @Override
    public double set(int i, double value) throws ImmutableException {
        throw new ImmutableException("Identity vector is immutable. Use .getMutableInstance(int n)\"");
    }

    @Override
    public int getElementCount() {
        return elementCount;
    }

    @Override
    public Matrix getMatrixForm() {
        double[][] vector = new double[1][elementCount];
        for (int i = 0; i < elementCount; i++) {
            vector[0][i] = 1;
        }
        Matrix matrix = null;
        try {
            matrix = new RectangularMatrix(vector);
        } catch (BadArgsException ex) {

        }
        return matrix;
    }

    @Override
    public Vector toLineVector() {
        Vector vector = null;
        try {
            vector = new LineVector(getMatrixForm().getLineVector(0));
        } catch (IndexOutOfBoundsException ex) {

        }
        return vector;
    }

    @Override
    public Vector toColumnVector() {
        Vector vector = null;
        try {
            vector = new ColumnVector(getMatrixForm().getLineVector(0));
        } catch (IndexOutOfBoundsException ex) {

        }
        return vector;
    }
}
