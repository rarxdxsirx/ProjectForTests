package com.example.projectfortests;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will     execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.projectfortests", appContext.getPackageName());
    }
    @Test
    public void testGetOperationResult_Addition() {
        PresentationImpl pr = new PresentationImpl();
        DataModel model = new DataModel("5", "10", "+");
        String result = pr.getOperationResult(model);
        assertEquals("15", result);
    }
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
    public void testGetOperationResult_CombinedOperations() { //сложные вычисления
        PresentationImpl pr = new PresentationImpl();
        DataModel model = new DataModel("5", "10", "+");
        String intermediateResult = pr.getOperationResult(model); // 15

        model.setFirstData(intermediateResult);
        model.setSecondData("2");
        model.setThirdData("*");

        String finalResult = pr.getOperationResult(model);
        assertEquals("30", finalResult);
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
    @Test
    public void testGetOperationResult_EqualWithNoChange() {
        PresentationImpl pr = new PresentationImpl();
        DataModel model = new DataModel("5", "10", "=");
        String result = pr.getOperationResult(model);
        assertEquals("5", result); // Результат должен быть равен первому числу
    }

}