package com.example.pc.chatapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText email_reg, user_reg, pass_reg;
    private String email, username, password;
    Button reg_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email_reg = (EditText) findViewById(R.id.email_register);
        user_reg = (EditText) findViewById(R.id.user_register);
        pass_reg = (EditText) findViewById(R.id.pass_register);
        reg_button = (Button) findViewById(R.id.create_account_register);
        final TextView already_account = (TextView) findViewById(R.id.already_an_account);
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call when the button is clicked to validate the input
                register();
            }
        });
        already_account.setOnClickListener(new View.OnClickListener() {
            //This will redirect into LoginActivity's page
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void register() {
        //initialize the input to string variables
        initialize();
        if (!validate()) {
            //In case the registration doesn't meet the requirements.
            Toast.makeText(this,"Sign-up has Failed! Try again.", Toast.LENGTH_LONG).show();
        } else {
            //In case of successful registration will redirect into the MAIN MENU
            onSignupSuccess();
        }
    }

    public void initialize() {
        //The initiate function will help the software to convert from EditText format to String format in order to make easy the validation
        email = email_reg.getText().toString().trim();
        username = user_reg.getText().toString().trim();
        password = pass_reg.getText().toString().trim();
    }

    public boolean validate() {
        //This boolean validation function is to validate the username, password and email!
        //If the user doesn't meet the requirements the boolean return false and will require to insert again
        //In case of user requirement's successful will return true
        boolean valid = true;
        if (username.isEmpty() || username.length()>32) {
            user_reg.setError("Please, enter a valid name.");
            valid = false;
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_reg.setError("Please, enter a valid E-mail address.");
            valid = false;
        }
        if (password.isEmpty()) {
            pass_reg.setError("Please, insert a valid password!");
            valid = false;
        }
        return valid;
    }

    public void onSignupSuccess() {
        String method = "register";
        BackgroundTask backgroundTask = new BackgroundRegisterTask(this);
        backgroundTask.execute(method, email, username, password);
        finish();
    }
}
