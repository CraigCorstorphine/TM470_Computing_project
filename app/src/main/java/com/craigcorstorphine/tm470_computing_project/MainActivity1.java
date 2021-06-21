package com.craigcorstorphine.tm470_computing_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity {
    DatabaseHelperLogin mDatabaseHelperLogin;

    EditText editTextUserNaem, editTextPassword, EditTextPassword2;
    Button buttonReg, buttonLog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        mDatabaseHelperLogin = new DatabaseHelperLogin(this);
        editTextUserNaem = findViewById(R.id.et_username);
        editTextPassword = findViewById(R.id.et_password);
        EditTextPassword2 = findViewById(R.id.et_cpassword);
        buttonReg = findViewById(R.id.btn_register);
        buttonLog = findViewById(R.id.btn_login);








        buttonLog.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity1.this, Login.class);
            startActivity(intent);
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUserNaem.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirm_password = EditTextPassword2.getText().toString();

                if(username.equals("") || password.equals("") || confirm_password.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.equals(confirm_password)){
                        Boolean checkusername = mDatabaseHelperLogin.CheckUsername(username);
                        if(checkusername){
                            Boolean insert = mDatabaseHelperLogin.Insert(username, password);
                            if(insert){
                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                editTextUserNaem.setText("");
                                editTextPassword.setText("");
                                EditTextPassword2.setText("");
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
