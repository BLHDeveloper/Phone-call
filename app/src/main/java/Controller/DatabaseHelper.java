package Controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Data;
import Utils.Utils;


public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME,null, Utils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Utils.TABLE_NAME  +" ("
                + Utils.COLOUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.COLOUMN_FIRSTNAME + " TEXT,"
                + Utils.COLOUMN_LASTNAME + " TEXT,"
                + Utils.COLOUMN_COMPANY + " TEXT,"
                + Utils.COLOUMN_PHONE + " TEXT,"
                + Utils.COLOUMN_EMAIL + " TEXT,"
                + Utils.COLOUMN_NOTES + " TEXT,"
                + Utils.COLOUMN_TIME_STAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                +  " )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Utils.TABLE_NAME);
        onCreate(db);
    }
    public  long insertData(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Utils.COLOUMN_FIRSTNAME , data.getFirstname());
        cv.put(Utils.COLOUMN_LASTNAME , data.getLastname());
        cv.put(Utils.COLOUMN_COMPANY , data.getCompany());
        cv.put(Utils.COLOUMN_PHONE , data.getPhone());
        cv.put(Utils.COLOUMN_EMAIL , data.getEmail());
        cv.put(Utils.COLOUMN_NOTES , data.getNotes());

        long id = db.insert(Utils.TABLE_NAME , null , cv);
        db.close();
        return  id;
    }

    public  int updateData(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Utils.COLOUMN_FIRSTNAME , data.getFirstname());
        cv.put(Utils.COLOUMN_LASTNAME , data.getFirstname());
        cv.put(Utils.COLOUMN_COMPANY , data.getCompany());
        cv.put(Utils.COLOUMN_PHONE , data.getPhone());
        cv.put(Utils.COLOUMN_EMAIL , data.getEmail());
        cv.put(Utils.COLOUMN_NOTES , data.getNotes());

        return db.update(Utils.TABLE_NAME , cv , "id" + " =?",
                new String[]{String.valueOf(data.getId())});
    }


    public  void deleteData(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Utils.TABLE_NAME ,  Utils.COLOUMN_ID + " =?",
                new String[]{String.valueOf(data.getId())});
        db.close();
    }


    @SuppressLint("Range")
    public Data getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Data data = null;
        try (Cursor cursor = db.query(Utils.TABLE_NAME,
                new String[]{Utils.COLOUMN_ID,
                        Utils.COLOUMN_FIRSTNAME,
                        Utils.COLOUMN_LASTNAME,
                        Utils.COLOUMN_COMPANY,
                        Utils.COLOUMN_PHONE,
                        Utils.COLOUMN_EMAIL,
                        Utils.COLOUMN_NOTES,
                        Utils.COLOUMN_TIME_STAMP},
                Utils.COLOUMN_ID + "=?", new String[]{String.valueOf(id)},
                null,null,null,null)) {

            if (cursor != null && cursor.moveToFirst()) {
                data = new Data(
                        cursor.getInt(cursor.getColumnIndex(Utils.COLOUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_FIRSTNAME)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_LASTNAME)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_PHONE)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_NOTES)),
                        cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_TIME_STAMP))
                );
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error while getting data", e);
        }
        return data;
    }





    @SuppressLint("Range")
    public List<Data> getAllData() {
        List<Data> allData = new ArrayList<>();
        String query = "SELECT * FROM "+ Utils.TABLE_NAME +
                " ORDER BY " + Utils.COLOUMN_TIME_STAMP + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( query ,null);
        if(cursor.moveToFirst())
            do{
                Data data = new Data();
                data.setId(cursor.getInt(cursor.getColumnIndex(Utils.COLOUMN_ID)));
                data.setFirstname( cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_FIRSTNAME)));
                data.setLastname( cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_LASTNAME)));
                data.setCompany(cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_COMPANY)));
                data.setCompany(cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_PHONE)));
                data.setCompany(cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_EMAIL)));
                data.setNotes( cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_NOTES)));
                data.setTimeStamp(cursor.getString(cursor.getColumnIndex(Utils.COLOUMN_TIME_STAMP)));

                allData.add(data);

            }while (cursor.moveToNext());
        db.close();
        return allData;
    }


    public  int getDataCount(){
        String query = "SELECT * FROM "+ Utils.TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( query ,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


}