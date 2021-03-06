package me.gtihtina.parstagram;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText usernameInput;
    private EditText passwordInput;
    private Button signupbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            final Intent i = new Intent(MainActivity.this, TabsActivity.class);
            startActivity(i);
        }

        setContentView(R.layout.activity_main);
        usernameInput = findViewById(R.id.etUsername1);
        passwordInput = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.btLogin);
        signupbtn = findViewById(R.id.signupbtn);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();
                login(username,password);
            }
        });



    }

    private void login(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null){
                    Log.d("LoginActivity", "Login successful.");
                    final Intent i = new Intent(MainActivity.this, TabsActivity.class);
                    startActivity(i);
                    finish();

                } else {
                    Log.e("LoginActivity", "Login failure.");
                    e.printStackTrace();
                }
            }
        });

    }
}
