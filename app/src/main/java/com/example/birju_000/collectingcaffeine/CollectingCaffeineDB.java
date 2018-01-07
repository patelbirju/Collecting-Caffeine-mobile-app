package com.example.birju_000.collectingcaffeine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by birju_000 on 06/01/2018.
 */

public class CollectingCaffeineDB {


    //Database constatnts
    public static final String DB_NAME = "CollectingCaffeine.db";
    public static final int DB_VERSION = 1;


    //users table constants
    public static final String USERS_TABLE = "users";

    public static final String USER_ID = "_id";
    public static final int USER_ID_COL = 0;

    public static final String FIRST_NAME = "firstName";
    public static final int FIRST_NAME_COL = 1;

    public static final String LAST_NAME = "lastName";
    public static final int LAST_NAME_COL = 2;

    public static final String EMAIL = "email";
    public static final int EMAIL_COL = 3;

    public static final String USERNAME = "userName";
    public static final int USERNAME_COL = 4;

    public static final String PASSWORD = "password";
    public static final int PASSWORD_COL = 5;

    //budget table constants
    public static  final String BUDGET_TABLE = "budget";

    public static final String BUDGET_ID = "_id";
    public static final int BUDGET_ID_COL = 0;

    public static final String INCOME = "income";
    public static final int INCOME_COL = 1;

    public static final String SPEND_AMOUNT = "spendAmount";
    public static final int SPEND_AMOUNT_COL = 2;

    public static final String COFFEE_AMOUNT = "coffeeAmount";
    public static final int COFFEE_AMOUNT_COL = 3;

    //coffeeExpense table constants
    //budget table constants
    public static  final String COFFEE_EXPENSE_TABLE = "coffee_expense";

    public static final String COFFEE_EXPENSE_ID = "_id";
    public static final int COFFEE_EXPENSE_ID_COL = 0;

    public static final String COFFEE_EXPENSE_AMOUNT_SPENT = "amount";
    public static final int COFFEE_EXPENSE_AMOUNT_SPENT_COL = 1;

    public static final String COFFEE_EXPENSE_DATE = "date";
    public static final int COFFEE_EXPENSE_DATE_COL = 2;

    //Create and drop table statements
    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + USERS_TABLE + "(" +
                    USER_ID + " INTEGER PRIMARY KEY," +
                    FIRST_NAME + " TEXT," +
                    LAST_NAME + " TEXT," +
                    EMAIL + " TEXT," +
                    USERNAME + " TEXT," +
                    PASSWORD + " TEXT);";

    private static final String CREATE_BUDGET_TABLE =
            "CREATE TABLE " + BUDGET_TABLE + "(" +
                    BUDGET_ID + " INTEGER PRIMARY KEY," +
                    INCOME + " INTEGER," +
                    SPEND_AMOUNT + " INTEGER," +
                    COFFEE_AMOUNT + " INTEGER);";

    private static final String CREATE_COFFEE_EXPENSE_TABLE =
            "CREATE TABLE " + COFFEE_EXPENSE_TABLE + "(" +
                    COFFEE_EXPENSE_ID + " INTEGER PRIMARY KEY," +
                    COFFEE_EXPENSE_AMOUNT_SPENT + " REAL," +
                    COFFEE_EXPENSE_DATE + " TEXT);";

    private static  final String DELETE_USERS_TABLE =
            "DROP TABLE IF EXISTS " + USERS_TABLE + ";";
    private static  final String DELETE_BUDGET_TABLE =
            "DROP TABLE IF EXISTS " + BUDGET_TABLE + ";";
    private static  final String DELETE_COFFEE_EXPENSE_TABLE =
            "DROP TABLE IF EXISTS " + COFFEE_EXPENSE_TABLE + ";";


    //Using SQliteOpenHelper to create the database

    public static class DbHelper extends SQLiteOpenHelper {



