package edu.orangecoastcollege.cs273.nhoang53.petprotector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by Joseph on 11/3/2016.
 */

class DBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "PerProtector";
    private static final String DATABASE_TABLE = "Pet";
    private static final int DATABASE_VERSION = 1;

    // Define field for table
    private static final String KEY_FIELD_ID = "id";
    private static final String KEY_FIELD_NAME = "name";
    private static final String KEY_FIELD_DETAILS = "details";
    private static final String KEY_FIELD_PHONE = "phone";
    private static final String KEY_FIELD_IMAGEURI = "imageUri";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String table = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_FIELD_NAME + " TEXT, "
                + KEY_FIELD_DETAILS + " TEXT, "
                + KEY_FIELD_PHONE + " TEXT, "
                + KEY_FIELD_IMAGEURI + " TEXT)";
        sqLiteDatabase.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addPet(Pet newPet)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIELD_NAME, newPet.getName());
        values.put(KEY_FIELD_DETAILS, newPet.getDetail());
        values.put(KEY_FIELD_PHONE, newPet.getPhone());
        values.put(KEY_FIELD_IMAGEURI, String.valueOf(newPet.getmImageUri()));

        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    public ArrayList<Pet> getAllPets()
    {
        ArrayList<Pet> petList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            do{
                Pet pet = new Pet(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        Uri.parse(cursor.getString(4)));

                petList.add(pet);
            }
            while (cursor.moveToNext());
        }

        return petList;
    }
}
