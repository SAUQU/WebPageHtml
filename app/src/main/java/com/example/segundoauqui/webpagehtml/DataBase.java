package com.example.segundoauqui.webpagehtml;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.segundoauqui.webpagehtml.Model.User;

/**
 * Created by segundoauqui on 9/25/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyData";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "User";
    private static final String USER_NAME = "Name";
    private static final String TAG = "DataBase";
    boolean isSaved =  false;
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("+
                USER_NAME + " TEXT "+
                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean saveUser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put(USER_NAME, user.getName());
        database.insert(TABLE_NAME, null, contentValues);
        isSaved = true;
        return isSaved;
    }
}
