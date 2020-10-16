package algo;

import algo.slae.SLAE;
import exception.BadArgsException;
import exception.ImmutableException;
import matrix.IdentityMatrix;
import matrix.Matrix;
import matrix.SquareMatrix;
import vector.Vector;

public class Inverter {
    static void checker(Matrix A) throws BadArgsException {
        if (A.getColumnCount() != A.getLineCount()) {
            throw new BadArgsException("A - must be square");
        }
        try {
            A.set(0, 0, A.get(0, 0));
        } catch (ImmutableException ex) {
            throw new BadArgsException(ex.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            throw new BadArgsException("A less then 1x1");
        }
    }
    public static Matrix getInvertMatrix(Matrix A) throws BadArgsException {
        checker(A);
        try {
            int n = A.getLineCount();
            Matrix identity = IdentityMatrix.getMutableInstance(n);
            Matrix invertMatrix = new SquareMatrix(n);
            for (int i = 0; i < n; i++) {
                Vector vector = new SLAE(A, identity.getLineVector(i)).getRootVector();
                for (int j = 0; j < n; j++) {
                    invertMatrix.set(j, i, vector.get(j));
                }
            }
            return invertMatrix;
        } catch (Exception ex) {
            throw new BadArgsException(ex.getMessage());
        }
    }
}
