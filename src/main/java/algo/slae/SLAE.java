package algo.slae;

import algo.Multiplicator;
import algo.exception.DegenerateMatrixException;
import algo.exception.DifferentMatrixSizes;
import exception.ImmutableException;
import matrix.Matrix;
import matrix.RectangularMatrix;
import matrix.SquareMatrix;
import vector.ColumnVector;
import vector.Vector;

public class SLAE {
    private final static double eps = 1e-7;
    private Matrix A = null;
    private Matrix b = null;
    private Matrix x = null;
    private double det = 1;
    private int sign = 1;
    private boolean processed = false;
    public SLAE(Matrix A, Vector b) throws DifferentMatrixSizes {
        b = b.toColumnVector();
        if (A.getColumnCount() != b.getElementCount()) {
            throw new DifferentMatrixSizes(String.format("A column count(%d) != b element count(%d)", A.getColumnCount(), b.getElementCount()));
        }
        this.A = new SquareMatrix(A);
        this.b = new RectangularMatrix(b.getMatrixForm());
    }
    private void swapLines(int i, int j) {
        try {
            Matrix permMatrix = new PermutationMatrix(A.getColumnCount(), i, j);
            A = Multiplicator.multiply(permMatrix, A);
            b = Multiplicator.multiply(permMatrix, b);
            sign *= -1;
        } catch (DifferentMatrixSizes ex) {

        }
    }
    private void swapColumn(int i, int j) {
        try {
            Matrix permMatrix = new PermutationMatrix(A.getColumnCount(), i, j);
            A = Multiplicator.multiply(A, permMatrix);
            b = Multiplicator.multiply(permMatrix, b);
        } catch (DifferentMatrixSizes ex) {

        }
    }
    public double getDet() {
        try {
            if (!processed)
                solve();
            return det * sign;
        } catch (DegenerateMatrixException ex) {
            return 0;
        }
    }
    private void solve() throws DegenerateMatrixException {
        straightCourse();
    }
    private void straightCourse() throws DegenerateMatrixException {
        int n = A.getColumnCount();
        for (int i = 0; i < n; i++) {
            int maxElemIndx = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(A.get(j, i)) > Math.abs(A.get(maxElemIndx, i)))
                    maxElemIndx = j;
            }
            if (i != maxElemIndx) {
                swapLines(maxElemIndx, i);
            }
        }
        double[][] Matrix_Clone = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Matrix_Clone[i][j] = A.get(i, j);
            }
        }
        for (int i = 0; i < n; i++) {
            Matrix_Clone[i][n] = b.get(i, 0);
        }
        for (int k = 0; k < n; k++) {
            det *= Matrix_Clone[k][k];
            if (Math.abs(Matrix_Clone[k][k]) < eps) {
                throw new DegenerateMatrixException();
            }
            for (int i = 0; i < n + 1; i++) {
                Matrix_Clone[k][i] = Matrix_Clone[k][i] / A.get(k, k);
            }
            for (int i = k + 1; i < n; i++) {
                double K = Matrix_Clone[i][k] / Matrix_Clone[k][k];
                for (int j = 0; j < n + 1; j++) {
                    Matrix_Clone[i][j] = Matrix_Clone[i][j] - Matrix_Clone[k][j] * K;
                }
            }
            try {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++)
                        A.set(i, j, Matrix_Clone[i][j]);
                    b.set(i, 0, Matrix_Clone[i][n]);
                }
            } catch (ImmutableException ex) {
            }
        }
        for (int k = n - 1; k > -1; k--) {
            for (int i = n; i > -1; i--) {
                Matrix_Clone[k][i] = Matrix_Clone[k][i] / A.get(k, k);
            }
            for (int i = k - 1; i > -1; i--) {
                double K = Matrix_Clone[i][k] / Matrix_Clone[k][k];
                for (int j = n; j > -1; j--) {
                    Matrix_Clone[i][j] = Matrix_Clone[i][j] - Matrix_Clone[k][j] * K;
                }
            }
        }
        try {
            x = new ColumnVector(n).getMatrixForm();
            for (int i = 0; i < n; i++)
                x.set(i, 0, Matrix_Clone[i][n]);
        } catch (ImmutableException ex) {
        }
        processed = true;
    }
    public Vector getRootVector() throws DegenerateMatrixException {
        solve();
        return x.getColumnVector(0);
    }
}
