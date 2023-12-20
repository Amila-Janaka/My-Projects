package com.example.advanceapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Registration_Database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE students(regNo TEXT PRIMARY KEY,name TEXT,bDay TEXT,email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }

    public boolean insertData(String regParam, String nameParam, String bDayParam, String emailParam) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues conValues = new ContentValues();
        conValues.put("regNo", regParam);
        conValues.put("name", nameParam);
        conValues.put("bDay", bDayParam);
        conValues.put("email", emailParam);
        long result = db.insert("students", null, conValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(String regParam, String nameParam, String bDayParam, String emailParam) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues conValues = new ContentValues();
        if (nameParam != null) {
            conValues.put("name", nameParam);
        }
        if (nameParam != null) {
            conValues.put("bDay", bDayParam);
        }
        if (nameParam != null) {
            conValues.put("email", emailParam);
        }
        Cursor cursor = db.rawQuery("SELECT * FROM students WHERE regNo = ?", new String[]{regParam});
        if (cursor.getCount() > 0) {
            cursor.close();
            long result = db.update("students", conValues, "regNo = ?", new String[]{regParam});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        cursor.close();
        return false;
    }

    public boolean deleteData(String regParam) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM students WHERE regNo = ?", new String[]{regParam});
        if (cursor.getCount() > 0) {
            cursor.close();
            long result = db.delete("students", "regNo = ?", new String[]{regParam});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        cursor.close();
        return false;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM students",null);
    }
}
