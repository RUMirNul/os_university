package task.matrix;

public class Main {

    public static void main(String[] args) {
        Matrix matrixFirst = new Matrix(2, 2);
        Matrix matrixSecond = new Matrix(2, 2);

        matrixFirst.setValueByIndex(0, 0, 2);
        matrixFirst.setValueByIndex(0, 1, 3);
        matrixFirst.setValueByIndex(1, 0, 5);
        matrixFirst.setValueByIndex(1, 1, 7);

        matrixSecond.setValueByIndex(0, 0, -1);
        matrixSecond.setValueByIndex(0, 1, 2);
        matrixSecond.setValueByIndex(1, 0, -2);
        matrixSecond.setValueByIndex(1, 1, 3);

        Matrix matr1 = new Matrix(1000, 1000);
        Matrix matr2 = new Matrix(1000);
        matr1.generateRandomValue(-50, 50);
        matr2.generateRandomValue(-50, 50);

        long start = System.currentTimeMillis();
        Matrix result1 = MatrixMultiplication.calculate(matr1, matr2);
        System.out.println("Однопоточно ВРЕМЯ = " + (System.currentTimeMillis() - start));


        start = System.currentTimeMillis();
        Matrix result2 = MatrixMultiplication.calculateMultiThread(matr1, matr2, 10);
        System.out.println("Многопоточно  ВРЕМЯ = " + (System.currentTimeMillis() - start));

        System.out.println("Equals = " + result1.equals(result2));


    }
}
