import algo.Multiplicator;
import algo.TransposeMatrix;
import matrix.Matrix;
import matrix.RectangularMatrix;
import vector.IdentityVector;

public class Main {
    public static void main(String[] args) throws Exception {
        double[][] a = {
                { 2, 3 },
                { 1, 4 }
        };
        double[][] b = {
                { 2, 2 },
                { 2, 3}
        };
        System.out.println(IdentityVector.getMutableInstance(12).toColumnVector().toLineVector());
    }
}
