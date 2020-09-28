package algo;

import matrix.Matrix;
import matrix.RectangularMatrix;
import matrix.exception.BadMatrixException;
import matrix.exception.ImmutableMatrixException;
import matrix.exception.OutOfBoundsMatrixException;

public class TransposeMatrix {
    public static Matrix transpose(Matrix matrix) {
        Matrix transposed = null;
        try {
            transposed = new RectangularMatrix(matrix.getColumnCount(), matrix.getLineCount());
            for (int i = 0; i < matrix.getLineCount(); i++) {
                for (int j = 0; j < matrix.getColumnCount(); j++) {
                    transposed.set(j, i, matrix.get(i, j));
                }
            }
        } catch (OutOfBoundsMatrixException | ImmutableMatrixException ex) {

        }
        return  transposed;
    }
}
