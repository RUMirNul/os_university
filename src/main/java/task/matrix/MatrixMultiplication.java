package task.matrix;

public class MatrixMultiplication {

    public static Matrix calculate(Matrix first, Matrix second) {
        if (!isMatrixMultiplicationCanDo(first, second)) {
            throw new IllegalArgumentException("These matrices are not suitable for multiplying");
        }

        Matrix result = new Matrix(first.getRowCount(), second.getColumnCount());

        for (int row = 0; row < second.getRowCount(); row++) {
            for (int column = 0; column < first.getColumnCount(); column++) {
                result.setValueByIndex(row, column, calculateSingleValue(first, second, row, column));
            }
        }

        return result;
    }

    public static Matrix calculateMultiThread(Matrix first, Matrix second, int threadCount) {
        if (!isMatrixMultiplicationCanDo(first, second)) {
            throw new IllegalArgumentException("These matrices are not suitable for multiplying");
        }

        if (threadCount <= 0) {
            throw new IllegalArgumentException("The number of threads cannot be negative or equal to zero");
        }

        Matrix result = new Matrix(first.getRowCount(), second.getColumnCount());

        int elementsTotalCount = first.getAllElementsCount();

        threadCount = Math.min(threadCount, elementsTotalCount);

        int integerNumberOfElements = elementsTotalCount / threadCount;
        int remainingElements = elementsTotalCount - integerNumberOfElements * threadCount;

        Thread[] threads = new Thread[threadCount];

        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < threadCount; i++) {
            int elementsCalculatedNow = integerNumberOfElements;
            if (remainingElements > 0) {
                elementsCalculatedNow++;
                remainingElements--;
            }
            endIndex += elementsCalculatedNow;
            if (endIndex >= elementsTotalCount) {
                endIndex = elementsTotalCount - 1;
            }
            threads[i] = new CalculateMatrixMultiplicationThread(first, second, result, startIndex, endIndex);
            startIndex = endIndex + 1;
            threads[i].start();
        }

        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static int calculateSingleValue(Matrix first, Matrix second, int row, int column) {
        int result = 0;

        for (int i = 0; i < first.getColumnCount(); i++) {
            result += first.getValueByIndex(row, i) * second.getValueByIndex(i, column);
        }

        return result;
    }

    private static boolean isMatrixMultiplicationCanDo(Matrix first, Matrix second) {
        boolean columnsAndRowsEqualsLength = first.getColumnCount() == second.getRowCount();
        boolean rowsAndColumnsCorrectFirst = first.getColumnCount() > 0 && first.getRowCount() > 0;
        boolean rowsAndColumnsCorrectSecond = second.getColumnCount() > 0 && second.getRowCount() > 0;

        return columnsAndRowsEqualsLength && rowsAndColumnsCorrectFirst && rowsAndColumnsCorrectSecond;
    }
}
