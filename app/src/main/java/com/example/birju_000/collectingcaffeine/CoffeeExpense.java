package com.example.birju_000.collectingcaffeine;

/**
 * Created by birju_000 on 06/01/2018.
 */

public class CoffeeExpense {

    private int coffeeExpenseId;
    private double amountSpent;
    private String date;

    public CoffeeExpense(int coffeeExpenseId, double amountSpent, String date) {
        this.coffeeExpenseId = coffeeExpenseId;
        this.amountSpent = amountSpent;
        this.date = date;
    }

    public int getCoffeeExpenseId() {
        return coffeeExpenseId;
    }

    public void setCoffeeExpenseId(int coffeeExpenseId) {
        this.coffeeExpenseId = coffeeExpenseId;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
