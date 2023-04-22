package task.matrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private final List<List<Integer>> matrix;
    private final int rowCount;
    private final int columnCount;
    private final int allElementsCount;


    public Matrix(int rowCount, int columnCount) {
        if (rowCount <= 0) {
            throw new IllegalArgumentException("The number of rows cannot be negative or zero");
        }

        if (columnCount <= 0) {
            throw new IllegalArgumentException("The number of columns cannot be negative or zero");
        }

        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.allElementsCount = rowCount * columnCount;
        this.matrix = new ArrayList<>(rowCount);

        for (int i = 0; i < rowCount; i++) {
                matrix.add(new ArrayList<>(columnCount));
        }

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                matrix.get(i).add(0);
            }
        }
    }

    public Matrix(int rowAndColumnCount) {
        this(rowAndColumnCount, rowAndColumnCount);
    }

    public void setValueByIndex(int rowIndex, int columnIndex, int value) {
        if (rowIndex < 0 || columnIndex < 0) {
            throw new IllegalArgumentException("The index cannot be negative");
        }

        if (rowIndex < rowCount && columnIndex < columnCount) {
            matrix.get(rowIndex).set(columnIndex, value);
        } else {
            throw new IllegalArgumentException("The index goes beyond the array");
        }
    }

    public int getValueByIndex(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || columnIndex < 0) {
            throw new IllegalArgumentException("The index cannot be negative");
        }

        if (rowIndex < rowCount && columnIndex < columnCount) {
            return matrix.get(rowIndex).get(columnIndex);
        } else {
            throw new IllegalArgumentException("The index goes beyond the array");
        }
    }

    public void setIdenticalValueAllElements(int value) {
        for (int row = 0; row < getRowCount(); row++) {
            for (int column = 0; column < getColumnCount(); column++) {
                setValueByIndex(row, column, value);
            }
        }
    }

    public void generateRandomValue(int min, int max) {
        if (min > max) {
            int temp = max;
            max = min;
            min = temp;
        }
        boolean minMaxValuesEquals = min == max;

        max -= min;
        for (int row = 0; row < getRowCount(); row++) {
            for (int column = 0; column < getColumnCount(); column++) {
                int setNewValue = minMaxValuesEquals ? min : ((int) (Math.random() * (max + 1)) + min);

                setValueByIndex(row, column, setNewValue);
            }
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getAllElementsCount() {
        return allElementsCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                sb.append(String.format("%7d", getValueByIndex(row, column)));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix1 = (Matrix) o;

        if (rowCount != matrix1.rowCount) return false;
        if (columnCount != matrix1.columnCount) return false;

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (!matrix.get(row).get(column).equals(matrix1.matrix.get(row).get(column))) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = matrix.hashCode();
        result = 31 * result + rowCount;
        result = 31 * result + columnCount;
        return result;
    }
}
