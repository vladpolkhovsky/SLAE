package algo.slae;

import exception.ImmutableException;
import matrix.IdentityMatrix;
import matrix.SquareMatrix;

public class PermutationMatrix extends SquareMatrix {
    public PermutationMatrix(int n, int i, int j) {
        super(IdentityMatrix.getMutableInstance(n));
        try {
            super.set(i, i, 0);
            super.set(j, j, 0);
            super.set(i, j, 1);
            super.set(j, i, 1);
        } catch (IndexOutOfBoundsException | ImmutableException ex) {

        }
    }
    @Override
    public double set(int i, int j, double value) throws ImmutableException {
        throw new ImmutableException("Permutation matrix is immutable.");
    }
}
