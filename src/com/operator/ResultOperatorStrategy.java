package com.operator;

import com.constant.CommonConstant;

import java.math.BigDecimal;
import java.util.Map;

import static com.constant.CommonConstant.BUTTON.*;
import static com.constant.CommonConstant.CalculatorParam.*;
import static com.constant.CommonConstant.ScreenParam.MAX_SIZE;
import static com.utils.CommonOperatorUtil.runOperator;

/**
 * Created by caifangyi on 2017/12/5.
 */
public class ResultOperatorStrategy implements OperatorStrategy {


    @Override
    public Map<String, Object> execute(Map<String, Object> map, String inputText) {


        String saveValue = (String) map.get(SAVE_VALUE);
        String symbol = (String) map.get(SYMBOL_VALUE);
        String screenText = (String) map.get(SCREEN_TEXT);
        Boolean numInputFlag = (Boolean) map.get(NUM_INPUT_FLAG);

        BigDecimal saveValueBig = new BigDecimal(saveValue);
        BigDecimal screenTextBig = new BigDecimal(screenText);


        if(inputText.equals(BUTTON_CLAER)){

            saveValueBig = new BigDecimal(0);
            screenTextBig = new BigDecimal(0);

        }else if(inputText.equals(BUTTON_EQUAL)){

            screenTextBig = runOperator(symbol, saveValueBig, screenTextBig);

            if(screenTextBig.toString().length() > MAX_SIZE){

                screenTextBig = screenTextBig.setScale(MAX_SIZE, BigDecimal.ROUND_HALF_UP);
            }

            saveValueBig = screenTextBig;

        }
        symbol = "";
        numInputFlag = false;

        map.put(SAVE_VALUE,saveValueBig.toString());
        map.put(SYMBOL_VALUE,symbol);
        map.put(SCREEN_TEXT,screenTextBig.toString());
        map.put(NUM_INPUT_FLAG,numInputFlag);

        return map;
    }


}
