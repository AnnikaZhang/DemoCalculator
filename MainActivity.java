package com.example.annika.calculator;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.util.Log;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    private EditText etInput;
    private Button bt0;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;
    private Button bt6;
    private Button bt7;
    private Button bt8;
    private Button bt9;
    private Button bt_add;
    private Button bt_sbt;
    private Button bt_mul;
    private Button bt_div;
    private Button bt_cl;
    private Button bt_eq;
    boolean clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connect buttons with button id in UI
        etInput=(EditText) findViewById(R.id.etInput);
        bt0=(Button) findViewById(R.id.button0);
        bt1=(Button) findViewById(R.id.button1);
        bt2=(Button) findViewById(R.id.button2);
        bt3=(Button) findViewById(R.id.button3);
        bt4=(Button) findViewById(R.id.button4);
        bt5=(Button) findViewById(R.id.button5);
        bt6=(Button) findViewById(R.id.button6);
        bt7=(Button) findViewById(R.id.button7);
        bt8=(Button) findViewById(R.id.button8);
        bt9=(Button) findViewById(R.id.button9);
        bt_add=(Button) findViewById(R.id.button_add);
        bt_sbt=(Button) findViewById(R.id.button_subtract);
        bt_mul=(Button) findViewById(R.id.button_multiply);
        bt_div=(Button) findViewById(R.id.button_divide);
        bt_cl=(Button) findViewById(R.id.button_clear);
        bt_eq=(Button) findViewById(R.id.button_eq);
        //set on click events to the buttons
        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_sbt.setOnClickListener(this);
        bt_mul.setOnClickListener(this);
        bt_div.setOnClickListener(this);
        bt_cl.setOnClickListener(this);
        bt_eq.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        String etinput=etInput.getText().toString();
        switch(v.getId()){
            case R.id.button0:
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
                if (clear){
                    clear=false;
                    etinput="";
                    etInput.setText("");
                }
                etInput.setText(etinput+((Button)v).getText());
                break;
            case R.id.button_add:
            case R.id.button_subtract:
            case R.id.button_multiply:
            case R.id.button_divide:

                if (clear){
                    clear=false;
                    etinput="";
                    etInput.setText("");
                }
                etInput.setText(etinput+" "+((Button)v).getText()+" ");
                break;
            case R.id.button_clear:
                clear=false;
                etinput="";
                etInput.setText("");
                break;
            case R.id.button_eq:
                getResult();
                break;

        }
    }

    public void getResult(){
        String exp=etInput.getText().toString();
        if (exp==null ||exp.equals("")){
            return;
        }
        if (!exp.contains(" ")){
            //No operator selected;
            return;
        }
        if(clear){
            clear=false;
            return;
        }
        clear=true;
        double res=0;
        int ind=exp.indexOf(" ");
        String s1=exp.substring(0,ind); //the string representing the first number
        String op=exp.substring(ind+1,ind+2); //the string representing the operator
        String s2=exp.substring(ind+3); //the string representing the second number
       if (!s1.equals("") && !s2.equals("")) {
           double d1 = Double.parseDouble(s1);
           double d2 = Double.parseDouble(s2);
           if (op.equals("+")) {
               res = d1 + d2;
           } else if (op.equals("-")) {
               res = d1 - d2;
           } else if (op.equals("*")) {
               res = d1 * d2;
           } else if (op.equals("/")) {
               if (d2 == 0) {
                   res = 0;
               } else {
                   res = d1 / d2;
               }
           }
           etInput.setText(res+"");
       } else if (!s1.equals("")&&s2.equals("")){
           //missing the second number, do not calculate
           etInput.setText(exp);
       } else if (s1.equals("")&&!s2.equals("")){
           double d2=Double.parseDouble(s2);
           //d1=0 by default
           if(op.equals("+")){
               res=d2;
           } else if (op.equals("-")){
               res=-d2;
           } else if (op.equals("*")||op.equals("/")){
               res=0;
           }
           etInput.setText(res+"");
       } else{
           etInput.setText("");
       }



    }
}
