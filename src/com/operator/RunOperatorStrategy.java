package com.operator;

import java.util.Map;
import static com.constant.CommonConstant.CalculatorParam.*;

public class RunOperatorStrategy implements OperatorStrategy{


    @Override
    public Map<String, Object> execute(Map<String, Object> map, String inputText) {

        String saveValue = (String) map.get(SAVE_VALUE);
        String symbol = (String) map.get(SYMBOL_VALUE);
        String screenText = (String) map.get(SCREEN_TEXT);
        Boolean numInputFlag = (Boolean) map.get(NUM_INPUT_FLAG);
        
        switch (inputText) {
            case "+/-":
                if(screenText.indexOf(".") < 0){
                    screenText = String.valueOf(Integer.valueOf(screenText) * (-1));
                }else{
                    screenText = String.valueOf(Float.valueOf(screenText) * (-1));
                }
                break;
            case "+":
                if(screenText.indexOf(".") < 0 && saveValue.indexOf(".") < 0){
                    saveValue = String.valueOf(Integer.valueOf(saveValue) + Integer.valueOf(screenText));
                    screenText = saveValue;
                }else{
                    saveValue = String.valueOf(Float.valueOf(saveValue) + Float.valueOf(screenText));
                    screenText = saveValue;
                }
                symbol = "+";
                numInputFlag = false;
                break;
            case "-":
                if(screenText.indexOf(".") < 0 && saveValue.indexOf(".") < 0){
                    saveValue = String.valueOf(Integer.valueOf(saveValue) - Integer.valueOf(screenText));
                    screenText = saveValue;
                }else{
                    saveValue = String.valueOf(Float.valueOf(saveValue) - Float.valueOf(screenText));
                    screenText = saveValue;
                }
                symbol = "-";
                numInputFlag = false;
                break;
            case "×":
                if(screenText.indexOf(".") < 0 && saveValue.indexOf(".") < 0){
                    saveValue = String.valueOf(Integer.valueOf(saveValue) - Integer.valueOf(screenText));
                    screenText = saveValue;
                }else{
                    saveValue = String.valueOf(Float.valueOf(saveValue) - Float.valueOf(screenText));
                    screenText = saveValue;
                }
                symbol = "*";
                numInputFlag = false;
                break;
            case "÷":
                if(screenText.equals("0")){
                    break;
                }else if(screenText.indexOf(".") < 0 && saveValue.indexOf(".") < 0
                        && Integer.valueOf(saveValue) % Integer.valueOf(screenText)!= 0 ){
                    saveValue = String.valueOf(Integer.valueOf(saveValue) / Integer.valueOf(screenText));
                    screenText = saveValue;
                }else{
                    saveValue = String.valueOf(Float.valueOf(saveValue) / Float.valueOf(screenText));
                    screenText = saveValue;
                }
                symbol = "/";
                numInputFlag = false;
                break;
            case "=":
//                getResult(saveValue,symbol);
                numInputFlag = false;
                break;
            case "C":
                screenText = "0";
                saveValue = "0";
                symbol = "";
                break;
            default:
                break;
        }
        
        return null;
    }

//    private String getResult(String s1, String s2){
//        String rStr = "";
//        if(s2.equals("") || s2 == null){
//            return null;
//        }
//        switch(s2){
//            case "+":
//                if(screenField.getText().indexOf(".") < 0 && sv1.indexOf(".") < 0){
//                    sv1 = String.valueOf(Integer.valueOf(sv1) + Integer.valueOf(screenField.getText()));
//                    screenField.setText(sv1);
//                }else{
//                    sv1 = String.valueOf(Float.valueOf(sv1) + Float.valueOf(screenField.getText()));
//                    screenField.setText(sv1);
//                }
//                sv1 = "0";
//                break;
//            case "-":
//                if(screenField.getText().indexOf(".") < 0 && sv1.indexOf(".") < 0){
//                    sv1 = String.valueOf(Integer.valueOf(sv1) - Integer.valueOf(screenField.getText()));
//                    screenField.setText(sv1);
//                }else{
//                    sv1 = String.valueOf(Float.valueOf(sv1) - Float.valueOf(screenField.getText()));
//                    screenField.setText(sv1);
//                }
//                sv1 = "0";
//                break;
//            case "*":
//                if(screenField.getText().indexOf(".") < 0 && sv1.indexOf(".") < 0){
//                    sv1 = String.valueOf(Integer.valueOf(sv1) * Integer.valueOf(screenField.getText()));
//                    screenField.setText(sv1);
//                }else{
//                    sv1 = String.valueOf(Float.valueOf(sv1) * Float.valueOf(screenField.getText()));
//                    screenField.setText(sv1);
//                }
//                sv1 = "0";
//                break;
//            case "/":
//                if(screenField.getText().equals("0")){
//                    break;
//                }else if(screenField.getText().indexOf(".") < 0 && sv1.indexOf(".") < 0
//                        && Integer.valueOf(sv1) % Integer.valueOf(screenField.getText())!= 0 ){
//                    sv1 = String.valueOf(Integer.valueOf(sv1) / Integer.valueOf(screenField.getText()));
//                    screenField.setText(sv1);
//                }else{
//                    sv1 = String.valueOf(Float.valueOf(sv1) / Float.valueOf(screenField.getText()));
//                    screenField.setText(sv1);
//                }
//                sv1 = "0";
//                break;
//        }
//
//        return rStr;
//    }

}
