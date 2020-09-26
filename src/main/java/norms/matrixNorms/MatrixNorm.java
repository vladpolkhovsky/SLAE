package norms.matrixNorms;

import matrix.Matrix;
import matrix.exception.OutOfBoundsMatrixException;

public interface MatrixNorm {
    double calculate(Matrix matrix) throws OutOfBoundsMatrixException;
}
