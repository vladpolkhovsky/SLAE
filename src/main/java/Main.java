import algo.Multiplicator;
import algo.TransposeMatrix;
import matrix.Matrix;
import matrix.RectangularMatrix;

public class Main {
    public static void main(String[] args) throws Exception {
        double a[][] = {
                { 2, -3, 1 },
                { 5, 4, -2 },
        };
        double b[][] = {
                { -7, 5 },
                { 2, -1 },
                { 4, 3 }
        };
        Matrix left = new RectangularMatrix(a);
        Matrix right = new RectangularMatrix(b);
        System.out.println(left);
        System.out.println(right);
        System.out.println(Multiplicator.multiply(left, right));
        System.out.println(TransposeMatrix.transpose(left.getLineVector(0).getMatrixForm()));
    }
}
