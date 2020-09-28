package vector;

import matrix.Matrix;
import vector.exception.ImmutableVectorException;
import vector.exception.OutOfBoundsVectorException;

public interface Vector extends ColumnVectorInterface, LineVectorInterface {
    double get(int i) throws OutOfBoundsVectorException;
    double set(int i, double value) throws OutOfBoundsVectorException, ImmutableVectorException;
    int getElementCount();
    Matrix getMatrixForm();
}
