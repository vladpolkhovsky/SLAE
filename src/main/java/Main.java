import algo.Multiplicator;
import algo.slae.SLAE;
import matrix.Matrix;
import matrix.RectangularMatrix;
import vector.ColumnVector;

public class Main {
    public static void main(String[] args) throws Exception {
        Matrix a = new RectangularMatrix(new double[][]{
                { 0.0944, 1.0799, 0.0000, -0.0726, 0.0726 },
                { 0.6897, -0.0908, 0.0182, 0.0363, 0.1271 },
                { 0.0545, 0.0000, 0.8676, -0.2541, 0.1452 },
                { -0.1089, 0.2287, 0.0000, 0.8531, -0.0363 },
                { 0.4538, 0.00000, 0.1634, 0.0182, 1.0164 }
        });
        ColumnVector b = new ColumnVector(new double[] {
                4.6174,
                4.2108,
                -5.8770,
                2.7842,
                0.2178
        });
        SLAE slae = new SLAE(a, b);
        slae.canSolve();
        System.out.println(slae.getRootVector());
        System.out.println(Multiplicator.multiply(a, slae.getRootVector().getMatrixForm()));
    }
}
