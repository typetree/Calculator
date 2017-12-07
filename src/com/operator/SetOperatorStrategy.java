package com.operator;

import javafx.stage.Screen;

import java.math.BigDecimal;
import java.util.Map;

import static com.constant.CommonConstant.CalculatorParam.*;
import static com.constant.CommonConstant.BUTTON.*;
import static com.constant.CommonConstant.ScreenParam.MAX_SIZE;

/**
 * Created by caifangyi on 2017/12/5.
 */
public class SetOperatorStrategy implements OperatorStrategy{


    @Override
    public Map<String, Object> execute(Map<String, Object> map, String inputText) {


        String symbol = (String) map.get(SYMBOL_VALUE);
        String screenText = (String) map.get(SCREEN_TEXT);
        Boolean numInputFlag = (Boolean) map.get(NUM_INPUT_FLAG);

        if (screenText.indexOf(BUTTON_POINT) < 0 && inputText.equals(BUTTON_POINT)) {
            //输入 .
            screenText = screenText + inputText;
            numInputFlag = true;

        } else if (inputText.equals(BUTTON_BACK)) {

            //输入 Back
            if(screenText.length()==1){
                screenText = "0";

            }else if (symbol == null || symbol == "") {

                if(screenText.indexOf(BUTTON_POINT)==screenText.length()-1){

                    screenText = screenText.substring(0, screenText.length() - 2);

                }else{

                    screenText = screenText.substring(0, screenText.length() - 1);
                }
            }

        }else {

            if (screenText.indexOf(BUTTON_POINT) == screenText.length() - 1) {
                screenText = screenText.substring(0, screenText.length() - 1);
            }
            BigDecimal screenBig = new BigDecimal(screenText);

            if (inputText.equals(BUTTON_POSITIVE_NEGATIVE)) {
                //输入 +/-
                screenBig = screenBig.negate();

            } else if (inputText.equals(BUTTON_RECIPROCAL)) {
                //输入 1/x
                if (!screenBig.equals(new BigDecimal(0))) {
                    screenBig = new BigDecimal(1).divide(screenBig,MAX_SIZE,BigDecimal.ROUND_HALF_UP);
                }

            } else if (inputText.equals(BUTTON_PERCENT)) {
                //输入 %
                screenBig = screenBig.divide(new BigDecimal(100));

            } else if(inputText.equals(BUTTON_SQRT)){

                screenBig = new BigDecimal(Math.sqrt(screenBig.doubleValue()));

                if(screenBig.toString().length() > MAX_SIZE){

                    screenBig = screenBig.setScale(MAX_SIZE, BigDecimal.ROUND_HALF_UP);
                }
            }


            screenText = screenBig.toString();
            numInputFlag = false;
        }

        map.put(SYMBOL_VALUE, symbol);
        map.put(SCREEN_TEXT, screenText);
        map.put(NUM_INPUT_FLAG, numInputFlag);

        return map;
    }
}
