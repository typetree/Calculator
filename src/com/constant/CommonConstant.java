package com.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caifangyi on 2017/12/5.
 */
public class CommonConstant {

    /**
     * title
     */
    public static class Title{

        public static final String TITLE = "Calculator";

    }

    /**
     * menu
     */
    public static class Menu{

        public static final String MENU_FILE = "File";

        public static final String MENU_HELP = "Help";

        public static final String MENU_FILE_SUB_FILE = "File";

        public static final String MENU_FILE_SUB_EXIT = "exit";

        public static final String MENU_HELP_SUB_OTHER = "Other";

    }

    public static class BUTTON{

        /**
         * RUN_OPERATOR
         */
        public static final String BUTTON_PLUS = "+";

        public static final String BUTTON_MINUS = "-";

        public static final String BUTTON_MULTIPLE = "×";

        public static final String BUTTON_DIVISION = "÷";


        /**
         * SET_OPERATOR
         */
        public static final String BUTTON_PERCENT = "%";

        public static final String BUTTON_POSITIVE_NEGATIVE = "+/-";

        public static final String BUTTON_RECIPROCAL = "1/x";

        public static final String BUTTON_BACK = "Back";

        public static final String BUTTON_POINT = ".";

        public static final String BUTTON_SQRT = "sqrt";
        /**
         * RESULT_OPERATOR
         */
        public static final String BUTTON_EQUAL = "=";

        public static final String BUTTON_CLAER = "C";



        public static final List<String> runOperatorList = new ArrayList<String>();

        public static final List<String> setOperatorList = new ArrayList<String>();

        public static final List<String> resultOperatorList = new ArrayList<String>();


        static {

            runOperatorList.add(BUTTON_PLUS);
            runOperatorList.add(BUTTON_MINUS);
            runOperatorList.add(BUTTON_MULTIPLE);
            runOperatorList.add(BUTTON_DIVISION);
            runOperatorList.add(BUTTON_SQRT);

            setOperatorList.add(BUTTON_PERCENT);
            setOperatorList.add(BUTTON_POSITIVE_NEGATIVE);
            setOperatorList.add(BUTTON_RECIPROCAL);
            setOperatorList.add(BUTTON_BACK);
            setOperatorList.add(BUTTON_POINT);

            resultOperatorList.add(BUTTON_EQUAL);
            resultOperatorList.add(BUTTON_CLAER);
        }

        public static final List<String> getRunOperatorList(){
            return runOperatorList;
        }

        public static final List<String> getSetOperatorList(){
            return setOperatorList;
        }

        public static final List<String> getResultOperatorList(){
            return resultOperatorList;
        }

    }


    public static class Regex{

        /**
         * regex
         */
        public static final String REGEX_NUM = "[0-9]";

        public static final String REGEX_RUN_OPERATOR0 = "\\+|-|×|÷";

        public static final String REGEX_RESULT_OPERATOR = "=|C";

        public static final String REGEX_SET_OPERATOR = "^(.|%|\\+/-|1/x|(sqrt)|(Back))$";

        public static final List<String> regexList = new ArrayList<String>();

        public static final Map<String,String> regexMap = new HashMap<String,String>();

        static {
            regexList.add(REGEX_NUM);
            regexList.add(REGEX_RUN_OPERATOR0);
            regexList.add(REGEX_RESULT_OPERATOR);
            regexList.add(REGEX_SET_OPERATOR);

            regexMap.put(REGEX_NUM,StrategyName.NUM_OPERATOR);
            regexMap.put(REGEX_RUN_OPERATOR0,StrategyName.RUN_OPERATOR);
            regexMap.put(REGEX_RESULT_OPERATOR,StrategyName.RESULT_OPERATOR);
            regexMap.put(REGEX_SET_OPERATOR,StrategyName.SET_OPERATOR);
        }

        public static List<String> getRegexList(){
            return regexList;
        }

        public static Map<String,String> getRegexMap(){
            return regexMap;
        }
    }

    public static class StrategyName{

        public static final String NUM_OPERATOR = "NUM_OPERATOR";

        public static final String RUN_OPERATOR = "RUN_OPERATOR";

        public static final String SET_OPERATOR = "SET_OPERATOR";

        public static final String RESULT_OPERATOR = "RESULT_OPERATOR";

    }

    public static class CalculatorParam{

        public static final String SAVE_VALUE = "SAVE_VALUE";

        public static final String SYMBOL_VALUE = "SYMBOL_VALUE";

        public static final String SCREEN_TEXT = "SCREEN_TEXT";

        public static final String NUM_INPUT_FLAG = "NUM_INPUT_FLAG";
    }

    public static class ScreenParam{

        public static final Integer MAX_SIZE = 15;
    }

}