        public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USERS_TABLE);
            db.execSQL(CREATE_BUDGET_TABLE);
            db.execSQL(CREATE_COFFEE_EXPENSE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DELETE_USERS_TABLE);
            db.execSQL(DELETE_BUDGET_TABLE);
            db.execSQL(DELETE_COFFEE_EXPENSE_TABLE);
            onCreate(db);
        }
    }

    private SQLiteDatabase db;
    private DbHelper dbHelper;

    //constructor
    public CollectingCaffeineDB(Context context){
        dbHelper = new DbHelper(context, DB_NAME, null, DB_VERSION);
    }

    //private methods
    private void openReadableDB(){
        db = dbHelper.getReadableDatabase();
    }
    private void openWriteableDB(){
        db = dbHelper.getWritableDatabase();
    }
    private void closeDB(){
        if(db != null){
            db.close();
        }
    }

    //USER TABLE CRUD METHODS
    public long insertUser(ContentValues values){
        this.openWriteableDB();
        long rowID = db.insert(USERS_TABLE, null, values);
        this.closeDB();

        return rowID;
    }

    public User getUser(String userName)
    {
        String where = USERNAME + "=?";
        String[] whereArgs = {userName};

        this.openReadableDB();
        Cursor cursor = db.query(USERS_TABLE, null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        User user = getUserFromCursor(cursor);
        if(cursor != null){
            cursor.close();
        }
        this.closeDB();
        return  user;
    }


    private static User getUserFromCursor(Cursor cursor)
    {
        if(cursor == null || cursor.getCount() == 0){
            return  null;
        }
        else{
            try{
                User user = new User(
                        cursor.getInt(USER_ID_COL),
                        cursor.getString(FIRST_NAME_COL),
                        cursor.getString(LAST_NAME_COL),
                        cursor.getString(EMAIL_COL),
                        cursor.getString(USERNAME_COL),
                        cursor.getString(PASSWORD_COL)
                );
                return user;
            }
            catch (Exception e){
                return  null;
            }
        }
    }

    public int deleteUser(long id)
    {
        String where = USER_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};

        this.openWriteableDB();
        int rowCount = db.delete(USERS_TABLE, where, whereArgs);
        this.closeDB();

        return  rowCount;
    }

    //BUDGET TABLE CRUD METHODS

    public long insertBudget(ContentValues values){
        this.openWriteableDB();
        long rowID = db.insert(BUDGET_TABLE, null, values);
        this.closeDB();

        return rowID;
    }


    private static CoffeeBudget getBudgetsFromCursor(Cursor cursor)
    {
        if(cursor == null || cursor.getCount() == 0){
            return  null;
        }
        else{
            try{
                CoffeeBudget budget = new CoffeeBudget(
                        cursor.getInt(BUDGET_ID_COL),
                        cursor.getInt(INCOME_COL),
                        cursor.getInt(SPEND_AMOUNT_COL),
                        cursor.getInt(COFFEE_AMOUNT_COL)
                );
                return budget;
            }
            catch (Exception e){
                return  null;
            }
        }
    }

    public List<CoffeeBudget> getBudgets(){
        List<CoffeeBudget> budgets = new ArrayList<>();

        String query = "SELECT * FROM " + BUDGET_TABLE;
        this.openWriteableDB();
        Cursor cursor = db.rawQuery(query, null);

        //looping through all rows
        if(cursor.moveToFirst()){
            do{
                CoffeeBudget cb = new CoffeeBudget();
                cb.setBudgetId(cursor.getInt(0));
                cb.setIncome(cursor.getInt(1));
                cb.setSpendAmount(cursor.getInt(2));
                cb.setCoffeeAmount(cursor.getInt(3));
                budgets.add(cb);
            }while (cursor.moveToNext());
        }
        return  budgets;
    }

    public void DropBudgetTable()
    {
        this.openWriteableDB();
        db.execSQL(DELETE_BUDGET_TABLE);
        db.execSQL(CREATE_BUDGET_TABLE);
        this.closeDB();
    }

    //COFFEE_EXPENSE_TABLE METHODS
    public long insertCoffeeExpense(ContentValues values){
        this.openWriteableDB();
        long rowID = db.insert(COFFEE_EXPENSE_TABLE, null, values);
        this.closeDB();

        return rowID;
    }

    public double getTotalExpenses(){
        double total = 0.0;
        String query = "SELECT * FROM " + COFFEE_EXPENSE_TABLE;
        this.openWriteableDB();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do
            {
                total += cursor.getDouble(1);
            }while (cursor.moveToNext());
        }
        return total;
    }

    public void DropExpensesTable()
    {
        this.openWriteableDB();
        db.execSQL(DELETE_COFFEE_EXPENSE_TABLE);
        db.execSQL(CREATE_COFFEE_EXPENSE_TABLE);
        this.closeDB();
    }
}


