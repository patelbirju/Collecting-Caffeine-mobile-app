package com.example.birju_000.collectingcaffeine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private Button setBudget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setBudget = (Button)findViewById(R.id.set_budget_button);

        setBudget.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.set_budget_button:
                Intent setBudget = new Intent(this, Budget.class);
                startActivity(setBudget);
                break;
        }

    }
}
