package norms.matrixNorms;

import matrix.Matrix;

public class CubicMatrixNorm implements MatrixNorm {
    @Override
    public double calculate(Matrix matrix) {
        double max = 0;
        try {
            for (int i = 0; i < matrix.getLineCount(); i++) {
                double columnSum = 0;
                for (int j = 0; j < matrix.getColumnCount(); j++) {
                    columnSum += Math.abs(matrix.get(i, j));
                }
                max = Math.max(max, columnSum);
            }
        } catch (IndexOutOfBoundsException ex) {

        }
        return max;
    }
}
