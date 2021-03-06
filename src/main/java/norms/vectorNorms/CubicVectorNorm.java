package norms.vectorNorms;

import vector.Vector;

public class CubicVectorNorm implements VectorNorm {
    @Override
    public double calculate(Vector vector) {
        double max = 0;
        try {
            for (int i = 0; i < vector.getElementCount(); i++) {
                max = Math.max(max, Math.abs(vector.get(i)));
            }
        } catch (IndexOutOfBoundsException outOfBoundsVectorException) {

        }
        return max;
    }
}
