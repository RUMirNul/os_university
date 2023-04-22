package task.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixMultiplicationTest {

    @Test
    void calculate_whenMultiplicationMatrix_thenCorrect() {
        //Given
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

        //When
        Matrix result = MatrixMultiplication.calculate(matrixFirst, matrixSecond);

        //Then
        assertEquals(-8, result.getValueByIndex(0, 0));
        assertEquals(13, result.getValueByIndex(0, 1));
        assertEquals(-19, result.getValueByIndex(1, 0));
        assertEquals(31, result.getValueByIndex(1, 1));
    }

    @Test
    void calculate_whenMultiplicationUnevenSizeMatrix_thenException() {
        //Given
        Matrix matrixFirst = new Matrix(3, 3);
        Matrix matrixSecond = new Matrix(2, 2);

        //When
        Throwable result = assertThrows(IllegalArgumentException.class,
                () -> MatrixMultiplication.calculate(matrixFirst, matrixSecond));

        //Then
        assertEquals("These matrices are not suitable for multiplying", result.getMessage());
    }

    @Test
    void calculateMultiThread_whenMultiplicationMatrix_thenCorrect() {
        //Given
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

        //When
        Matrix result = MatrixMultiplication.calculateMultiThread(matrixFirst, matrixSecond, 2);

        //Then
        assertEquals(-8, result.getValueByIndex(0, 0));
        assertEquals(13, result.getValueByIndex(0, 1));
        assertEquals(-19, result.getValueByIndex(1, 0));
        assertEquals(31, result.getValueByIndex(1, 1));
    }

    @Test
    void calculateMultiThread_whenMultiplicationUnevenMatrix_thenException() {
        //Given
        Matrix matrixFirst = new Matrix(3, 3);
        Matrix matrixSecond = new Matrix(2, 2);

        //When
        Throwable result = assertThrows(IllegalArgumentException.class,
                () -> MatrixMultiplication.calculateMultiThread(matrixFirst, matrixSecond, 2));

        //Then
        assertEquals("These matrices are not suitable for multiplying", result.getMessage());
    }

    @Test
    void calculateMultiThread_whenThreadCountNegative_thenException() {
        //Given
        Matrix matrixFirst = new Matrix(2, 2);
        Matrix matrixSecond = new Matrix(2, 2);

        //When
        Throwable result = assertThrows(IllegalArgumentException.class,
                () -> MatrixMultiplication.calculateMultiThread(matrixFirst, matrixSecond, -2));

        //Then
        assertEquals("The number of threads cannot be negative or equal to zero", result.getMessage());
    }

    @Test
    void calculateMultiThread_whenThreadCountIsZero_thenException() {
        //Given
        Matrix matrixFirst = new Matrix(2, 2);
        Matrix matrixSecond = new Matrix(2, 2);

        //When
        Throwable result = assertThrows(IllegalArgumentException.class,
                () -> MatrixMultiplication.calculateMultiThread(matrixFirst, matrixSecond, 0));

        //Then
        assertEquals("The number of threads cannot be negative or equal to zero", result.getMessage());
    }

    @Test
    void calculateSingleValue_whenSquareMatrix_thenCorrect() {
        //Given
        Matrix matrixFirst = new Matrix(2, 2);
        Matrix matrixSecond = new Matrix(2, 2);

        matrixFirst.generateRandomValue(-100, 100);
        matrixSecond.generateRandomValue(-100, 100);


        int actualValue1 = MatrixMultiplication.calculateSingleValue(matrixFirst, matrixSecond, 0, 0);
        int actualValue2 = MatrixMultiplication.calculateSingleValue(matrixFirst, matrixSecond, 0, 1);
        int actualValue3 = MatrixMultiplication.calculateSingleValue(matrixFirst, matrixSecond, 1, 0);
        int actualValue4 = MatrixMultiplication.calculateSingleValue(matrixFirst, matrixSecond, 1, 1);

        //When
        int expectedValue1 = matrixFirst.getValueByIndex(0, 0) * matrixSecond.getValueByIndex(0, 0) +
                matrixFirst.getValueByIndex(0, 1) * matrixSecond.getValueByIndex(1, 0);
        int expectedValue2 = matrixFirst.getValueByIndex(0, 0) * matrixSecond.getValueByIndex(0, 1) +
                matrixFirst.getValueByIndex(0, 1) * matrixSecond.getValueByIndex(1, 1);
        int expectedValue3 = matrixFirst.getValueByIndex(1, 0) * matrixSecond.getValueByIndex(0, 0) +
                matrixFirst.getValueByIndex(1, 1) * matrixSecond.getValueByIndex(1, 0);
        int expectedValue4 = matrixFirst.getValueByIndex(1, 0) * matrixSecond.getValueByIndex(0, 1) +
                matrixFirst.getValueByIndex(1, 1) * matrixSecond.getValueByIndex(1, 1);

        //Then
        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
        assertEquals(expectedValue3, actualValue3);
        assertEquals(expectedValue4, actualValue4);
    }

    @Test
    void calculateSingleValue_whenRectangularMatrix_thenCorrect() {
        //Given
        final int valueFirstMatrix = (int) (Math.random() * -20);
        final int valueSecondMatrix = (int) (Math.random() * 50);
        Matrix matrixFirst = new Matrix(12, 11);
        Matrix matrixSecond = new Matrix(11, 13);

        matrixFirst.setIdenticalValueAllElements(valueFirstMatrix);
        matrixSecond.setIdenticalValueAllElements(valueSecondMatrix);

        int expectedResult = valueFirstMatrix * valueSecondMatrix * matrixFirst.getColumnCount();

        //When
        boolean result = true;

        for (int row = 0; row < matrixFirst.getRowCount(); row++) {
            for (int column = 0; column < matrixSecond.getColumnCount(); column++) {
                result = result && (expectedResult == MatrixMultiplication.calculateSingleValue(matrixFirst, matrixSecond, row, column));
            }
        }

        //Then
        assertTrue(result);
    }
}