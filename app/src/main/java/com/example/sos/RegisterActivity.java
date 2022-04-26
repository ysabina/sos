package com.example.sos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    EditText eUserName,eEmail,ePassword,eNumber;
    Button btnOK;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        eUserName=(EditText) findViewById(R.id.editTextTextPersonName2);
        eEmail=(EditText) findViewById(R.id.editTextTextEmailAddress);
        ePassword=(EditText) findViewById(R.id.editTextTextPassword2);
        btnOK=(Button) findViewById(R.id.button3);
        eNumber=(EditText) findViewById(R.id.editTextNumber);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });
    }
    private void RegisterUser(){
        String uname=eUserName.getText().toString().trim();
        String uemail=eEmail.getText().toString().trim();
        String upassword=ePassword.getText().toString().trim();
        String unumber=eNumber.getText().toString().trim();
        if(uname.isEmpty() || uemail.isEmpty()|| upassword.isEmpty() || unumber.isEmpty() ){
            eUserName.setError("Введите всю информацию!");
            eUserName.requestFocus();
            return;
        }
        else {
            mAuth.createUserWithEmailAndPassword(uemail,upassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }

    }
}