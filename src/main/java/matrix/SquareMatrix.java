package matrix;

import exception.BadArgsException;
import exception.ImmutableException;

public class SquareMatrix extends RectangularMatrix {
    public SquareMatrix(double[][] matrix) throws BadArgsException {
        super(matrix);
    }

    public SquareMatrix(Matrix matrix) {
        super(matrix);
    }

    public SquareMatrix(int n) {
        super(n, n);
    }

    public SquareMatrix(int n, double[][] matrix) throws BadArgsException, ImmutableException {
        super(n, n, matrix);
    }
}
