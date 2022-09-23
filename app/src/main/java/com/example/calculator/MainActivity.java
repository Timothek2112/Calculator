package com.example.calculator;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView result;
    int linearCount = 0;
    ScrollView sv;
    LinearLayout ll;
    float leftNumF = 0, rightNumF = 0, finalResultF = 0;
    int  SVplace, finalResult = 0;
    String resultStr, operator = "";
    Boolean  _isHaveResult = false, _isFloated = false, _isOperatorSelected = false, _isFirstNumber = true, _isSomeFloated = false;
    List<String> history = new ArrayList<String>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        resultStr = "";
        sv = findViewById(R.id.scrollid);
        SVplace = 1;
        ll = findViewById(R.id.linear);
        for(int i =0;i<15;i++){
            history.add("");
        }

    }

    public String GetSign(View view){
        switch(view.getId()){
            case R.id.plus:
                    operator = "+";
                    return " + ";

            case R.id.minus:
                operator = "-";
                return " - ";

            case R.id.delenie:
                operator = "/";
                return " / ";

            case R.id.multiply:
                operator = "*";
                return " * ";



            default:
                return "";
        }

    }

    protected String GetButtonNumber(View v) {

        switch (v.getId()) {


            case R.id.btn0:
                return "0";

            case R.id.btn1:
                return "1";

            case R.id.btn2:
                return "2";

            case R.id.btn3:
                return "3";

            case R.id.btn4:
                return "4";

            case R.id.btn5:
                return "5";

            case R.id.btn6:
                return "6";

            case R.id.btn7:
                return "7";

            case R.id.btn8:
                return "8";

            case R.id.btn9:
                return "9";

            case R.id.btnFloat:
                if(result.getText()==""){
                    return "0.";
                }
                return ".";

            default:
                return "";

        }

    }


    public void up(View view) {
        SVplace--;
        sv.smoothScrollTo(0, SVplace * 93);
    }

    public void down(View view) {
        SVplace++;
        sv.smoothScrollTo(0, SVplace * 93);
    }

    public void UpdateHistory(){
        for(int i =0;i<15;i++){
            TextView tv = (TextView)ll.getChildAt(i);
            tv.setText(history.get(i));
        }
        linearCount++;
    }

    public void OnNumberClicked(View view) {
        if(_isOperatorSelected&&_isHaveResult&&GetSign(view)!=""){
            _isOperatorSelected = false;
            _isHaveResult = false;
            resultStr = String.valueOf(finalResultF);
        }else if(_isOperatorSelected&&_isHaveResult&&GetButtonNumber(view)!=""){
            reset(view);
        }

        if(!_isFirstNumber){

            if(GetButtonNumber(view)!="") {
                resultStr+=GetButtonNumber(view);
                result.setText(result.getText() + GetButtonNumber(view));
            }

            if(GetSign(view)!=""&&!_isOperatorSelected) {
                System.out.println("___ВЫБРАН ОПЕРАТОР___");
                leftNumF = Float.parseFloat(resultStr);
                resultStr = "";
                _isOperatorSelected = true;
                result.setText(result.getText() + GetSign(view));
            }

        }else{

            if(GetButtonNumber(view)!="") {
                _isFirstNumber =false;
                resultStr+=GetButtonNumber(view);
                result.setText("");
                result.setText(result.getText() + GetButtonNumber(view));
            }
            else {
                _isFirstNumber = true;
                result.setText("0");

            }

            if(GetSign(view)!=""&&!_isOperatorSelected) {
                resultStr = "0";
                _isFirstNumber =false;
                System.out.println("___ВЫБРАН ОПЕРАТОР___");
                leftNumF = Float.parseFloat(resultStr);
                resultStr = "";
                _isOperatorSelected = true;
                result.setText(result.getText() + GetSign(view));
            }

        }
        System.out.println("\n\n\n\r\r\r");
        System.out.println("Первая ли цифра?  "+_isFirstNumber);
        System.out.println("Выбран ли оператор?  " + _isOperatorSelected);
        System.out.println("Левое число: " + leftNumF);
        System.out.println("Правое число: " + rightNumF);
        System.out.println("\n\n\n\r\r\r");
    }








    public void equals(View view){
        _isHaveResult = true;
        finalResultF = 0;
        try {
            rightNumF = Float.parseFloat(resultStr);
        }catch(Exception e){
            System.out.println("НЕ НАБРАНА ВТОРАЯ ЦИФРА");
        }
        resultStr = "";
        switch (operator){
            case "+":
                finalResultF = leftNumF + rightNumF;
                history.set(linearCount,leftNumF + operator+rightNumF+"="+finalResultF);
                UpdateHistory();
                    try{
                        Integer.parseInt(String.valueOf(finalResultF));
                        finalResult = Math.round(finalResultF);
                        System.out.println(finalResult);
                        result.setText(String.valueOf(finalResult));
                        break;
                    }catch(Exception e){
                        System.out.println(finalResultF);
                        result.setText(String.valueOf(finalResultF));
                    }

                    break;

            case "-":
                finalResultF = leftNumF - rightNumF;
                history.set(linearCount,leftNumF + operator+rightNumF+"="+finalResultF);
                UpdateHistory();
                    try{
                        Integer.parseInt(String.valueOf(finalResultF));
                        finalResult = Math.round(finalResultF);
                        System.out.println(finalResult);
                        result.setText(String.valueOf(finalResult));
                        break;
                    } catch (Exception e){
                        System.out.println(finalResultF);
                        result.setText(String.valueOf(finalResultF));
                    }
                break;

            case "/":
                finalResultF = leftNumF / rightNumF;
                history.set(linearCount,leftNumF + operator+rightNumF+"="+finalResultF);
                UpdateHistory();
                try {
                    Integer.parseInt(String.valueOf(finalResultF));
                    finalResult = Math.round(finalResultF);
                    System.out.println(finalResult);
                    result.setText(String.valueOf(finalResult));
                    break;
                }catch(Exception e){
                    System.out.println(finalResultF);
                    result.setText(String.valueOf(finalResultF));
                }
                break;

            case "*":
                finalResultF = leftNumF * rightNumF;
                history.set(linearCount,leftNumF + operator+rightNumF+"="+finalResultF);
                UpdateHistory();
                try {
                    Integer.parseInt(String.valueOf(finalResultF));
                    finalResult = Math.round(finalResultF);
                    System.out.println(finalResult);
                    result.setText(String.valueOf(finalResult));
                    break;
                }catch(Exception e){
                    System.out.println(finalResultF);
                    result.setText(String.valueOf(finalResultF));
                }


                break;
        }



    }



    public void reset(View view){
        rightNumF = 0;
        leftNumF = 0;
        finalResult = 0;
        finalResultF = 0;
        _isSomeFloated = false;
        _isFloated = false;
        _isOperatorSelected = false;
        _isFirstNumber = true;
        _isHaveResult = false;
        result.setText("0");
        resultStr = "";
        operator = "";
    }
}