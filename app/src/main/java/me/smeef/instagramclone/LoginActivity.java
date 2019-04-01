package me.smeef.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username, password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goSignUpActivity();
            }
        });

        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null) {
            goMainActivity();
        }
    }

    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "issue with login");
                    Toast.makeText(LoginActivity.this, "Invalid Login Credentials!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    return;
                }

                goMainActivity();
            }
        });
    }

    private void goSignUpActivity() {
        Log.d(TAG, "Navigating to signup activity");
        Intent i = new Intent(this, SignUpActivity.class);

        startActivity(i);
        finish();
    }

    private void goMainActivity() {
        Log.d(TAG, "Navigating to main activity");
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
        finish(); //End login activity
    }
}
