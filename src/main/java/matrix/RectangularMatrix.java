package matrix;

import exception.BadArgsException;
import exception.ImmutableException;
import vector.ColumnVector;
import vector.LineVector;
import vector.Vector;

public class RectangularMatrix implements Matrix {

    private final double[][] matrix;

    public RectangularMatrix(double[][] matrix) throws BadArgsException {
        int columnCount = matrix[0].length;
        for (double[] doubles : matrix) {
            if (doubles.length != columnCount)
                throw new BadArgsException("Different line sizes.");
        }
        this.matrix = new double[matrix.length][columnCount];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < columnCount; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public RectangularMatrix(int n, int m) {
        matrix = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public RectangularMatrix(int n, int m, double[][] matrix) throws BadArgsException {
        if (matrix.length < n)
            throw new BadArgsException(String.format("Matrix line count(%d) less then %d.", matrix.length, n));
        this.matrix = new double[n][m];
        for (int i = 0; i < n; i++) {
            if (matrix[i].length < m)
                throw new BadArgsException((String.format("Matrix line %d length(%d) less then %d.", i, matrix[i].length, m)));
            for (int j = 0; j < m; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public RectangularMatrix(Matrix matrix) {
        this.matrix = new double[matrix.getLineCount()][matrix.getColumnCount()];
        try {
            for (int i = 0; i < matrix.getLineCount(); i++) {
                for (int j = 0; j < matrix.getColumnCount(); j++) {
                    this.matrix[i][j] = matrix.get(i, j);
                }
            }
        } catch (IndexOutOfBoundsException ex) {

        }
    }

    private void badIndexChecker(int i, int j) throws IndexOutOfBoundsException {
        if (i < 0 || j < 0)
            throw new IndexOutOfBoundsException("Negative indx.");
        if (matrix.length <= i)
            throw new IndexOutOfBoundsException(String.format("Matrix line count(%d) less then %d.", matrix.length, i));
        if (matrix[i].length <= j)
            throw new IndexOutOfBoundsException(String.format("Matrix line %d length(%d) less then %d.", i, matrix[i].length, j));
    }

    @Override
    public double get(int i, int j) throws IndexOutOfBoundsException {
        badIndexChecker(i, j);
        return matrix[i][j];
    }

    @Override
    public double set(int i, int j, double value) throws IndexOutOfBoundsException, ImmutableException {
        badIndexChecker(i, j);
        double pValue = matrix[i][j];
        matrix[i][j] = value;
        return pValue;
    }

    @Override
    public int getLineCount() {
        return matrix.length;
    }

    @Override
    public int getColumnCount() {
        return matrix[0].length;
    }

    @Override
    public Vector getLineVector(int i) throws IndexOutOfBoundsException {
        badIndexChecker(i, 0);
        return new LineVector(matrix[i]);
    }

    @Override
    public Vector getColumnVector(int j) throws IndexOutOfBoundsException {
        badIndexChecker(0, j);
        double[] vector = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            vector[i] = matrix[i][j];
        }
        return new ColumnVector(vector);
    }

    @Override
    public String toString() {
        String output = "{\n";
        try {
            for (int i = 0; i < getLineCount(); i++) {
                for (int j = 0; j < getColumnCount(); j++) {
                    output += String.format("%15.12s", String.format("%.8f", get(i, j)));
                }
                output += "\n";
            }
        } catch (Exception ex) {

        }
        output += "}";
        return output;
    }
}
