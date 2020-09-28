package vector;

import matrix.Matrix;
import matrix.SquareMatrix;
import matrix.exception.BadMatrixException;
import vector.exception.BadVectorException;
import vector.exception.OutOfBoundsVectorException;

public class LineVector implements Vector {
    private final double[] vector;

    public LineVector(Vector vector) {
        this.vector = new double[vector.getElementCount()];
        try {
            for (int i = 0; i < this.vector.length; i++) {
                this.vector[i] = vector.get(i);
            }
        } catch (OutOfBoundsVectorException ex) {

        }
    }

    public LineVector(double[] vector) {
        this.vector = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i];
        }
    }

    public LineVector(int n) {
        vector = new double[n];
    }

    public LineVector(int n, double[] vector) throws BadVectorException {
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
        double[][] matrix = new double[1][];
        matrix[0] = vector;
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
