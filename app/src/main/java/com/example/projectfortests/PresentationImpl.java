package com.example.projectfortests;

public class PresentationImpl {
    public String getOperationResult (DataModel model){
        String operation = model.thirdData;
        int number2 = Integer.valueOf(model.firstData);
        int number = Integer.valueOf(model.secondData);
        switch(operation){
            case "=":
                number =number2;
                break;
            case "/":
                number /=number2;
                break;
            case "*":
                number *=number2;
                break;
            case "+":
                number +=number2;
                break;
            case "-":
                number -=number2;
                break;
        }
        return String.valueOf(number);
    }
    public Boolean checkNumberValue (String number){
        if (number != null && !number.equals("") && !number.equals("3")){
            return true;
        }
        return false;
    }

    public void onCleanTextClick(DataModel model) {
    }
}
