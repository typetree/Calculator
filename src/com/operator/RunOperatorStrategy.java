package com.operator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.constant.CommonConstant.BUTTON.*;
import static com.constant.CommonConstant.CalculatorParam.*;
import static com.constant.CommonConstant.ScreenParam.MAX_SIZE;
import static com.utils.CommonOperatorUtil.runOperator;

public class RunOperatorStrategy implements OperatorStrategy{


    @Override
    public Map<String, Object> execute(Map<String, Object> map, String inputText) {

        String saveValue = (String) map.get(SAVE_VALUE);
        String symbol = (String) map.get(SYMBOL_VALUE);
        String screenText = (String) map.get(SCREEN_TEXT);
        Boolean numInputFlag = (Boolean) map.get(NUM_INPUT_FLAG);

        List<String> runOperatorList = getRunOperatorList();
        if(runOperatorList.contains(symbol)){

            BigDecimal saveValueBig = new BigDecimal(saveValue);
            BigDecimal screenTextBig = new BigDecimal(screenText);

            screenTextBig = runOperator(symbol, saveValueBig, screenTextBig);

            if(screenTextBig.toString().length() > MAX_SIZE){

                screenTextBig = screenTextBig.setScale(MAX_SIZE, BigDecimal.ROUND_HALF_UP);
            }

            saveValue = screenTextBig.toString();
            screenText = screenTextBig.toString();

            numInputFlag = true;

        }else{
            saveValue = screenText;
            numInputFlag = false;
        }

        symbol = inputText;

        map.put(SAVE_VALUE,saveValue);
        map.put(SYMBOL_VALUE,symbol);
        map.put(SCREEN_TEXT,screenText);
        map.put(NUM_INPUT_FLAG,numInputFlag);

        return map;
    }



}
