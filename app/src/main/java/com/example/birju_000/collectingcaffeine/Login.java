package com.example.birju_000.collectingcaffeine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener{
    //variables
    private Button signupBtn;
    private Button signinBtn;
    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupBtn = (Button)findViewById(R.id.sign_up_btn);
        signinBtn = (Button)findViewById(R.id.sign_in_btn);

        signupBtn.setOnClickListener(this);
        signinBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.sign_up_btn)
        {
            Intent signUp = new Intent(this, Signup.class);
            startActivity(signUp);
        }
        if(view.getId() == R.id.sign_in_btn)
        {
            userName = ((EditText)findViewById(R.id.user_name_login)).getText().toString();
            password = ((EditText)findViewById(R.id.password_login)).getText().toString();
            CollectingCaffeineDB db = new CollectingCaffeineDB(getApplicationContext());
            User user = db.getUser(userName);
            if(user == null)
            {
                Toast.makeText(this, "Error: user not found ", Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent home = new Intent(this, Home.class);
                startActivity(home);
            }
        }

    }
}
