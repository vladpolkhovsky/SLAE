package algo;

import algo.exception.DifferentMatrixSizes;
import matrix.Matrix;
import matrix.RectangularMatrix;
import matrix.SquareMatrix;

public class Substractor {
    public static Matrix substract(Matrix a, Matrix b) throws DifferentMatrixSizes {
        if (a.getLineCount() != b.getLineCount() || a.getColumnCount() != b.getColumnCount()) {
            throw new DifferentMatrixSizes("A size not equals B size.");
        }
        Matrix result = null;
        try {
            result = new RectangularMatrix(a.getLineCount(), a.getColumnCount());
            for (int i = 0; i < a.getLineCount(); i++) {
                for (int j = 0; j < a.getColumnCount(); j++) {
                    result.set(i, j, a.get(i, j) - b.get(i, j));
                }
            }
        } catch (Exception ex) {

        }
        return result;
    }
}
