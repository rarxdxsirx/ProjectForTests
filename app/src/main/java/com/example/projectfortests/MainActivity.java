package com.example.projectfortests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText numberField;
    TextView resultField;
    TextView operationField;
    PresentationImpl pr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberField = findViewById(R.id.numberField);
        resultField = findViewById(R.id.resultField);
        operationField = findViewById(R.id.operationField);
        pr = new PresentationImpl();
    }
    public void onNumberClick(View view){
        Button btn = (Button) view;
        if (pr.checkNumberValue((String) btn.getText()) == true){
            numberField.append(btn.getText());
        }
    }
    public void onOperationClick(View view){
        Button btn = (Button) view;
        operationField.setText(btn.getText());
        if (resultField.getText() == ""){
            resultField.setText(numberField.getText());
            numberField.setText("");
        }
    }
    public void onEqualsClick(View view){
        resultField.setText(pr.getOperationResult(DataModelGenerate()));
        numberField.setText("");
        operationField.setText("=");
    }
    public void onGetDataClick(View view){
        DataImpl data = new DataImpl(getApplicationContext());
        DataModel result = data.getData();
        numberField.setText(result.getFirstData());
        resultField.setText(result.getSecondData());
        operationField.setText(result.getThirdData());
    }
    public void onSaveDataClick(View view){
        DataImpl data = new DataImpl(getApplicationContext());
        Boolean result = data.saveData(DataModelGenerate());
        CleanText();
        if (result == true){
            numberField.setText("Результат успешно сохранен");
        }
        else {
            numberField.setText("Результат успешно сохранен");
        }
    }
    public void onCleanTextClick(View view){
        CleanText();
    }
    public DataModel DataModelGenerate(){
        return new DataModel(
                String.valueOf(numberField.getText()),
                String.valueOf(resultField.getText()),
                String.valueOf(operationField.getText())
        );
    }
    public void CleanText()
    {
        numberField.setText("");
        resultField.setText("");
        operationField.setText("");
    }
}