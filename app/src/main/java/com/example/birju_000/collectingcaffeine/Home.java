package com.example.birju_000.collectingcaffeine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private Button setBudget;
    private Button boughtCoffee;
    private TextView budgetText;
    private TextView moneyLeftText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setBudget = (Button)findViewById(R.id.set_budget_button);
        boughtCoffee = (Button) findViewById(R.id.bought_coffee_btn);
        budgetText = (TextView) findViewById(R.id.budget_text);
        budgetText.setText("0");
        moneyLeftText = (TextView) findViewById(R.id.money_left_text);
        moneyLeftText.setText("0");

        setBudget.setOnClickListener(this);
        boughtCoffee.setOnClickListener(this);

        //checking if a budget has been added

        if(getIntent().getExtras() != null)
        {
            String newBudget = getIntent().getExtras().get("newBudget").toString();
            if(newBudget != null)
            {
                budgetText.setText(newBudget);
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.set_budget_button:
                Intent setBudget = new Intent(this, Budget.class);
                startActivity(setBudget);
                break;

            case R.id.bought_coffee_btn:
                Intent coffeeExpenseEntry = new Intent(this, CoffeeExpenseEntry.class);
                startActivity(coffeeExpenseEntry);
        }

    }
}
