package matrix;

import matrix.exception.BadMatrixException;

public class SquareMatrix extends RectangularMatrix {
    public SquareMatrix(double[][] matrix) throws BadMatrixException {
        super(matrix);
    }

    public SquareMatrix(int n) {
        super(n, n);
    }

    public SquareMatrix(int n, double[][] matrix) throws BadMatrixException {
        super(n, n, matrix);
    }
}
