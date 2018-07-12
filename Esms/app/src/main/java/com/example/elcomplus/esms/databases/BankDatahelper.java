package com.example.elcomplus.esms.databases;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.elcomplus.esms.models.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankDatahelper extends SQLiteOpenHelper  {

    private static final String BANK_ID="ID";
    private static final String BANK_PHONE="BANK_PHONE";
    private static final String DATABASE="DATABASE";
    private static final String BANK_TABLE_NAME="BANK_TABLE_NAME";
    public BankDatahelper(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE " + BANK_TABLE_NAME+ " ( " +BANK_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+ BANK_PHONE +" TEXT )";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE IF EXISTS "+BANK_TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
    public void addBank(Bank bank){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(BANK_PHONE,bank.getPhone());
        db.insert(BANK_TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Bank> getAll() {
        ArrayList<Bank> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + BANK_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("ttt", "getAll: " + cursor);
        if (cursor.moveToFirst()) {
            do {
                Bank bank = new Bank();
                bank.setId(cursor.getInt(cursor.getColumnIndex(BANK_ID)));
                bank.setPhone(cursor.getString(cursor.getColumnIndex(BANK_PHONE)));
                list.add(bank);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public void deletebank(Bank user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BANK_TABLE_NAME, BANK_ID + "=?", new String[]{String.valueOf(user.getId())});
        db.close();

    }


    public Bank getBank(String BankPhone){
        Bank bank=new Bank();
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " +BANK_TABLE_NAME +" WHERE BANK_PHONE =? " ;
        Cursor cursor=db.rawQuery(query, new String[]{BankPhone});
        if(cursor!=null){
            cursor.moveToNext();
            bank.setId(cursor.getInt(cursor.getColumnIndex(BANK_ID)));
            bank.setPhone(cursor.getString(cursor.getColumnIndex(BANK_PHONE)));

        }
        cursor.close();
        return bank;
    }
}
