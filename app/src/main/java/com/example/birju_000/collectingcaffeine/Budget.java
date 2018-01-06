package com.example.birju_000.collectingcaffeine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

public class Budget extends AppCompatActivity {

    NumberPicker coffeeAmount;
    private TextView coffeeConsumptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        coffeeConsumptionText = (TextView)findViewById(R.id.coffee_consumption_text);

        coffeeAmount = (NumberPicker)findViewById(R.id.coffee_num);
        coffeeAmount.setMaxValue(30);
        coffeeAmount.setMinValue(0);
        coffeeAmount.setWrapSelectorWheel(true);
        coffeeAmount.setOnValueChangedListener( new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
                coffeeConsumptionText.setText("Daily Coffee Consumption : "+
                        newVal);
            }
        });
    }
}
