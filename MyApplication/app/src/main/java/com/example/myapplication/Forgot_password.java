package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Forgot_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();
        EditText profilename=findViewById(R.id.pned);
        EditText phonenum=findViewById(R.id.emed);
        MaterialButton more = findViewById(R.id.moremb);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vie2) {
                if (profilename.getText().toString().length() != 0 || phonenum.getText().toString().length() != 0) {
                    Intent intent4 = new Intent(Forgot_password.this, try_other_ways.class);
                    startActivity(intent4);
                }
                else{
                    Toast.makeText(Forgot_password.this, "Invalid!!!", Toast.LENGTH_SHORT).show();
                }
            }
            });

        }
    }
