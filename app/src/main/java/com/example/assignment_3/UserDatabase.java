package com.example.assignment_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users_data";
    private static final String TABLE_SCORES = "users_score";
    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_SCORE = "score";


    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORES_TABLE = "CREATE TABLE " + TABLE_SCORES + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SCORE + " TEXT"+ ");";
        db.execSQL(CREATE_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(db);
    }

    void addUserScores(UserScores userScores){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, userScores.getName());
        values.put(KEY_SCORE, userScores.getScore());

        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    UserScores getScore(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SCORES, new String[]{KEY_ID, KEY_NAME, KEY_SCORE}, KEY_NAME + "=?",
                new String[]{name}, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        UserScores scores = new UserScores(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        return scores;
    }

    public List<UserScores> getAllTable(){
        List<UserScores> score_list = new ArrayList<>();
        String selectQuery = "SELECT *FROM " + TABLE_SCORES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                UserScores userScores = new UserScores();
                userScores.setId(Integer.parseInt(cursor.getString(0)));
                userScores.setName(cursor.getString(1));
                userScores.setScore(Integer.parseInt(cursor.getString(2)));
                score_list.add(userScores);
            } while (cursor.moveToNext());

        }
        db.close();
        return score_list;
    }

    public void updateScore(UserScores userScores){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, userScores.getName());
        values.put(KEY_SCORE, userScores.getScore());

        db.update(TABLE_SCORES, values, KEY_ID + "=?",
                new String[]{String.valueOf(userScores.getId())});
    }

    public void deleteAll(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCORES, null, null);
        db.execSQL("delete from "+ TABLE_SCORES);

        db.close();
    }

    public void deleteScore(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {id};
        db.delete(TABLE_SCORES, KEY_ID + "=?", args);
    }


}