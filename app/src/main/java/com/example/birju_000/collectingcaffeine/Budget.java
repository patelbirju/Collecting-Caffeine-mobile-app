package com.example.birju_000.collectingcaffeine;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class Budget extends AppCompatActivity implements View.OnClickListener {
    private Button saveBudgetBtn;
    private int income;
    private Long spendAmount;
    private int coffeeAmount = 0;
    private NumberPicker np;
    private TextView coffeeConsumptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        saveBudgetBtn = (Button) findViewById(R.id.save_budget_btn);
        saveBudgetBtn.setOnClickListener(this);
        coffeeConsumptionText = (TextView)findViewById(R.id.coffee_consumption_text);

        np = (NumberPicker)findViewById(R.id.coffee_num);
        np.setMaxValue(30);
        np.setMinValue(0);
        np.setWrapSelectorWheel(true);
        np.setOnValueChangedListener( new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
                coffeeConsumptionText.setText("Daily Coffee Consumption : "+
                        newVal);
                coffeeAmount = newVal;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_budget_btn)
        {
            income = Integer.parseInt(((EditText)findViewById(R.id.monthly_income_text)).getText().toString());
            spendAmount = Long.parseLong(((EditText)findViewById(R.id.spending_text)).getText().toString());

            try {
                ContentValues values = new ContentValues();
                values.put(CollectingCaffeineDB.INCOME, income);
                values.put(CollectingCaffeineDB.SPEND_AMOUNT, spendAmount);
                values.put(CollectingCaffeineDB.COFFEE_AMOUNT, coffeeAmount);

                CollectingCaffeineDB db = new CollectingCaffeineDB(getApplicationContext());

                long rowID = db.insertBudget(values);
                if (rowID > 0) {
                    Toast.makeText(this, "Your Budget has successfully been created. ", Toast.LENGTH_LONG).show();

                    Intent homeIntent = new Intent(this, Home.class);
                    homeIntent.putExtra("newBudget", spendAmount);
                    startActivity(homeIntent);
                }
            }
            catch (Exception ex) {
                Log.e("Error inserting user ", ex.getMessage().toString());
            }
        }
    }
}
