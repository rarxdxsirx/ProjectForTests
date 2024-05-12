package com.example.projectfortests;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;

import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Test
    public void onEqualsClick() {

        PresentationImpl pr = new PresentationImpl();
        DataModel model = new DataModel("5", "10", "=");
        String result = pr.getOperationResult(model);
        assertEquals("5", result); // Результат должен быть равен первому числу
    }
    @Test
    public void testGetOperationResult_Addition() {
        PresentationImpl pr = new PresentationImpl();
        DataModel model = new DataModel("5", "10", "+");
        String result = pr.getOperationResult(model);
        assertEquals("15", result);
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
    public void testOnOperationClickSetsNumberToResultFieldWhenEmpty() {
        MainActivity activity = new MainActivity();
        activity.onCreate(null);

        Button button = new Button(activity);
        button.setText("+");

        activity.resultField.setText(""); // Устанавливаем resultField пустым
        activity.numberField.setText("123");
        activity.onOperationClick(button);

        assertEquals("123", activity.resultField.getText().toString());
        assertEquals("", activity.numberField.getText().toString());
    }


    @Test
    public void testOnEqualsClickSetsResultAndClearsFields() {
        MainActivity activity = new MainActivity();
        activity.onCreate(null);

        PresentationImpl pr = new PresentationImpl();

        activity.resultField.setText("");
        activity.numberField.setText("10");
        activity.operationField.setText("=");

        activity.onEqualsClick(new View(activity)); // View не используется в методе

        DataModel expectedModel = new DataModel("5", "10", "="); // Предполагаем, что первое число всегда "5"
        String expectedResult = pr.getOperationResult(expectedModel);

        assertEquals(expectedResult, activity.resultField.getText().toString());
        assertEquals("", activity.numberField.getText().toString());
        assertEquals("=", activity.operationField.getText().toString());
    }

    @Test
    public void testOnGetDataClickSetsFieldsFromDataModel() {
        MainActivity activity = new MainActivity();
        activity.onCreate(null);

        // Предполагаем, что DataImpl.getData() возвращает DataModel с определенными значениями
        // В реальном тесте нужно будет настроить DataImpl или использовать mock-объект
        DataModel expectedData = new DataModel("10", "20", "+");

        activity.onGetDataClick(new View(activity)); // View не используется в методе

        assertEquals(expectedData.getFirstData(), activity.numberField.getText().toString());
        assertEquals(expectedData.getSecondData(), activity.resultField.getText().toString());
        assertEquals(expectedData.getThirdData(), activity.operationField.getText().toString());
    }

    @Test
    public void testOnSaveDataClickSetsSuccessMessageWhenSaveSuccessful() {
        MainActivity activity = new MainActivity();
        activity.onCreate(null);

        // Предполагаем, что DataImpl.saveData() возвращает true
        // В реальном тесте нужно будет настроить DataImpl или использовать mock-объект
        activity.numberField.setText("123");
        activity.resultField.setText("456");
        activity.operationField.setText("+");

        activity.onSaveDataClick(new View(activity));

        assertEquals("Результат успешно сохранен", activity.numberField.getText().toString());
        assertEquals("", activity.resultField.getText().toString());
        assertEquals("", activity.operationField.getText().toString());
    }

    @Test
    public void testOnCleanTextClickClearsAllFields() {
        MainActivity activity = new MainActivity();
        activity.onCreate(null);

        activity.numberField.setText("123");
        activity.resultField.setText("456");
        activity.operationField.setText("+");

        activity.onCleanTextClick(new View(activity));

        assertEquals("", activity.numberField.getText().toString());
        assertEquals("", activity.resultField.getText().toString());
        assertEquals("", activity.operationField.getText().toString());
    }

    @Test
    public void testDataModelGenerateCreatesDataModelFromFields() {
        MainActivity activity = new MainActivity();
        activity.onCreate(null);
        activity.numberField.setText("123");
        activity.resultField.setText("456");
        activity.operationField.setText("+");
        DataModel model = activity.DataModelGenerate();
        assertEquals("123", model.getFirstData());
        assertEquals("456", model.getSecondData());
        assertEquals("+", model.getThirdData());
    }
}