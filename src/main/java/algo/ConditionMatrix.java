package algo;

import algo.exception.DifferentMatrixSizes;
import exception.BadArgsException;
import matrix.Matrix;
import norms.matrixNorms.CubicMatrixNorm;
import norms.matrixNorms.MatrixNorm;

public class ConditionMatrix {
    public static double getCondition(Matrix A, MatrixNorm norm) throws BadArgsException {
        double aNorm = new CubicMatrixNorm().calculate(A);
        double invANorm = new CubicMatrixNorm().calculate(Inverter.getInvertMatrix(A));
        return Math.abs(aNorm * invANorm);
    }
}
