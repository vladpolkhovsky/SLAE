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

        //Прямой ход (Зануление нижнего левого угла)
        for (int k = 0; k < n; k++) //k-номер строки
        {
            if (Math.abs(Matrix_Clone[k][k]) < eps) {
                throw new DegenerateMatrixException();
            }
            for (int i = 0; i < n + 1; i++) //i-номер столбца
                Matrix_Clone[k][i] = Matrix_Clone[k][i] / A.get(k, k); //Деление k-строки на первый член !=0 для преобразования его в единицу

            for (int i = k + 1; i < n; i++) //i-номер следующей строки после k
            {
                double K = Matrix_Clone[i][k] / Matrix_Clone[k][k]; //Коэффициент
                for (int j = 0; j < n + 1; j++) //j-номер столбца следующей строки после k
                    Matrix_Clone[i][j] = Matrix_Clone[i][j] - Matrix_Clone[k][j] * K; //Зануление элементов матрицы ниже первого члена, преобразованного в единицу
            }
            try {
                for (int i = 0; i < n; i++) { //Обновление, внесение изменений в начальную матрицу
                    for (int j = 0; j < n; j++)
                        A.set(i, j, Matrix_Clone[i][j]);
                    b.set(i, 0, Matrix_Clone[i][n]);
                }
            } catch (ImmutableException ex) {

            }
        }

        //Обратный ход (Зануление верхнего правого угла)
        for (int k = n - 1; k > -1; k--) //k-номер строки
        {
            for (int i = n; i > -1; i--) //i-номер столбца
                Matrix_Clone[k][i] = Matrix_Clone[k][i] / A.get(k, k);
            for (int i = k - 1; i > -1; i--) //i-номер следующей строки после k
            {
                double K = Matrix_Clone[i][k] / Matrix_Clone[k][k];
                for (int j = n; j > -1; j--) //j-номер столбца следующей строки после k
                    Matrix_Clone[i][j] = Matrix_Clone[i][j] - Matrix_Clone[k][j] * K;
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
