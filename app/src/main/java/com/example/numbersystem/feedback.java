package com.example.numbersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class feedback extends AppCompatActivity {

    private Button b;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        b=findViewById(R.id.b);
        et=findViewById(R.id.et);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ifb=new Intent(Intent.ACTION_SEND);
                ifb.setType("text/email");

                ifb.putExtra(Intent.EXTRA_SUBJECT,"Number System Feedback");
                ifb.putExtra(Intent.EXTRA_TEXT,et.getText().toString());

                ifb.putExtra(Intent.EXTRA_EMAIL,new String[]{"mithun.2121@yahoo.com","m2n1st@gmail.com"});

                startActivity(Intent.createChooser(ifb,"Feedback . . ."));
            }
        });

    }
}