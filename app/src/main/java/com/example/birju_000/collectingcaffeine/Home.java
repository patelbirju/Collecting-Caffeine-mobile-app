package com.example.birju_000.collectingcaffeine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private Button setBudget;
    private Button boughtCoffee;
    private Button settingsBtn;
    private TextView budgetText;
    private TextView moneyLeftText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setBudget = (Button)findViewById(R.id.set_budget_button);
        boughtCoffee = (Button) findViewById(R.id.bought_coffee_btn);
        settingsBtn = (Button) findViewById(R.id.settings_btn);
        budgetText = (TextView) findViewById(R.id.budget_text);
        moneyLeftText = (TextView) findViewById(R.id.money_left_text);

        setBudget.setOnClickListener(this);
        boughtCoffee.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);

        //checking if a budget has been added
        updateExpenses();

        if(getIntent().getExtras() != null)
        {
            String newBudget = getIntent().getExtras().get("newBudget").toString();
            if(newBudget != null)
            {
                budgetText.setText("");
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
                break;

            case R.id.settings_btn:
                Intent settingsIntent = new Intent(this, Settings.class);
                startActivity(settingsIntent);
        }
    }

    public void updateExpenses()
    {
        List<CoffeeBudget> budgets;
        CoffeeBudget budget;
        Double totalExpenses = 0.0;
        Double amountLeft = 0.0;
        int budgetAmount = 0;

        CollectingCaffeineDB db = new CollectingCaffeineDB(getApplicationContext());
        totalExpenses = db.getTotalExpenses();
        budgets = db.getBudgets();
        if((budgets != null) && (budgets.size() != 0))
        {
            budget = budgets.get(budgets.size() - 1);
            budgetAmount = budget.getSpendAmount();
            amountLeft = budgetAmount - totalExpenses;
        }
        else
        {
            budgetAmount = 0;
            amountLeft = 0.0;
        }
        budgetText.setText(String.valueOf(budgetAmount));
        moneyLeftText.setText(amountLeft.toString());
    }
}
