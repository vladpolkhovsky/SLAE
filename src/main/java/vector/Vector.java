package vector;

import exception.ImmutableException;
import matrix.Matrix;

public interface Vector extends ColumnVectorInterface, LineVectorInterface {
    double get(int i) throws IndexOutOfBoundsException;
    double set(int i, double value) throws IndexOutOfBoundsException, ImmutableException;
    int getElementCount();
    Matrix getMatrixForm();
}
