package task.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void createMatrix_whenNegativeRowCount_thenException() {
        //Given

        //When
        Throwable result = assertThrows(IllegalArgumentException.class,
                () -> new Matrix(-10, 10));

        //Then
        assertEquals("The number of rows cannot be negative or zero", result.getMessage());
    }

    @Test
    void createMatrix_whenNegativeColumnCount_thenException() {
        //Given

        //When
        Throwable result = assertThrows(IllegalArgumentException.class,
                () -> new Matrix(10, -10));

        //Then
        assertEquals("The number of columns cannot be negative or zero", result.getMessage());
    }

    @Test
    void createMatrix_whenCorrectArgs_thenCorrect() {
        //Given
        final int rowCount = 3;
        final int columnCount = 5;

        //Then
        Matrix matrix = new Matrix(rowCount, columnCount);

        //When
        assertEquals(rowCount, matrix.getRowCount());
        assertEquals(columnCount, matrix.getColumnCount());
        assertEquals(rowCount * columnCount, matrix.getAllElementsCount());
    }

    @Test
    void setValue_whenRowOrColumnIndexNegative_thenException() {
        //Given
        Matrix matrix = new Matrix(2, 3);

        //When
        Throwable result = assertThrows(IllegalArgumentException.class,
                () -> matrix.setValueByIndex(-2, -1, 10));

        //Then
        assertEquals("The index cannot be negative", result.getMessage());
    }

    @Test
    void setValue_whenRowOrColumnIndexCountOutOfIndex_thenException() {
        //Given
        Matrix matrix = new Matrix(2, 3);

        //When
        Throwable result = assertThrows(IllegalArgumentException.class,
                () -> matrix.setValueByIndex(3, 2, 10));

        //Then
        assertEquals("The index goes beyond the array", result.getMessage());
    }

    @Test
    void setValue_whenCorrectArgs_thenCorrect() {
        //Given
        final int value = (int) (Math.random() * 1000);
        Matrix matrix = new Matrix(2, 3);

        //When
        matrix.setValueByIndex(1, 2, value);

        //Then
        assertEquals(value, matrix.getValueByIndex(1, 2));
    }

    @Test
    void getValue_whenRowOrColumnIndexNegative_thenException() {
        //Given
        Matrix matrix = new Matrix(2, 3);

        //When
        Throwable result = assertThrows(IllegalArgumentException.class,
                () -> matrix.getValueByIndex(-2, -1));

        //Then
        assertEquals("The index cannot be negative", result.getMessage());
    }

    @Test
    void getValue_whenRowOrColumnIndexCountOutOfIndex_thenException() {
        //Given
        Matrix matrix = new Matrix(2, 3);

        //When
        Throwable result = assertThrows(IllegalArgumentException.class,
                () -> matrix.getValueByIndex(3, 2));

        //Then
        assertEquals("The index goes beyond the array", result.getMessage());
    }

    @Test
    void getValue_whenCorrectArgs_thenCorrect() {
        //Given
        final int value = (int) (Math.random() * 1000);
        Matrix matrix = new Matrix(2, 3);

        //When
        matrix.setValueByIndex(1, 2, value);

        //Then
        assertEquals(value, matrix.getValueByIndex(1, 2));
    }

    @Test
    void generateRandomValue_whenMaxValueLessMin_thenCorrect() {
        //Given
        final int minRandomValue = -100;
        final int maxRandomValue = 150;
        Matrix matrix = new Matrix(2, 10);

        //When
        matrix.generateRandomValue(maxRandomValue, minRandomValue);

        boolean result = true;

        for (int row = 0; row < matrix.getRowCount(); row++) {
            for (int column = 0; column < matrix.getColumnCount(); column++) {
                result = result && (matrix.getValueByIndex(row, column) <= maxRandomValue);
                result = result && (matrix.getValueByIndex(row, column) >= minRandomValue);
            }
        }
        //Then
        assertTrue(result);
    }

    @Test
    void generateRandomValue_whenCorrectArgs_thenCorrect() {
        //Given
        final int minRandomValue = -100;
        final int maxRandomValue = 150;
        Matrix matrix = new Matrix(2, 10);

        //When
        matrix.generateRandomValue(minRandomValue, maxRandomValue);

        boolean result = true;

        for (int row = 0; row < matrix.getRowCount(); row++) {
            for (int column = 0; column < matrix.getColumnCount(); column++) {
                result = result && (matrix.getValueByIndex(row, column) <= maxRandomValue);
                result = result && (matrix.getValueByIndex(row, column) >= minRandomValue);
            }
        }
        //Then
        assertTrue(result);
    }

    @Test
    void testEquals_whenNotEqualsRowCount_thenFalse() {
        //Given
        Matrix matrix1 = new Matrix(10, 10);
        Matrix matrix2 = new Matrix(15, 10);

        //When
        boolean result = matrix1.equals(matrix2);

        //Then
        assertFalse(result);
    }

    @Test
    void testEquals_whenNotEqualsColumnCount_thenFalse() {
        //Given
        Matrix matrix1 = new Matrix(10, 10);
        Matrix matrix2 = new Matrix(10, 12);

        //When
        boolean result = matrix1.equals(matrix2);

        //Then
        assertFalse(result);
    }

    @Test
    void testEquals_whenNotEqualsMatrixElements_thenFalse() {
        //Given
        Matrix matrix1 = new Matrix(10, 10);
        Matrix matrix2 = new Matrix(10, 10);

        matrix1.generateRandomValue(-100, 100);
        matrix2.generateRandomValue(150, 200);

        //When
        boolean result = matrix1.equals(matrix2);

        //Then
        assertFalse(result);
    }

    @Test
    void testEquals_whenEqualsMatrixElements_thenTrue() {
        //Given
        Matrix matrix1 = new Matrix(10, 10);
        Matrix matrix2 = new Matrix(10, 10);

        matrix1.setIdenticalValueAllElements(34);
        matrix2.setIdenticalValueAllElements(34);

        //When
        boolean result = matrix1.equals(matrix2);

        //Then
        assertTrue(result);
    }

    @Test
    void setIdenticalValueAllElements_thenCorrect() {
        //Given
        final int value = (int) (Math.random() * 1000);
        Matrix matrix = new Matrix(10, 10);

        //When
        matrix.setIdenticalValueAllElements(value);

        boolean result = true;
        for (int row = 0; row < matrix.getRowCount(); row++) {
            for (int column = 0; column < matrix.getColumnCount(); column++) {
                result = result && (value == matrix.getValueByIndex(row, column));
            }
        }

        //Then
        assertTrue(result);
    }
}