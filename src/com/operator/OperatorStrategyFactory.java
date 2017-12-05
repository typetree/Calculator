package com.operator;

import com.constant.CommonConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caifangyi on 2017/12/5.
 */
public class OperatorStrategyFactory {

    private NumOperatorStrategy numOperatorStrategy = new NumOperatorStrategy();

    private RunOperatorStrategy runOperatorStrategy = new RunOperatorStrategy();

    private SetOperatorStrategy setOperatorStrategy = new SetOperatorStrategy();

    private ResultOperatorStrategy resultOperatorStrategy = new ResultOperatorStrategy();

    private Map<String,OperatorStrategy> serviceMap = new HashMap<String,OperatorStrategy>();

    public OperatorStrategy getInstance(String serviceName){
        return serviceMap.get(serviceName);
    }

    public OperatorStrategyFactory(){

        serviceMap.put(CommonConstant.StrategyName.NUM_OPERATOR,numOperatorStrategy);

        serviceMap.put(CommonConstant.StrategyName.RUN_OPERATOR,runOperatorStrategy);

        serviceMap.put(CommonConstant.StrategyName.SET_OPERATOR,setOperatorStrategy);

        serviceMap.put(CommonConstant.StrategyName.RESULT_OPERATOR,resultOperatorStrategy);
    }

}
