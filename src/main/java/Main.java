import algo.ConditionMatrix;
import algo.Multiplicator;
import algo.Inverter;
import algo.exception.DegenerateMatrixException;
import algo.slae.SLAE;
import matrix.Matrix;
import matrix.RectangularMatrix;
import norms.matrixNorms.CubicMatrixNorm;
import vector.ColumnVector;
import vector.Vector;

import javax.rmi.ssl.SslRMIClientSocketFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        /*
        test1();
        test2();
        try {
            test3();
        } catch (DegenerateMatrixException ex) {
            System.out.println("Degenerate matrix");
        }
        test4();
        test5();
         */

        //task();

        test6();
    }
    public static void test1() throws Exception {
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
        System.out.println(slae.getRootVector().toLineVector());
    }

    public static void test2() throws Exception {
        Matrix a = new RectangularMatrix(new double[][]{
                { 1, 1 },
                { 1, 0 }
        });
        ColumnVector b = new ColumnVector(new double[] {
                2,
                2
        });
        SLAE slae = new SLAE(a, b);
        System.out.println(slae.getRootVector().toLineVector());
    }

    public static void test3() throws Exception {
        Matrix a = new RectangularMatrix(new double[][]{
                { 1, 0 },
                { 1, 0 }
        });
        ColumnVector b = new ColumnVector(new double[] {
                2,
                2
        });
        SLAE slae = new SLAE(a, b);
        System.out.println(slae.getRootVector().toLineVector());
    }

    public static void test4() throws Exception {
        Matrix a = new RectangularMatrix(new double[][]{
                { 1, 1, 1 },
                { 2, 1, 1 },
                { 3, 0, 1 }
        });
        ColumnVector b = new ColumnVector(new double[] {
                1,
                2,
                3
        });
        SLAE slae = new SLAE(a, b);
        System.out.println(slae.getRootVector().toLineVector());
    }

    public static void test5() throws Exception {
        Matrix a = new RectangularMatrix(new double[][]{
                { 1, 4, 0 },
                { 0, 1, 0 },
                { 3, 0, 1 }
        });
        Matrix invA = Inverter.getInvertMatrix(a);
        try {
            System.out.println(Multiplicator.multiply(a, invA));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void task() {
        try {

            Matrix a = new RectangularMatrix(new double[][]{
                    { 0.0944, 1.0799, 0.0000, -0.0726, 0.0726   },
                    { 0.6897, -0.0908, 0.0182, 0.0363, 0.1271   },
                    { 0.0545, 0.0000, 0.8676, -0.2541, 0.1452   },
                    { -0.1089, 0.2287, 0.0000, 0.8531, -0.0363  },
                    { 0.4538, 0.00000, 0.1634, 0.0182, 1.0164   }
            });

            ColumnVector b = new ColumnVector(new double[]{
                    4.6174,
                    4.2108,
                    -5.8770,
                    2.7842,
                    0.2178
            });

            System.out.println("Уравнение вида A * x = b");
            System.out.println("Матрица A:");
            System.out.println(a);
            System.out.println("Вектор b:");
            System.out.println(b);

            Vector rootVector = new SLAE(a, b).getRootVector().toColumnVector();
            Vector resVector = Multiplicator.multiply(a, rootVector.getMatrixForm()).getColumnVector(0);

            System.out.println("Вектор x:");
            System.out.println(rootVector);

            double sum = 0.0;
            for (int i = 0; i < rootVector.getElementCount(); i++) {
                sum += Math.abs(resVector.get(i) - b.get(i));
            }

            System.out.printf("Сумма погрешности = %e\n", sum);

            System.out.printf("V(A) = ||A|| * ||A^(-1)|| == %f", ConditionMatrix.getCondition(a, new CubicMatrixNorm()));

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void test6() throws Exception {
        Matrix a = new RectangularMatrix(new double[][]{
                { 3, 7 },
                { 7, 2 }
        });
        ColumnVector b = new ColumnVector(new double[] {
                12,
                20
        });
        SLAE slae = new SLAE(a, b);
        try {
            System.out.println(slae.getRootVector().toLineVector());
            System.out.println(Multiplicator.multiply(a, slae.getRootVector().getMatrixForm()));
        } catch (DegenerateMatrixException ex) {
            System.out.println("Degenerate matrix");
        }
    }
}
