package com.example.birju_000.collectingcaffeine;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    private Button join_btn;

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        join_btn = (Button) findViewById(R.id.join_btn);
        join_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.join_btn) {
            firstName = ((EditText) findViewById(R.id.first_name_text)).getText().toString();
            lastName = ((EditText) findViewById(R.id.last_name_text)).getText().toString();
            email = ((EditText) findViewById(R.id.email_text)).getText().toString();
            userName = ((EditText) findViewById(R.id.username_signup_text)).getText().toString();
            password = ((EditText) findViewById(R.id.password_signup_text)).getText().toString();

            try {
                ContentValues values = new ContentValues();
                values.put(CollectingCaffeineDB.FIRST_NAME, firstName);
                values.put(CollectingCaffeineDB.LAST_NAME, lastName);
                values.put(CollectingCaffeineDB.EMAIL, email);
                values.put(CollectingCaffeineDB.USERNAME, userName);
                values.put(CollectingCaffeineDB.PASSWORD, password);

                CollectingCaffeineDB db = new CollectingCaffeineDB(getApplicationContext());

                long rowID = db.insertUser(values);
                if (rowID > 0) {
                    Toast.makeText(this, "Account Successfully Created. ", Toast.LENGTH_LONG).show();

                    Intent homeIntent = new Intent(this, Home.class);
                    startActivity(homeIntent);
                }
            }
            catch (Exception ex) {
                Log.e("Error inserting user ", ex.getMessage().toString());
            }
        }
    }
}
