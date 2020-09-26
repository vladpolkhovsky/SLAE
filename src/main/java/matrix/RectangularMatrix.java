package matrix;

import matrix.exception.BadMatrixException;
import matrix.exception.OutOfBoundsMatrixException;

public class RectangularMatrix implements Matrix {

    private final double[][] matrix;

    public RectangularMatrix(double[][] matrix) throws BadMatrixException {
        int columnCount = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != columnCount)
                throw new BadMatrixException("Different line sizes.");
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

    public RectangularMatrix(int n, int m, double[][] matrix) throws BadMatrixException {
        this.matrix = new double[n][m];
        if (matrix.length < n)
            throw new BadMatrixException(String.format("Matrix line count(%d) less then %d.", matrix.length, n));
        for (int i = 0; i < n; i++) {
            if (matrix[i].length < m)
                throw new BadMatrixException((String.format("Matrix line %d length(%d) less then %d.", i, matrix[i].length, m)));
            for (int j = 0; j < m; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    private void badIndexChecker(int i, int j) throws OutOfBoundsMatrixException {
        if (i < 0 || j < 0)
            throw new OutOfBoundsMatrixException("Negative indx.");
        if (matrix.length < i)
            throw new OutOfBoundsMatrixException(String.format("Matrix line count(%d) less then %d.", matrix.length, i));
        if (matrix[i].length < j)
            throw new OutOfBoundsMatrixException(String.format("Matrix line %d length(%d) less then %d.", i, matrix[i].length, j));
    }

    @Override
    public double get(int i, int j) throws OutOfBoundsMatrixException {
        badIndexChecker(i, j);
        return matrix[i][j];
    }

    @Override
    public double set(int i, int j, double value) throws OutOfBoundsMatrixException {
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
}
