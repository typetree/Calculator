package com.operator;

import java.util.Map;

/**
 * Created by caifangyi on 2017/12/5.
 */
public interface OperatorStrategy {

    Map<String, Object> execute(Map<String, Object> map, String inputText);
}
