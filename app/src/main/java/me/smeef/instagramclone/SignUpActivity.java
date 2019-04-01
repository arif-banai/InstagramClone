package me.smeef.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    private EditText etUsernameSignUp;
    private EditText etEmailSignUp;
    private EditText etPasswordSignUp;
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsernameSignUp = findViewById(R.id.etUserNameSignUp);
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = etUsernameSignUp.getText().toString();
                String email = etEmailSignUp.getText().toString();
                String password = etPasswordSignUp.getText().toString();

                signUp(username, email, password);
            }
        });

    }

    private void signUp(String username, String email, String password) {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        user.put("handle", username);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    goMainActivity();
                } else {
                    Log.e(TAG, "Error in creating new User (signUp)");

                    Toast.makeText(SignUpActivity.this, "Error in creating new account!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void goMainActivity() {
        Log.d(TAG, "Navigating to main activity");
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
        finish(); //End login activity
    }
}
