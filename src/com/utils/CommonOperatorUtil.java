package com.utils;

import java.math.BigDecimal;

import static com.constant.CommonConstant.BUTTON.*;
import static com.constant.CommonConstant.ScreenParam.MAX_SIZE;

/**
 * Created by caifangyi on 2017/12/7.
 */
public class CommonOperatorUtil {

    public static BigDecimal runOperator(String symbol, BigDecimal saveValueBig, BigDecimal screenTextBig) {
        if(symbol.equals(BUTTON_PLUS)){
            // +
            screenTextBig = saveValueBig.add(screenTextBig);

        }else if(symbol.equals(BUTTON_MINUS)){

            screenTextBig = saveValueBig.subtract(screenTextBig);

        }else if(symbol.equals(BUTTON_MULTIPLE)){

            screenTextBig = saveValueBig.multiply(screenTextBig);

        }else if(symbol.equals(BUTTON_DIVISION)){

            screenTextBig = saveValueBig.divide(screenTextBig,MAX_SIZE,BigDecimal.ROUND_HALF_UP);
        }
        return screenTextBig;
    }
}
