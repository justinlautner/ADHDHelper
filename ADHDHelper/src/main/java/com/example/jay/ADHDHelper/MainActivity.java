package com.example.jay.ADHDHelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView signOn;
    private EditText eMail;
    private EditText password;
    Button signIn;
    boolean create = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Temporarily skip login screen for debugging
        Intent intent = new Intent(this, AppHub.class);
        startActivity(intent);

        //Initialize XML content variables
        signOn = findViewById(R.id.signOn);
        eMail = findViewById(R.id.eMail);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);

        //Set GUI content variable text
        signOn.setText("Sign In");
        eMail.setHint("Enter E-Mail Address");
        password.setHint("Enter Password");
        signIn.setText("Enter");
    }

    protected void continueMain (View view){
        //Create strings for user entered text
        String eMailSubmission = eMail.getText().toString();
        String passwordSubmission = password.getText().toString();

        String empty = new String();

        //Retrieve previously saved email & password
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String savedeMail = sharedPref.getString("eMail", "");
        String savedPassword = sharedPref.getString("Password", "");

        //Check for blank text boxes
        if (eMailSubmission.equals(empty) && passwordSubmission.equals(empty)) {
            Toast.makeText(view.getContext(), "Please enter an e-mail address and password", Toast.LENGTH_LONG).show();
        }
        else if (passwordSubmission.equals(empty)){
            Toast.makeText(view.getContext(), "Please enter a password", Toast.LENGTH_LONG).show();
        }
        else if (eMailSubmission.equals(empty)){
            Toast.makeText(view.getContext(), "Please enter an e-mail address", Toast.LENGTH_LONG).show();
        }

        //Check for previous login information, if none, create new
        else if(savedeMail.equals(empty) && savedPassword.equals(empty) && !create){
            signOn.setText("Create A New Account");
            signIn.setText("Create");
            Toast.makeText(view.getContext(), "Please create an account", Toast.LENGTH_LONG).show();
            create = true;
        }
        else if (create){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("eMail", eMailSubmission);
            editor.putString("Password", passwordSubmission);
            editor.commit();
            Intent intent = new Intent(this, AppHub.class);
            startActivity(intent);
        }

        //Check for incorrect login information
        else if(!savedeMail.equals(eMailSubmission) || !savedPassword.equals(passwordSubmission)){
            Toast.makeText(view.getContext(), "Your username or password is incorrect\nPlease try again", Toast.LENGTH_LONG).show();
        }

        //Upon proper login verification start application
        else if (savedeMail.equals(eMailSubmission) && savedPassword.equals(passwordSubmission)) {
            Intent intent = new Intent(this, AppHub.class);
            startActivity(intent);
        }
    }
}