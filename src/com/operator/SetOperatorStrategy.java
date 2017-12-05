package com.operator;

import java.util.Map;

import static com.constant.CommonConstant.CalculatorParam.*;
import static com.constant.CommonConstant.BUTTON.*;

/**
 * Created by caifangyi on 2017/12/5.
 */
public class SetOperatorStrategy implements OperatorStrategy{


    @Override
    public Map<String, Object> execute(Map<String, Object> map, String inputText) {


        String symbol = (String) map.get(SYMBOL_VALUE);
        String screenText = (String) map.get(SCREEN_TEXT);
        Boolean numInputFlag = (Boolean) map.get(NUM_INPUT_FLAG);


        if(screenText.indexOf(BUTTON_POINT) < 0 && inputText.equals(BUTTON_POINT)){
            //输入 .
            screenText = screenText + inputText;

        }else if(inputText.equals(BUTTON_POSITIVE_NEGATIVE)){

            //输入 +/-
            if(screenText.indexOf(BUTTON_POINT) < 0){
                screenText = String.valueOf(Integer.valueOf(screenText) * (-1));
            }else{
                screenText = String.valueOf(Float.valueOf(screenText) * (-1));
            }

        }else if(inputText.equals(BUTTON_RECIPROCAL)){
            //输入 1/x


        }else if(inputText.equals(BUTTON_BACK)){
            //输入 Back
            if(symbol !=null && symbol != ""){
                screenText = screenText.substring(0, screenText.length() - 2);
            }

        }else if(inputText.equals(BUTTON_PERCENT)){
            //输入 %
            if(screenText.indexOf(BUTTON_POINT) < 0){
                screenText = screenText + ".";
            }
            int index = screenText.indexOf(".");

            String leftPart = screenText.substring(0, index);
            String rightPart = screenText.substring(index+1, screenText.length() - 1);

            if(index < 3) {

                //如果不足三位数，用0补全三位
                String temp = String.format("%3s", leftPart);
                leftPart = temp.replaceAll("\\s","0");

                screenText = leftPart + rightPart;

            }else{
                screenText = leftPart + rightPart;
            }

            screenText = screenText.substring(0, 2)
                    + BUTTON_POINT
                    + screenText.substring(2, screenText.length() - 1);
        }

        map.put(SYMBOL_VALUE,symbol);
        map.put(SCREEN_TEXT,screenText);
        map.put(NUM_INPUT_FLAG,numInputFlag);

        return map;
    }

}
