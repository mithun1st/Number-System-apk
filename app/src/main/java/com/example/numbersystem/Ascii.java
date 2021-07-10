package com.example.numbersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Ascii extends AppCompatActivity {

    private Spinner sp;
    private EditText input;
    private Button translate,clear;
    private TextView label1,label2,display;

    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascii);

        getSupportActionBar().setTitle("Ascii Translator");

        input=findViewById(R.id.asciiInput);
        label1=findViewById(R.id.asciiDisplay1);
        label2=findViewById(R.id.asciiDisplay2);
        display=findViewById(R.id.asciiOutput);
        translate=findViewById(R.id.asciiTranslate);
        clear=findViewById(R.id.asciiClear);
        sp=findViewById(R.id.asciiSp);


        //spinner
        String[] numSystem= {"Plain Text To Ascii Code","Ascii Code To Plain Text"};

        //adapter
        ArrayAdapter adapter = new ArrayAdapter(Ascii.this,R.layout.tem_ly,R.id.tem_tv,numSystem);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos=position;

                display.setText("");
                input.setText("");

                if(pos==0){
                    label1.setText("Plain Text:");
                    label2.setText("Ascii Code:");
                    translate.setText("Text To Ascii");
                }
                else {
                    label1.setText("Ascii Code:");
                    label2.setText("Plain Text:");
                    translate.setText("Ascii To Text");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //translate
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(input.getText().toString().isEmpty()){
                    Toast.makeText(Ascii.this,"Field Is Empty",Toast.LENGTH_SHORT).show();
                }
                else{

                    try{
                        String s;
                        switch (pos){
                            case 0:
                                s=textToAscii(input.getText().toString());
                                display.setText(s);
                                break;
                            case 1:
                                s=asciiToText(input.getText().toString());
                                display.setText(s);
                                break;
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(Ascii.this,e.toString(),Toast.LENGTH_SHORT).show();
                        input.setError("Invalid Input!");
                        input.requestFocus();
                    }
                }
            }
        });


        //clear
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("");
                input.setText("");
                Toast.makeText(Ascii.this,"Clear Display",Toast.LENGTH_SHORT).show();
            }
        });

    }


    //ascii to text
    public static String asciiToText(String str){

        StringBuilder sb=new StringBuilder();
        StringBuilder sb1=new StringBuilder();
        str+=" ";

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                sb1.append((char) Integer.parseInt(sb.toString()));
                sb.delete(0,sb.length());
                continue;
            }
            sb.append(str.charAt(i));
        }
        return sb1.toString();
    }

    //text to ascii
    public static String textToAscii(String str){
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<str.length();i++){
            if( String.valueOf((int) (str.charAt(i))).length()<3){
                sb.append(0);
                sb.append( (int) (str.charAt(i)));
                sb.append(' ');
            }
            else {
                sb.append( (int) (str.charAt(i)));
                sb.append(' ');
            }
        }
        return sb.toString().trim();
    }

}