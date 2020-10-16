import algo.slae.ConditionMatrix;
import algo.Multiplicator;
import algo.Inverter;
import algo.Substractor;
import algo.exception.DegenerateMatrixException;
import algo.slae.SLAE;
import matrix.Matrix;
import matrix.RectangularMatrix;
import matrix.SquareMatrix;
import norms.matrixNorms.CubicMatrixNorm;
import vector.ColumnVector;
import vector.Vector;

import java.util.Random;

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
        task();
        test6();
 */
        //testDet();
        task();

        //random(new Random().nextLong());
    }

    public static void testDet() throws Exception {
        Matrix a = new RectangularMatrix(new double[][]{
                { 1, 2, 3 },
                { 4, 5*2, 6 },
                { 10, 7, 8 }
        });
        ColumnVector b = new ColumnVector(new double[] {
                5,
                14,
                34
        });
        SLAE slae = new SLAE(a, b);
        System.out.println(slae.getDet());
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
        Matrix A = new RectangularMatrix(new double[][]{
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
        System.out.println(A);
        System.out.println("Вектор b:");
        System.out.println(b);
        SLAE slaeAxB = new SLAE(A, b);
        Vector rootVector = slaeAxB.getRootVector().toColumnVector();
        double detA = slaeAxB.getDet();
        System.out.println("Определитель A = " + detA);
        System.out.println("\nВектор x:");
        System.out.println(rootVector);
        double sum = 0.0;
        Vector r = Substractor.substract(Multiplicator.multiply(A, rootVector.getMatrixForm()), b.getMatrixForm()).getColumnVector(0);
        for (int i = 0; i < r.getElementCount(); i++) {
            sum += r.get(i);
        }
        System.out.println("\nВектор невязки r:");
        System.out.println(r);
        System.out.printf("Сумма элементов вектора невязки = %e\n", sum);
        System.out.printf("V(A) = ||A|| * ||A^(-1)|| == %f\n", ConditionMatrix.getCondition(A, new CubicMatrixNorm()));
        System.out.println("Обратная матрица:\n" + Multiplicator.multiply(A, Inverter.getInvertMatrix(A)));
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

    public static void random(long seed) {
        Random random = new Random();
        random.setSeed(seed);
        System.out.println("Seed = " + seed);
        int n = random.nextInt(10) + 10;
        System.out.println("n = " + n);
        try {
            Matrix A = new SquareMatrix(n);
            Vector x = new ColumnVector(n);

            for (int i = 0; i < n; i++) {
                x.set(i, random.nextDouble() * new Random().nextInt(100));
                for (int j = 0; j < n; j++) {
                    A.set(i, j, random.nextDouble() / new Random().nextInt(10000));
                }
            }

            Vector b = Multiplicator.multiply(A, x.getMatrixForm()).getColumnVector(0);

            SLAE slae = new SLAE(A, b);
            Vector root = slae.getRootVector();

            System.out.println(A);
            System.out.println(x.toLineVector());
            System.out.println(root.toLineVector());

            double sum = 0.0;
            for (int i = 0; i < n; i++) {
                sum += Math.abs(x.get(i) - root.get(i));
            }

            System.out.println("delta = " + sum);
            System.out.println("Condition = " + ConditionMatrix.getCondition(A, new CubicMatrixNorm()));

        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
}
