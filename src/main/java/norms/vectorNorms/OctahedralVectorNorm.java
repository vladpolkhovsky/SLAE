package norms.vectorNorms;

import vector.Vector;

public class OctahedralVectorNorm implements VectorNorm {
    @Override
    public double calculate(Vector vector) {
        double sum = 0;
        try {
            for (int i = 0; i < vector.getElementCount(); i++) {
                sum += Math.abs(vector.get(i));
            }
        } catch (IndexOutOfBoundsException outOfBoundsVectorException) {

        }
        return sum;
    }
}
