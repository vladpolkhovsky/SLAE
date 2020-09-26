import matrix.IdentityMatrix;
import matrix.Matrix;
import matrix.SquareMatrix;
import norms.matrixNorms.CubicMatrixNorm;
import norms.vectorNorms.CubicVectorNorm;
import norms.vectorNorms.OctahedralVectorNorm;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Matrix matrix = IdentityMatrix.getMutableInstance(10);// new SquareMatrix(10);
        //for (int i = 0; i < matrix.getLineCount(); i++) {
        //    for (int j = 0; j < matrix.getColumnCount(); j++) {
        //        matrix.set(i, j, new Random().nextDouble() * 10);
        //    }
        //}
        System.out.println(matrix);
        System.out.println(new CubicMatrixNorm().calculate(matrix));
    }
}
