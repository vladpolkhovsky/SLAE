package algo;

import exception.BadArgsException;
import matrix.Matrix;
import norms.matrixNorms.MatrixNorm;

public class ConditionMatrix {
    public static double getCondition(Matrix A, MatrixNorm norm) throws BadArgsException {
        double aNorm = norm.calculate(A);
        double invANorm = norm.calculate(Inverter.getInvertMatrix(A));
        return Math.abs(aNorm * invANorm);
    }
}
