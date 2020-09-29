package algo.slae;

import algo.Multiplicator;
import algo.exception.DifferentMatrixSizes;
import exception.ImmutableException;
import matrix.Matrix;
import matrix.RectangularMatrix;
import matrix.SquareMatrix;
import vector.ColumnVector;
import vector.Vector;

public class SLAE {
    private final static double eps = 1e-4;

    private Matrix A = null;
    private Matrix b = null;
    private Matrix x = null;

    private boolean processed = false;

    public SLAE(Matrix A, ColumnVector b) {
        this.A = new SquareMatrix(A);
        this.b = new RectangularMatrix(b.getMatrixForm());
    }

    private void swapLines(int i, int j) throws DifferentMatrixSizes {
        Matrix permMatrix = new PermutationMatrix(A.getColumnCount(), i, j);
        A = Multiplicator.multiply(permMatrix, A);
        b = Multiplicator.multiply(permMatrix, b);
    }

    private void swapColumn(int i, int j) throws DifferentMatrixSizes {
        Matrix permMatrix = new PermutationMatrix(A.getColumnCount(), i, j);
        A = Multiplicator.multiply(A, permMatrix);
        b = Multiplicator.multiply(permMatrix, b);
    }

    void solve() throws ImmutableException, IndexOutOfBoundsException, DifferentMatrixSizes {
        straightCourse();
    }

    private void straightCourse() throws ImmutableException {
        int n = A.getColumnCount();

        System.out.println(A);

        for (int i = 0; i < n; i++) {
            int maxElemIndx = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(A.get(j, i)) > Math.abs(A.get(maxElemIndx, i)))
                    maxElemIndx = j;
            }
            if (i != maxElemIndx) {
                try {
                    A = Multiplicator.multiply(new PermutationMatrix(n, i, maxElemIndx), A);
                    b = Multiplicator.multiply(new PermutationMatrix(n, i, maxElemIndx), b);
                } catch (DifferentMatrixSizes ex) {

                }
            }
        }

        System.out.println(A);

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
            for (int i = 0; i < n + 1; i++) //i-номер столбца
                Matrix_Clone[k][i] = Matrix_Clone[k][i] / A.get(k, k); //Деление k-строки на первый член !=0 для преобразования его в единицу
            if (Matrix_Clone[k][k] < eps) {
                Matrix_Clone[k][k] = 0;
            }
            for (int i = k + 1; i < n; i++) //i-номер следующей строки после k
            {
                double K = Matrix_Clone[i][k] / Matrix_Clone[k][k]; //Коэффициент
                for (int j = 0; j < n + 1; j++) //j-номер столбца следующей строки после k
                    Matrix_Clone[i][j] = Matrix_Clone[i][j] - Matrix_Clone[k][j] * K; //Зануление элементов матрицы ниже первого члена, преобразованного в единицу
            }
            for (int i = 0; i < n; i++) { //Обновление, внесение изменений в начальную матрицу
                for (int j = 0; j < n; j++)
                    A.set(i, j, Matrix_Clone[i][j]);
                b.set(i, 0, Matrix_Clone[i][n]);
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

        System.out.println(A);
        System.out.println(b);

        x = new ColumnVector(n).getMatrixForm();
        for (int i = 0; i < n; i++)
            x.set(i, 0, Matrix_Clone[i][n]);

        System.out.println(x);

        processed = true;
    }

    public boolean canSolve() throws IndexOutOfBoundsException,DifferentMatrixSizes,ImmutableException {
        solve();
        return false;
    }

    public Vector getRootVector() {
        return x.getColumnVector(0);
    }
}
