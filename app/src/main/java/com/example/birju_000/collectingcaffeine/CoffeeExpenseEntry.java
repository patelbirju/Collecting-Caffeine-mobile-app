package com.example.birju_000.collectingcaffeine;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CoffeeExpenseEntry extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private EditText datePicked;
    private Button saveExpenseBtn;

    private double amountSpent;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_expense_entry);

        datePicked = (EditText) findViewById(R.id.datePicked);
        saveExpenseBtn = (Button) findViewById(R.id.saveExpenseBtn);

        datePicked.setOnClickListener(this);
        saveExpenseBtn.setOnClickListener(this);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.datePicked)
        {
            showDatePickerDialog(view);
        }
        if(view.getId() == R.id.saveExpenseBtn)
        {
            amountSpent = Double.parseDouble(((EditText) findViewById(R.id.amount_spent)).getText().toString());
            date = datePicked.getText().toString();

            try{
                ContentValues values = new ContentValues();
                values.put(CollectingCaffeineDB.COFFEE_EXPENSE_AMOUNT_SPENT, amountSpent);
                values.put(CollectingCaffeineDB.COFFEE_EXPENSE_DATE, date);


                CollectingCaffeineDB db = new CollectingCaffeineDB(getApplicationContext());

                long rowID = db.insertCoffeeExpense(values);
                if(rowID > 0){
                    Toast.makeText(this, "Your Expense is Successfully added ", Toast.LENGTH_LONG).show();
                    Intent homeIntent = new Intent(this, Home.class);
                    startActivity(homeIntent);
                }
            }
            catch (Exception ex){
                Log.e("Exception message: ",ex.getMessage());
            }
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }
}
