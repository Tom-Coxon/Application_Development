package com.example.tomco.appdevassignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity {

    private
    EditText textName;
    EditText textPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
    };

    public void login(View v){
        textName = (EditText) findViewById(R.id.NameTextField1);
        CharSequence textName_value = textName.getText();

        textPassword = (EditText) findViewById(R.id.PasswordTextField1);
        CharSequence textPassword_value = textPassword.getText();

        if(textName_value.length() < 1 || textPassword_value.length() < 1){
            Context context = getApplicationContext();
            CharSequence text = "Invalid: Fields not filled in";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {

            String username = textName.getText().toString();
            String password = textPassword.getText().toString();
            String type = "login";
            ConnectionActivity connection = new ConnectionActivity(this);
            connection.execute(type, username, password);
        }
    };

    public void register(View v){
        textName = (EditText) findViewById(R.id.NameTextField1);
        CharSequence textName_value = textName.getText();

        textPassword = (EditText) findViewById(R.id.PasswordTextField1);
        CharSequence textPassword_value = textPassword.getText();

        if(textName_value.length() < 1 || textPassword_value.length() < 1){
            Context context = getApplicationContext();
            CharSequence text = "Invalid: Fields not filled in";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {

            String username = textName.getText().toString();
            String password = textPassword.getText().toString();
            String type = "register";
            ConnectionActivity connection = new ConnectionActivity(this);
            connection.execute(type, username, password);
        }
    };
}
