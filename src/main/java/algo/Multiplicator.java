package algo;

import algo.exception.DifferentMatrixSizes;
import exception.ImmutableException;
import matrix.Matrix;
import matrix.RectangularMatrix;

public class Multiplicator {
    public static Matrix multiply(Matrix a, Matrix b) throws DifferentMatrixSizes {
        if (a.getColumnCount() != b.getLineCount())
            throw new DifferentMatrixSizes("left matrix line count != right matrix column count");
        Matrix mulResult = new RectangularMatrix(a.getLineCount(), b.getColumnCount());
        try {
            for (int i = 0; i < a.getLineCount(); i++) {
                for (int j = 0; j < b.getColumnCount(); j++) {
                    for (int k = 0; k < b.getLineCount(); k++) {
                        double pValue = mulResult.get(i, j);
                        mulResult.set(i, j, pValue + a.get(i, k) * b.get(k, j));
                    }
                }
            }
        } catch (IndexOutOfBoundsException | ImmutableException ex) {
            System.out.println(ex);
        }
        return mulResult;
    }
}
