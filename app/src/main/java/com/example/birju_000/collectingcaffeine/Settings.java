package com.example.birju_000.collectingcaffeine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    private Button clearExpensesBtn;
    private Button clearBudgetsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        clearExpensesBtn = (Button) findViewById(R.id.clearExpensesBtn);
        clearBudgetsBtn = (Button) findViewById(R.id.clearBudgetsBtn);

        clearExpensesBtn.setOnClickListener(this);
        clearBudgetsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        CollectingCaffeineDB db = new CollectingCaffeineDB(getApplicationContext());

        if(view.getId() == R.id.clearExpensesBtn)
        {
            db.DropExpensesTable();
            Toast.makeText(this, "All Expenses cleared!", Toast.LENGTH_LONG).show();

            Intent home = new Intent(this, Home.class);
            startActivity(home);
        }
        if(view.getId() == R.id.clearBudgetsBtn)
        {
            db.DropBudgetTable();
            Toast.makeText(this, "All Budgets cleared!", Toast.LENGTH_LONG).show();

            Intent home = new Intent(this, Home.class);
            startActivity(home);
        }
    }
}
