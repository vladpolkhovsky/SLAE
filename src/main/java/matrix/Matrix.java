package matrix;

import matrix.exception.ImmutableMatrixException;
import matrix.exception.OutOfBoundsMatrixException;

public interface Matrix {
    double get(int i, int j) throws OutOfBoundsMatrixException;
    double set(int i, int j, double value) throws OutOfBoundsMatrixException, ImmutableMatrixException;
    int getLineCount();
    int getColumnCount();
}
