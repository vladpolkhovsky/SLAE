package vector;

import exception.BadArgsException;
import matrix.Matrix;
import matrix.SquareMatrix;

public class ColumnVector implements Vector {
    private final double[] vector;

    public ColumnVector(double[] vector) {
        this.vector = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i];
        }
    }

    public ColumnVector(Vector vector) {
        this.vector = new double[vector.getElementCount()];
        try {
            for (int i = 0; i < this.vector.length; i++) {
                this.vector[i] = vector.get(i);
            }

        } catch (IndexOutOfBoundsException ex) {

        }
    }

    public ColumnVector(int n) {
        vector = new double[n];
    }

    public ColumnVector(int n, double[] vector) throws BadArgsException {
        if (vector.length < n) {
            throw new BadArgsException(String.format("vector size(%d) less then %d", vector.length, n));
        }
        this.vector = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i];
        }
    }

    private void indxChecker(int i) throws IndexOutOfBoundsException {
        if (i < 0 || vector.length <= i) {
            throw new IndexOutOfBoundsException(String.format("vector size(%d) less then %d", vector.length, i));
        }
    }

    @Override
    public double get(int i) throws IndexOutOfBoundsException {
        indxChecker(i);
        return vector[i];
    }

    @Override
    public double set(int i, double value) throws IndexOutOfBoundsException {
        indxChecker(i);
        double prevValue = vector[i];
        vector[i] = value;
        return prevValue;
    }

    @Override
    public int getElementCount() {
        return vector.length;
    }

    @Override
    public Matrix getMatrixForm() {
        double[][] matrix = new double[vector.length][1];
        for (int i = 0; i < vector.length; i++)
            matrix[i][0] = vector[i];
        Matrix rMatrix = null;
        try {
            rMatrix = new SquareMatrix(matrix);
        } catch (BadArgsException badMatrixException) {

        }
        return rMatrix;
    }

    @Override
    public Vector toLineVector() {
        return new LineVector(vector);
    }

    @Override
    public Vector toColumnVector() {
        return new ColumnVector(vector);
    }

    @Override
    public String toString() {
        return getMatrixForm().toString();
    }
}
