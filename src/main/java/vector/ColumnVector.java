package vector;

import matrix.Matrix;
import matrix.SquareMatrix;
import matrix.exception.BadMatrixException;
import vector.exception.BadVectorException;
import vector.exception.ImmutableVectorException;
import vector.exception.OutOfBoundsVectorException;

public class ColumnVector implements Vector {
    private final double[] vector;

    public ColumnVector(double[] vector) {
        this.vector = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i];
        }
    }

    public ColumnVector(int n) {
        vector = new double[n];
    }

    public ColumnVector(int n, double[] vector) throws BadVectorException {
        if (vector.length < n) {
            throw new BadVectorException(String.format("vector size(%d) less then %d", vector.length, n));
        }
        this.vector = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i];
        }
    }

    private void indxChecker(int i) throws OutOfBoundsVectorException {
        if (i < 0 || vector.length <= i) {
            throw new OutOfBoundsVectorException(String.format("vector size(%d) less then %d", vector.length, i));
        }
    }

    @Override
    public double get(int i) throws OutOfBoundsVectorException {
        indxChecker(i);
        return vector[i];
    }

    @Override
    public double set(int i, double value) throws OutOfBoundsVectorException {
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
        } catch (BadMatrixException badMatrixException) {

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
