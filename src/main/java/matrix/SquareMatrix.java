package matrix;

import exception.BadArgsException;

public class SquareMatrix extends RectangularMatrix {
    public SquareMatrix(double[][] matrix) throws BadArgsException {
        super(matrix);
    }

    public SquareMatrix(int n) {
        super(n, n);
    }

    public SquareMatrix(int n, double[][] matrix) throws BadArgsException {
        super(n, n, matrix);
    }
}
