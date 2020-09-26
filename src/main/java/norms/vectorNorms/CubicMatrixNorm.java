package norms.vectorNorms;

import vector.Vector;

public class CubicMatrixNorm implements VectorNorm {
    @Override
    public double calculate(Vector vector) {
        double max = 0;
        for (int i = 0; i < vector.getElementCount(); i++) {
            max = Math.max(max, Math.abs(vector.get(i)));
        }
        return max;
    }
}
