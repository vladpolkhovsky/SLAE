package matrix;

import matrix.exception.BadMatrixException;
import matrix.exception.ImmutableMatrixException;
import matrix.exception.OutOfBoundsMatrixException;

public class IdentityMatrix implements Matrix {
    public static Matrix getMutableInstance(int n) {
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = i == j ? 1 : 0;
            }
        }
        Matrix mutableInstance = null;
        try {
            mutableInstance = new SquareMatrix(matrix);
        } catch (BadMatrixException ex) {
            System.out.println(ex);
        }
        return mutableInstance;
    }

    private final int lineAndColumnCount;

    public IdentityMatrix(int n) {
        lineAndColumnCount = n;
    }

    private void badIndexChecker(int i, int j) throws OutOfBoundsMatrixException {
        if (i < 0 || j < 0)
            throw new OutOfBoundsMatrixException("Negative indx.");
        if (lineAndColumnCount < i)
            throw new OutOfBoundsMatrixException(String.format("Matrix line count(%d) less then %d.", lineAndColumnCount, i));
        if (lineAndColumnCount < j)
            throw new OutOfBoundsMatrixException(String.format("Matrix line %d length(%d) less then %d.", i, lineAndColumnCount, j));
    }

    @Override
    public double get(int i, int j) throws OutOfBoundsMatrixException {
        badIndexChecker(i, j);
        return i == j ? 1 : 0;
    }

    @Override
    public double set(int i, int j, double value) throws ImmutableMatrixException {
        throw new ImmutableMatrixException("Identity matrix is immutable. Use .getMutableInstance(int n)");
    }

    @Override
    public int getLineCount() {
        return lineAndColumnCount;
    }

    @Override
    public int getColumnCount() {
        return lineAndColumnCount;
    }

}
