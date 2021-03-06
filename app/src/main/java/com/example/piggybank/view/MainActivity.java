package com.example.piggybank.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.piggybank.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

/**
 * this project is for managing money and reminding tasks which shows transactions base on
 * month and day and also notify tasks which is defined before.
 * some part is written by mvvm and rxandroid
 *
 *
 * @author sahar
 *
 */
public class MainActivity extends AppCompatActivity {
    TextInputEditText username;
    TextInputEditText password;
    MaterialButton login;
    Intent intent;
    boolean b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        login.setOnClickListener(view -> {
            login.setEnabled(true);
            String uName = username.getText().toString().trim();
            String uPass = password.getText().toString().trim();

            if (checkNotEmpty(uName, uPass)) {
                intent = new Intent(MainActivity.this, BaseActivity.class);
                startActivity(intent);
            }
        });
    }
    private void findView() {
        login = findViewById(R.id.btn_login);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
    }
    private boolean checkNotEmpty(String uName, String uPass) {
        if (uName.isEmpty() || uPass.isEmpty()) {
            login.setEnabled(false);
            if (uName.isEmpty())
                username.setError("ایمیل خود را وارد کنید");
            if (uPass.isEmpty())
                password.setError(" رمز عبور خود را وارد کنید");
            return false;
        } else {
            return true;
        }
    }

}