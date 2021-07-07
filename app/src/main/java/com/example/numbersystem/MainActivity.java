package com.example.numbersystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner sp;
    private Button convert,clear;
    private EditText input;
    private TextView dec,bin,oct,hex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //support get action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.home_icon);


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


        //spinner database
        String[] numSystem=getResources().getStringArray(R.array.numberSystem);

        //adapter
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,R.layout.tem_ly,R.id.tem_tv,numSystem);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=numSystem[position];
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        //convert
        if(v.getId()==R.id.convert){
            String s=sp.getSelectedItem().toString();
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
        }


        //clear
        if(v.getId()==R.id.clear){
            sp.setSelection(0);
            clearDisplay();
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
        if(item.getItemId()==R.id.menuShare){
            Toast.makeText(MainActivity.this,"workding",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    //clear display
    public void clearDisplay(){
        dec.setText("- - - - -");
        bin.setText("- - - - -");
        oct.setText("- - - - -");
        hex.setText("- - - - -");
    }
}