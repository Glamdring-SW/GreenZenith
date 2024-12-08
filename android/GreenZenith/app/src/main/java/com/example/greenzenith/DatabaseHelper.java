package com.example.greenzenith;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "GreenZenith.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (user TEXT PRIMARY KEY, email TEXT, password TEXT)");

        db.execSQL("CREATE TABLE plants (" +
                "name TEXT PRIMARY KEY, " +
                "description TEXT, " +
                "hour INTEGER, " +
                "minutes INTEGER, " +
                "days TEXT, " +
                "planting TEXT, " +
                "user TEXT, " +
                "FOREIGN KEY(user) REFERENCES users(user) ON DELETE CASCADE)");

        db.execSQL("CREATE TABLE days (" +
                "id TEXT PRIMARY KEY, " +
                "day1 TEXT, " +
                "day2 TEXT, " +
                "day3 TEXT, " +
                "day4 TEXT, " +
                "day5 TEXT, " +
                "day6 TEXT, " +
                "day7 TEXT, " +
                "name TEXT, " +
                "FOREIGN KEY(name) REFERENCES plants(name) ON DELETE CASCADE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS days");
        db.execSQL("DROP TABLE IF EXISTS plants");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean insertUser(String user, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("user", user);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = db.insert("users", null, contentValues);

        return result != -1;
    }

    public boolean checkUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE user = ?", new String[]{user});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public Boolean checkUserPassword(String user, String password) {

        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where user = ? and password = ?", new String[]{user, password});

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }

    }

    public Cursor getUser(String user){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from users where user = ? ", new String[]{user});
        return cursor;
    }

    public ArrayList<User> getAllUsers() {
        SQLiteDatabase myDB = null;
        Cursor cursor = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            myDB = this.getReadableDatabase();

            cursor = myDB.rawQuery("SELECT * FROM users", null);

            if (cursor != null && cursor.moveToFirst()) {
                do {

                    String username = cursor.getString(cursor.getColumnIndexOrThrow("user"));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                    String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

                    User user = new User(username, email, password);
                    users.add(user);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (myDB != null) {
                myDB.close();
            }
        }
        return users;
    }

    public boolean updateUserPwd(String user, String newPassword) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword);

        int rowsAffected = myDB.update("users", values, "user = ?", new String[]{user});
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUserEmail(String user, String newEmail) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", newEmail);

        int rowsAffected = myDB.update("users", values, "user = ?", new String[]{user});
        return rowsAffected > 0;
    }

    public boolean deleteUser(String user) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE user = ?", new String[]{user});
        if (cursor.getCount() > 0) {
            cursor.close();

            int rowsAffected = myDB.delete("users", "user=?", new String[]{user});
            return rowsAffected > 0;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean insertplant(String name, String description, int hour, int minutes, String planting, String days, String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("hour", hour);
        contentValues.put("minutes", minutes);
        contentValues.put("planting", planting);
        contentValues.put("days", days);
        contentValues.put("user", user);

        long result = db.insert("plants", null, contentValues);
        return result != -1;
    }

    public boolean updateplant(String name, String description, int hour, int minutes, boolean vibration, String days) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("description", description);
        contentValues.put("hour", hour);
        contentValues.put("minutes", minutes);
        contentValues.put("vibration", vibration ? 1 : 0);
        contentValues.put("days", days);

        int rowsAffected = db.update("plants", contentValues, "name = ?", new String[]{name});
        return rowsAffected > 0;
    }

    public boolean deleteplant(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("plants", "name = ?", new String[]{name});
        return rowsDeleted > 0;
    }

    public ArrayList<Plant> getAllPlants(String user) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ArrayList<Plant> plants = new ArrayList<>();

        try {
            db = this.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM plants WHERE user = ?", new String[]{user});

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                    int hour = cursor.getInt(cursor.getColumnIndexOrThrow("hour"));
                    int minutes = cursor.getInt(cursor.getColumnIndexOrThrow("minutes"));
                    String planting = cursor.getString(cursor.getColumnIndexOrThrow("planting"));
                    String days = cursor.getString(cursor.getColumnIndexOrThrow("days"));

                    Log.d("DatabaseHelper", "Plant found: " + name);

                    Plant plant = new Plant(name, description, hour, minutes, planting, days, user);
                    plants.add(plant);
                } while (cursor.moveToNext());
            } else {
                Log.d("DatabaseHelper", "No plants found for user: " + user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DatabaseHelper", "Error in getAllPlants: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        Log.d("DatabaseHelper", "Total plants retrieved: " + plants.size());
        return plants;
    }

    public ArrayList<Plant> getAllPlants() {
        SQLiteDatabase myDB = null;
        Cursor cursor = null;
        ArrayList<Plant> plants = new ArrayList<>();

        try {
            myDB = this.getReadableDatabase();

            cursor = myDB.rawQuery("SELECT * FROM plants", null);

            if (cursor != null && cursor.moveToFirst()) {
                do {

                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                    int hour = cursor.getInt(cursor.getColumnIndexOrThrow("hour"));
                    int minutes = cursor.getInt(cursor.getColumnIndexOrThrow("minutes"));
                    String planting = cursor.getString(cursor.getColumnIndexOrThrow("planting"));
                    String days = cursor.getString(cursor.getColumnIndexOrThrow("days"));

                    Plant plant = new Plant(name, description, hour, minutes, planting, days);
                    plants.add(plant);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (myDB != null) {
                myDB.close();
            }
        }
        return plants;
    }


    public boolean insertDay(ArrayList<String> days, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try {

            contentValues.put("name", name);
            contentValues.put("day1", days.get(0));
            contentValues.put("day2", days.get(1));
            contentValues.put("day3", days.get(2));
            contentValues.put("day4", days.get(3));
            contentValues.put("day5", days.get(4));
            contentValues.put("day6", days.get(5));
            contentValues.put("day7", days.get(6));

            long result = db.insert("days", null, contentValues);
            return result != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }


    public boolean updateDay(String id, String day1, String day2, String day3, String day4, String day5, String day6, String day7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("day1", day1);
        contentValues.put("day2", day2);
        contentValues.put("day3", day3);
        contentValues.put("day4", day4);
        contentValues.put("day5", day5);
        contentValues.put("day6", day6);
        contentValues.put("day7", day7);

        int rowsAffected = db.update("days", contentValues, "id = ?", new String[]{id});
        return rowsAffected > 0;
    }

    public boolean deleteDay(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("days", "id = ?", new String[]{id});
        return rowsDeleted > 0;
    }

    public ArrayList<String> getDays(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> daysList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM days WHERE name = ?", new String[]{name});
        if (cursor != null && cursor.moveToFirst()) {
            String day1 = cursor.getString(cursor.getColumnIndexOrThrow("day1"));
            String day2 = cursor.getString(cursor.getColumnIndexOrThrow("day2"));
            String day3 = cursor.getString(cursor.getColumnIndexOrThrow("day3"));
            String day4 = cursor.getString(cursor.getColumnIndexOrThrow("day4"));
            String day5 = cursor.getString(cursor.getColumnIndexOrThrow("day5"));
            String day6 = cursor.getString(cursor.getColumnIndexOrThrow("day6"));
            String day7 = cursor.getString(cursor.getColumnIndexOrThrow("day7"));

            daysList.add(day1);
            daysList.add(day2);
            daysList.add(day3);
            daysList.add(day4);
            daysList.add(day5);
            daysList.add(day6);
            daysList.add(day7);

        }
        if (cursor != null) {
            cursor.close();
        }
        return daysList;
    }

}