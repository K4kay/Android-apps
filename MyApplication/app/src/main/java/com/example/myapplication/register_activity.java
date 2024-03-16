package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register_activity extends AppCompatActivity {
    EditText inputname,inputdob,inputmobile,inputemail,inputpassword;
    MaterialButton register;
    ProgressBar progressbar;
    ProgressDialog progressdialogue;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        inputname=findViewById(R.id.name);
        inputdob=findViewById(R.id.dob);
        inputemail=findViewById(R.id.email);
        inputmobile=findViewById(R.id.mobile);
        register=findViewById(R.id.register);
        inputpassword=findViewById(R.id.password1);
        progressdialogue=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        register.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                perforAuth();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void perforAuth()
    {
        String name = inputemail.getText().toString();
        String email=inputemail.getText().toString();
        String mobile=inputmobile.getText().toString();
        String dob=inputdob.getText().toString();
        String password=inputpassword.getText().toString();
        if(email.isEmpty())
        {
            inputemail.setError("Enter correct Email");
        }
        else if(name.isEmpty())
    {
        inputname.setError("enter name");
    }
    else if(mobile.length()<10)
    {
        inputmobile.setError("enter valid mobile no.");
    }
    else if(dob.isEmpty())
    {
        inputdob.setError("Enter dob");
    }
    else if(password.isEmpty() || password.length()<6)
    {
        inputpassword.setError("enter valid password");
    }
    else{
        progressdialogue.setMessage("registration going on.....");
        progressdialogue.setTitle("Registration");
        progressdialogue.setCanceledOnTouchOutside(false);
        progressdialogue.show();
        Toast.makeText(register_activity.this, "please wait", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressdialogue.dismiss();
                    Toast.makeText(register_activity.this, "sucessful", Toast.LENGTH_SHORT).show();
                    senduser();

                }
                else
                {
                    progressdialogue.dismiss();
                    Toast.makeText(register_activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    }
    private void senduser(){
        Intent intent=new Intent(register_activity.this, profile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
