package com.example.numbersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] numSystem=getResources().getStringArray(R.array.numberSystem);
        System.out.println(numSystem);

        sp=findViewById(R.id.sp);

        //adapter
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,R.layout.tem_ly,R.id.tem_tv,numSystem);
        sp.setAdapter(adapter);

    }
}