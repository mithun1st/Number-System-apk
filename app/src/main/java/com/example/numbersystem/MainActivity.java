package com.example.numbersystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner sp;
    private Button convert,clear;
    private EditText input;
    private TextView dec,bin,oct,hex;

    public String system;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //support get action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.action_bar_icon);


        //action bar color
        int actionBarColor=getResources().getColor(R.color.actioBar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(actionBarColor));


        //find view by id
        sp=findViewById(R.id.sp);
        convert=findViewById(R.id.convert);
        clear=findViewById(R.id.clear);
        input=findViewById(R.id.et);
        dec=findViewById(R.id.decimalDisplay);
        bin=findViewById(R.id.binDisplay);
        oct=findViewById(R.id.octalDisplay);
        hex=findViewById(R.id.hexaDisplay);

        //button click listener
        convert.setOnClickListener(this);
        clear.setOnClickListener(this);

        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sp.setSelection(0);
                input.setText("");
                clearDisplay();
                Toast.makeText(MainActivity.this,"Reset All",Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        //spinner database
        String[] numSystem=getResources().getStringArray(R.array.numberSystem);

        //adapter
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,R.layout.tem_ly,R.id.tem_tv,numSystem);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //input section enable/disable
                if(sp.getSelectedItemPosition()==0){
                    input.setEnabled(false);
                }
                else {
                    input.setEnabled(true);
                }

                //set input type
                system=numSystem[position];
                if(!system.matches("Hexadecimal")){
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                else {
                    input.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                }

                //clear field and display
                input.setText("");
                clearDisplay();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        //convert button
        if(v.getId()==R.id.convert){
            if(sp.getSelectedItemPosition()==0){
                Toast.makeText(MainActivity.this,"Select The Item First",Toast.LENGTH_SHORT).show();
            }
            else {
                calculate();
            }
        }

        //clear button
        if(v.getId()==R.id.clear){
            input.setText("");
            clearDisplay();
            Toast.makeText(MainActivity.this,"Clear Display",Toast.LENGTH_SHORT).show();
        }
    }


    //Menu Inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.option_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuAscii){
            Intent i=new Intent(MainActivity.this, Ascii.class);
            startActivity(i);
        }

        if(item.getItemId()==R.id.menuAbout){
            dialog();
        }
        if(item.getItemId()==R.id.menuShare){
            share();
        }

        if(item.getItemId()==R.id.menufeedBack){
            Intent i = new Intent(MainActivity.this, Feedback.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


//----------------------------------------------------------------------------------FUNCTION

    //clear display function
    public void clearDisplay(){
        dec.setText("- - - - -");
        bin.setText("- - - - -");
        oct.setText("- - - - -");
        hex.setText("- - - - -");
    }


    //show display function
    public void showDisplay(String a,String b, String c, String d){
        dec.setText(a);
        bin.setText(b);
        oct.setText(c);
        hex.setText(d);
    }

    //convert
    public void calculate(){
        String sdec=null, sbin=null, soct=null,shex=null;
        try{
            switch (system) {
                case "Decimal":
                    sdec=input.getText().toString();
                    sbin=Integer.toBinaryString(Integer.parseInt(input.getText().toString()));
                    soct=Integer.toOctalString(Integer.parseInt(input.getText().toString()));
                    shex=Integer.toHexString(Integer.parseInt(input.getText().toString())).toUpperCase();
                    break;

                case "Binary":
                    sdec=String.valueOf(Integer.parseInt(input.getText().toString(),2));
                    sbin=input.getText().toString();
                    soct=Integer.toOctalString(Integer.parseInt(input.getText().toString(), 2));
                    shex=Integer.toHexString(Integer.parseInt(input.getText().toString(), 2)).toUpperCase();
                    break;

                case "Octal":
                    sdec=String.valueOf(Integer.parseInt(input.getText().toString(),8));
                    sbin=Integer.toBinaryString(Integer.parseInt(input.getText().toString(), 8));
                    soct=input.getText().toString();
                    shex=Integer.toHexString(Integer.parseInt(input.getText().toString(), 8)).toUpperCase();
                    break;

                case "Hexadecimal":
                    sdec=String.valueOf(Integer.parseInt(input.getText().toString(),16));
                    sbin=Integer.toBinaryString(Integer.parseInt(input.getText().toString(), 16));
                    soct=Integer.toOctalString(Integer.parseInt(input.getText().toString(), 16));
                    shex=input.getText().toString().toUpperCase();
                    break;
            }
            input.setEnabled(false);
            input.setEnabled(true);
            input.setText(input.getText().toString().toUpperCase());
            showDisplay(sdec,sbin,soct,shex);
        }
        catch (Exception e){
            input.setError("Invalid "+system+" Number !");
            Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }


    //share
    public void share(){
        Intent is=new Intent(Intent.ACTION_SEND);
        is.setType("text/plain");

        is.putExtra(Intent.EXTRA_SUBJECT,"Number System Share");
        is.putExtra(Intent.EXTRA_TEXT, "*easy to use.\n*fully free.\ngood ui\n*less bug.");

        startActivity(Intent.createChooser(is,"Share Application Via"));
    }


    //dialog
    public void dialog(){
        AlertDialog ad;
        AlertDialog.Builder ab=new AlertDialog.Builder(this);

        ab.setIcon(R.drawable.ic_baseline_person_24);
        ab.setTitle("Build & Develop by");
        //ab.setMessage("");
        ab.setCancelable(false);

        //
        LayoutInflater li=getLayoutInflater();
        View v= (View) li.inflate(R.layout.about,findViewById(R.id.cv));
        ab.setView(v);
        //

        ab.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
//
//
//        ab.setNeutralButton("Nu+", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });


        ab.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ad=ab.create();
        ad.show();
    }
}