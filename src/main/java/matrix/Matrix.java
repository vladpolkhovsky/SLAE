package matrix;

import exception.ImmutableException;
import vector.Vector;

public interface Matrix {
    double get(int i, int j) throws IndexOutOfBoundsException;
    double set(int i, int j, double value) throws IndexOutOfBoundsException, ImmutableException;
    int getLineCount();
    int getColumnCount();
    Vector getLineVector(int i) throws IndexOutOfBoundsException;
    Vector getColumnVector(int j) throws IndexOutOfBoundsException;
}
