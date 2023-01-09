package com.example.sqlapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomerDB extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String ID = "ID";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String CUSTOMER_AGE = "customer_age";
    public static final String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";

    public CustomerDB(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable= "CREATE TABLE " + CUSTOMER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME + " TEXT, " + CUSTOMER_AGE + " INT, " + ACTIVE_CUSTOMER + " BOOL)";
        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(CustomerModel customermodel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(CUSTOMER_NAME, customermodel.getName());
        cv.put(CUSTOMER_AGE, customermodel.getAge());
        cv.put(ACTIVE_CUSTOMER, customermodel.isActive());
        long insert=db.insert(CUSTOMER_TABLE, null, cv);
        if(insert==-1){
            return false;
        }

        return true;
    }

    public boolean deleteOne(CustomerModel customermodel){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE FROM " + CUSTOMER_TABLE + " WHERE " + ID + " = " + customermodel.getId();
        Cursor c=db.rawQuery(query, null);
        if(c.moveToFirst()){
            return true;
        }
        return false;
    }
    public List<CustomerModel> getAll(){
        List<CustomerModel> returnlist=new ArrayList<>();
        String query="SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery(query, null);
        if(c.moveToFirst()){
            do{
                int customerID=c.getInt(0);
                String customerName=c.getString(1);
                int customerAge=c.getInt(2);
                boolean activeStatus=c.getInt(3)==1 ? true: false;
                CustomerModel customer=new CustomerModel(customerID, customerName, customerAge, activeStatus);
                returnlist.add(customer);
            }
            while(c.moveToNext());

        }
        else{

        }
        c.close();
        db.close();
        return returnlist;
    }


}
