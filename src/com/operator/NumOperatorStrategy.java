package com.operator;

import java.util.Map;

import static com.constant.CommonConstant.CalculatorParam.*;
import static com.constant.CommonConstant.ScreenParam.MAX_SIZE;

/**
 * Created by caifangyi on 2017/12/5.
 */
public class NumOperatorStrategy implements OperatorStrategy{

    /**
     * 输入的是数字
     */
    @Override
    public Map<String, Object> execute(Map<String, Object> map, String inputText) {

        String screenText = (String) map.get(SCREEN_TEXT);

        Boolean numInputFlag = (Boolean) map.get(NUM_INPUT_FLAG);

        if(screenText.length()>=MAX_SIZE){
            return map;
        }

        if(!numInputFlag ) {

            screenText = inputText;

        }else if(screenText.indexOf("0")==0){

            screenText = inputText;

        }else{

            screenText = screenText + inputText;

        }
        numInputFlag = true;

        map.put(SCREEN_TEXT,screenText);
        map.put(NUM_INPUT_FLAG,numInputFlag);

        return map;
    }
}
