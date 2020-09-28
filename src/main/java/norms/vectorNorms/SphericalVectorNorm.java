package norms.vectorNorms;

import vector.Vector;
import vector.exception.OutOfBoundsVectorException;

public class SphericalVectorNorm implements VectorNorm {
    @Override
    public double calculate(Vector vector) {
        double sum = 0;
        try {
            for (int i = 0; i < vector.getElementCount(); i++) {
                double value = vector.get(i);
                sum += value * value;
            }
        } catch (OutOfBoundsVectorException outOfBoundsVectorException) {

        }
        return Math.sqrt(sum);
    }
}
