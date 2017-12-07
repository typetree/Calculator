package com.view;

import com.constant.CommonConstant;
import com.operator.OperatorStrategyFactory;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import static com.constant.CommonConstant.Menu.*;
import static com.constant.CommonConstant.Title.*;
import static com.constant.CommonConstant.BUTTON.*;
import static com.constant.CommonConstant.Regex.*;
import static com.constant.CommonConstant.CalculatorParam.*;


public class View extends JFrame implements ActionListener{
    
    private TextField screenField;
    
    private JButton numBtn[] = new JButton[10];
    
    private JButton plusBtn,minusBtn,multipleBtn,divsionBtn,equalBtn,dBtn,pnBtn,
            reciprocalBtn,percentBtn,sqrtBtn,backBtn,clearBtn;
    
    private String saveValue = "0";

    private String symbol;
    
    private Boolean numInputFlag = true;

    private OperatorStrategyFactory operatorStrategyFactory = new OperatorStrategyFactory();

    public View(){
        setTitle(TITLE);
        setDefaultLookAndFeelDecorated(false);
        setBounds(100,100,400,250);
        setLocationRelativeTo(null);
        setResizable(false);
        init();

        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu(MENU_FILE);
        JMenu helpMenu = new JMenu(MENU_HELP);

        menubar.add(fileMenu);
        menubar.add(helpMenu);

        JMenu fileSubMenuFile = new JMenu(MENU_FILE_SUB_FILE);
        JMenuItem fileSubMenuExit = new JMenuItem(MENU_FILE_SUB_EXIT);
        fileSubMenuExit.addActionListener(this);

        JMenuItem helpSubMenuOther = new JMenuItem(MENU_HELP_SUB_OTHER);
        helpSubMenuOther.addActionListener(this);

        fileMenu.add(fileSubMenuFile);
        fileMenu.add(fileSubMenuExit);
        helpMenu.add(helpSubMenuOther);
        setJMenuBar(menubar);

        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(screenField,BorderLayout.NORTH);
        jPanel.add(backAndClearBtnPanel(),BorderLayout.CENTER);
        jPanel.add(inputPanel(),BorderLayout.SOUTH);

        getContentPane().add(jPanel);

        setVisible(true);
    }

    private void init(){

        screenField = new TextField("0");
        screenField.setEnabled(false);

        for(int i = 0; i < numBtn.length; i++){
            String num = String.valueOf(i);
            numBtn[i] = new JButton(num);
            numBtn[i].addActionListener(this);
        }

        plusBtn = new JButton(BUTTON_PLUS);
        plusBtn.addActionListener(this);

        minusBtn = new JButton(BUTTON_MINUS);
        minusBtn.addActionListener(this);

        multipleBtn = new JButton(BUTTON_MULTIPLE);
        multipleBtn.addActionListener(this);

        divsionBtn = new JButton(BUTTON_DIVISION);
        divsionBtn.addActionListener(this);

        dBtn = new JButton(BUTTON_POINT);
        dBtn.addActionListener(this);

        equalBtn = new JButton(BUTTON_EQUAL);
        equalBtn.addActionListener(this);

        pnBtn = new JButton(BUTTON_POSITIVE_NEGATIVE);
        pnBtn.addActionListener(this);

        reciprocalBtn = new JButton(BUTTON_RECIPROCAL);
        reciprocalBtn.addActionListener(this);

        percentBtn = new JButton(BUTTON_PERCENT);
        percentBtn.addActionListener(this);

        sqrtBtn = new JButton(BUTTON_SQRT);
        sqrtBtn.addActionListener(this);

        backBtn = new JButton(BUTTON_BACK);
        backBtn.addActionListener(this);

        clearBtn = new JButton(BUTTON_CLAER);
        clearBtn.addActionListener(this);


    }

    private JPanel backAndClearBtnPanel(){
        JPanel jPanel = new JPanel(new GridLayout(1,2));
        jPanel.setPreferredSize(new Dimension(100,50));
        jPanel.add(backBtn);
        jPanel.add(clearBtn);

        return jPanel;
    }

    private JPanel inputPanel(){
        JPanel jPanel = new JPanel(new GridLayout(5,4,0,0));

        jPanel.add(numBtn[7]);
        jPanel.add(numBtn[8]);
        jPanel.add(numBtn[9]);
        jPanel.add(divsionBtn);

        jPanel.add(numBtn[4]);
        jPanel.add(numBtn[5]);
        jPanel.add(numBtn[6]);
        jPanel.add(multipleBtn);

        jPanel.add(numBtn[1]);
        jPanel.add(numBtn[2]);
        jPanel.add(numBtn[3]);
        jPanel.add(minusBtn);

        jPanel.add(numBtn[0]);
        jPanel.add(pnBtn);
        jPanel.add(dBtn);
        jPanel.add(plusBtn);


        jPanel.add(reciprocalBtn);
        jPanel.add(percentBtn);
        jPanel.add(sqrtBtn);
        jPanel.add(equalBtn);
        return jPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        String inputText = e.getActionCommand();

        String screenText = screenField.getText();


        List<String> regexList = getRegexList();

        Map<String, Boolean> regexMap = new HashMap<String,Boolean>();


        for(int i=0;i<regexList.size();i++){

            String regex = regexList.get(i);

            Pattern pattern = Pattern.compile(regex);

            Boolean flag = pattern.matcher(inputText).matches();

            if(flag){
                regexMap.put(regex,flag);
                break;
            }

        }

        if(regexMap.size()!=1){
            System.out.println("bug is existed in regex"+",inputText:"+inputText+",screenText:"+screenText);
            return;
        }

        Map<String, String> strategyMap = getRegexMap();

        Iterator<Map.Entry<String, Boolean>> iterator = regexMap.entrySet().iterator();

        while(iterator.hasNext()){

            Map.Entry<String, Boolean> next = iterator.next();

            String regex = next.getKey();
            Boolean flag = next.getValue();

            if(flag){

                Map<String, Object> map = new HashMap<>();
                map.put(SAVE_VALUE,saveValue);
                map.put(SYMBOL_VALUE,symbol);
                map.put(SCREEN_TEXT,screenText);
                map.put(NUM_INPUT_FLAG,numInputFlag);

                String strategyName = strategyMap.get(regex);
                Map<String,Object> returnMap = operatorStrategyFactory
                        .getInstance(strategyName)
                        .execute(map,inputText);

                saveValue = (String) returnMap.get(SAVE_VALUE);
                symbol = (String) returnMap.get(SYMBOL_VALUE);
                screenText = (String) returnMap.get(SCREEN_TEXT);
                numInputFlag = (Boolean) returnMap.get(NUM_INPUT_FLAG);

            }

        }
        screenField.setText(screenText);

        if(e.getActionCommand().equals(MENU_HELP_SUB_OTHER)){
            JOptionPane.showMessageDialog(this, "Help");
        }

        if(e.getActionCommand().equals(MENU_FILE_SUB_EXIT)){
            System.exit(0);
        }
    }


    public static void main(String[] args){
        new View();
    }
}
