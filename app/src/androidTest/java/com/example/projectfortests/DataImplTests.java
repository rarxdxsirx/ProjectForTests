package com.example.projectfortests;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DataImplTests {

    private static final String SHARED_PREFS_NAME = "shared_prefs_name";

    private static final String KEY_FIRST_DATA = "firstData";

    private static final String KEY_SECOND_DATA = "secondData";

    private static final String KEY_THIRD_DATA = "thirdData";

    private static final String DEFAULT_DATA = "Default";
    private static final String TEST_PREFS_NAME = "test_prefs"; // Уникальное имя для тестовых SharedPreferences

    Context context;
    DataImpl dataImpl;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dataImpl = new DataImpl(context, TEST_PREFS_NAME); // Передаем имя тестовых SharedPreferences
    }

    @After
    public void tearDown() {
        context.getSharedPreferences(TEST_PREFS_NAME, Context.MODE_PRIVATE).edit().clear().commit(); // Очищаем SharedPreferences после теста
    }

    @Test
    public void testSaveData_Success() {
        DataModel data = new DataModel("10", "20", "+");
        boolean result = dataImpl.saveData(data);

        assertTrue(result);

        // Проверяем, что данные сохранены в SharedPreferences
        SharedPreferences prefs = context.getSharedPreferences(TEST_PREFS_NAME, Context.MODE_PRIVATE);
        assertEquals("10", prefs.getString(KEY_FIRST_DATA, null));
        assertEquals("20", prefs.getString(KEY_SECOND_DATA, null));
        assertEquals("+", prefs.getString(KEY_THIRD_DATA, null));
    }

    @Test
    public void testSaveData_NullData() {
        boolean result = dataImpl.saveData(null);
        assertFalse(result);
    }

    @Test
    public void testSaveData_ZeroFirstData() {
        DataModel data = new DataModel("0", "20", "+");
        boolean result = dataImpl.saveData(data);
        assertFalse(result);
    }

    @Test
    public void testGetData_DefaultValues() {
        DataModel result = dataImpl.getData();

        assertEquals(DEFAULT_DATA, result.getFirstData());
        assertEquals(DEFAULT_DATA, result.getSecondData());
        assertEquals(DEFAULT_DATA, result.getThirdData());
    }

    @Test
    public void testGetData_ExistingValues() {
        SharedPreferences prefs = context.getSharedPreferences(TEST_PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_FIRST_DATA, "10").putString(KEY_SECOND_DATA, "20").putString(KEY_THIRD_DATA, "+").commit();

        DataModel result = dataImpl.getData();

        assertEquals("10", result.getFirstData());
        assertEquals("20", result.getSecondData());
        assertEquals("+", result.getThirdData());
    }
}