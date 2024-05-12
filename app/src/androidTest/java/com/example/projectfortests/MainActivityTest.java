package com.example.projectfortests;

import static org.junit.Assert.assertEquals;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Rule
    public androidx.test.rule.ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

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
        // supposed to be failed cause of bug
        onView(withId(R.id.numberField)).perform(typeText("123"));
        onView(withId(R.id.getButton)).perform(click());
        assertEquals("123", getText(withId(R.id.numberField))); // notEQUALS
        assertEquals("", getText(withId(R.id.resultField)));
    }

    @Test
    public void testOnEqualsClickSetsResultAndClearsFields() {
        // supposed to be failed cause of bug
        PresentationImpl pr = new PresentationImpl();
        onView(withId(R.id.numberField)).perform(typeText("5"));
        onView(withText("=")).perform(click());
        onView(withId(R.id.numberField)).perform(typeText("10"));
        DataModel expectedModel = new DataModel("5", "10", "=");
        String expectedResult = pr.getOperationResult(expectedModel);
        assertEquals(expectedResult, getText(withId(R.id.resultField)));
        assertEquals("", getText(withId(R.id.numberField)));
        assertEquals("=", getText(withId(R.id.operationField)));
    }

    @Test
    public void testOnGetDataClickSetsFieldsFromDataModel() {
        // supposed to be failed cause of bug
        DataModel expectedData = new DataModel("10", "20", "+");
        onView(withId(R.id.getButton)).perform(click());
        assertEquals(expectedData.getFirstData(), getText(withId(R.id.numberField))); //notequals
        assertEquals(expectedData.getSecondData(), getText(withId(R.id.resultField)));
        assertEquals(expectedData.getThirdData(), getText(withId(R.id.operationField)));
    }

    @Test
    public void testOnSaveDataClickSetsSuccessMessageWhenSaveSuccessful() {
        onView(withId(R.id.numberField)).perform(typeText("123"));
        onView(withText("+")).perform(click());
        onView(withId(R.id.numberField)).perform(typeText("456"));
        onView(withId(R.id.saveButton)).perform(click());
        assertEquals("Результат успешно сохранен", getText(withId(R.id.numberField)));
        assertEquals("", getText(withId(R.id.resultField)));
        assertEquals("", getText(withId(R.id.operationField)));
    }

    @Test
    public void testOnCleanButtonClick() {
        onView(withId(R.id.numberField)).perform(typeText("123"));
        onView(withText("+")).perform(click());
        onView(withId(R.id.numberField)).perform(typeText("456"));
        onView(withId(R.id.clearButton)).perform(click());
        assertEquals("", getText(withId(R.id.numberField)));
        assertEquals("", getText(withId(R.id.resultField)));
        assertEquals("", getText(withId(R.id.operationField)));
    }

    @Test
    public void testGenerateDataModelFromFields() {
        onView(withId(R.id.numberField)).perform(typeText("123"));
        onView(withText("+")).perform(click());
        onView(withId(R.id.numberField)).perform(typeText("456"));
        DataModel model = mainActivityActivityTestRule.getActivity().DataModelGenerate();
        assertEquals("456", model.getFirstData());
        assertEquals("123", model.getSecondData());
        assertEquals("+", model.getThirdData());
    }

    String getText(final Matcher<View> matcher) {
        final String[] stringHolder = { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }
            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView)view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }
        });
        return stringHolder[0];
    }
}