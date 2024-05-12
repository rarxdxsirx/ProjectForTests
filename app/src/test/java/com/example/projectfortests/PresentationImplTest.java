package com.example.projectfortests;

import static org.junit.Assert.*;

import org.junit.Test;

public class PresentationImplTest {

    // accepted

    @Test
    public void testCheckNumberValue_ValidNumber() {
        PresentationImpl pr = new PresentationImpl();
        assertTrue(pr.checkNumberValue("5"));
    }
    @Test
    public void testGetOperationResult_LargeNumbers() {
        PresentationImpl pr = new PresentationImpl();
        DataModel model = new DataModel("999999999", "999999999", "+");
        String result = pr.getOperationResult(model);
        assertEquals("1999999998", result); // Проверяем, что нет переполнения
    }
    @Test
    public void testGetOperationResult_InvalidNumberFormat() {
        PresentationImpl pr = new PresentationImpl();
        DataModel model = new DataModel("abc", "10", "+");
        // Ожидаемое поведение зависит от вашей реализации,
        // например, можно выбрасывать исключение или возвращать специальное значение
        assertThrows(NumberFormatException.class, () -> pr.getOperationResult(model));
    }
    @Test
    public void testGetOperationResult_NegativeNumbers() {
        PresentationImpl pr = new PresentationImpl();
        DataModel model = new DataModel("-5", "10", "+");
        String result = pr.getOperationResult(model);
        assertEquals("5", result);
    }
    @Test
    public void testGetOperationResult_EmptyFirstData() {
        PresentationImpl pr = new PresentationImpl();
        DataModel model = new DataModel("", "10", "+");
        // Ожидаемое поведение зависит от вашей реализации
        assertThrows(IllegalArgumentException.class, () -> pr.getOperationResult(model));
    }

    @Test
    public void testGetOperationResult_EmptySecondData() {
        PresentationImpl pr = new PresentationImpl();
        DataModel model = new DataModel("5", "", "+");
        // Ожидаемое поведение зависит от вашей реализации
        assertThrows(IllegalArgumentException.class, () -> pr.getOperationResult(model));
    }
}