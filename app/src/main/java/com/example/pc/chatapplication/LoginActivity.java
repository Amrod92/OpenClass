package com.example.pc.chatapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;

    public static final String PREFS_NAME = "MyPrefsFile";      //Local Variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //                          WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        final Button temp_btn = (Button) findViewById(R.id.temp_btn);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = verify();
                if (response.contains("LoginSuccessful")) {
                    //Storing userID as local variable
                    String[] temp = response.split(" ");
                    setUser(temp[1]);
                    Intent loginIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                    startActivity(loginIntent);
                }
            }
        });
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }

        });
        temp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent demoQR = new Intent(LoginActivity.this, JoinSessionActivity.class);
                startActivity(demoQR);
            }
        });
    }

    private String verify() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String method = "login", response;
        BackgroundLoginTask bgTask = new BackgroundLoginTask(this);
        try {
            response = bgTask.execute(method, username, password).get();
        } catch (ExecutionException | InterruptedException e) {
            return "failed with " + e.getLocalizedMessage();
        }
        return response;
    }

    private void setUser(String userID) {
        SharedPreferences user = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = user.edit();
        editor.putString("userID", userID);
        editor.apply();
    }
}
