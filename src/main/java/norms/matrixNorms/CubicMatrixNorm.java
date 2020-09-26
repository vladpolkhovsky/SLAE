package norms.matrixNorms;

import matrix.Matrix;
import matrix.exception.OutOfBoundsMatrixException;

public class CubicMatrixNorm implements MatrixNorm {
    @Override
    public double calculate(Matrix matrix) throws OutOfBoundsMatrixException {
        double max = 0;
        for (int i = 0; i < matrix.getLineCount(); i++) {
            double columnSum = 0;
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                columnSum += Math.abs(matrix.get(i, j));
            }
            max = Math.max(max, columnSum);
        }
        return max;
    }
}
