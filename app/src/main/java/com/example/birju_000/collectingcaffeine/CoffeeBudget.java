package com.example.birju_000.collectingcaffeine;

/**
 * Created by birju_000 on 06/01/2018.
 */

public class CoffeeBudget {

    private int budgetId;
    private int income;
    private int spendAmount;
    private int coffeeAmount;

    public CoffeeBudget(int budgetId, int income, int spendAmount, int coffeeAmount) {
        this.budgetId = budgetId;
        this.income = income;
        this.spendAmount = spendAmount;
        this.coffeeAmount = coffeeAmount;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getSpendAmount() {
        return spendAmount;
    }

    public void setSpendAmount(int spendAmount) {
        this.spendAmount = spendAmount;
    }

    public int getCoffeeAmount() {
        return coffeeAmount;
    }

    public void setCoffeeAmount(int coffeeAmount) {
        this.coffeeAmount = coffeeAmount;
    }
}
