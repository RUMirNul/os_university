package task.matrix;

public class CalculateMatrixMultiplicationThread extends Thread {

    private final Matrix firstMatrix;
    private final Matrix secondMatrix;
    private final Matrix resultMatrix;
    private final int startIndex;
    private final int endIndex;

    public CalculateMatrixMultiplicationThread(Matrix firstMatrix, Matrix secondMatrix, Matrix resultMatrix, int startIndex, int endIndex) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.resultMatrix = resultMatrix;
    }

    @Override
    public void run() {
        for (int i = startIndex; i <= endIndex; i++) {
            int row = i / resultMatrix.getColumnCount();
            int column = i % resultMatrix.getColumnCount();
            resultMatrix.setValueByIndex(row, column, MatrixMultiplication.calculateSingleValue(firstMatrix, secondMatrix, row, column));
        }
    }


    @Override
    public String toString() {
        return "CalculateMatrixMultiplicationThread{" +
                "startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                '}';
    }
}
