import matrix.IdentityMatrix;
import matrix.Matrix;
import matrix.SquareMatrix;
import norms.matrixNorms.CubicMatrixNorm;
import norms.vectorNorms.CubicVectorNorm;
import norms.vectorNorms.OctahedralVectorNorm;
import vector.ColumnVector;
import vector.LineVector;
import vector.Vector;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Vector vector = new ColumnVector(2);
        System.out.println(vector.toColumnVector());
    }
}
