package com.example.discoproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.discoproject.IdentificationActivity.user;

public class BagDataBase extends SQLiteOpenHelper {
    private static final String DATABASENAME = "bag.db";
    private static final String TABLE_RECORD = "tblbag";
    private static final int DATABASEVERSION = 1;
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_RATING = "Rating";
    private static final String COLUMN_PRICE = "Price";
    private static final String COLUMN_USERNAME = "UserName";

    private static final String[] allColumns = {COLUMN_ID, COLUMN_NAME, COLUMN_RATING, COLUMN_PRICE,COLUMN_USERNAME};

    public static ArrayList<Bag> getBags() {
        return bags;
    }

    private static final String CREATE_TABLE_BAG = "CREATE TABLE IF NOT EXISTS " +
            TABLE_RECORD + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT," +
            COLUMN_RATING + " INTEGER," +
            COLUMN_PRICE + " INTEGER," +
            COLUMN_USERNAME + " Text);";
    private static ArrayList<Bag> bags;
    private SQLiteDatabase database; // access to table

    public BagDataBase(Context context) { // Context gives access to resource libary
        super(context, DATABASENAME, null, DATABASEVERSION);
        getAllRecords();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { // sqlLiteDataBase is created in db and creates a new table with .execSQL
        sqLiteDatabase.execSQL(CREATE_TABLE_BAG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);
        onCreate(sqLiteDatabase);
    }

    public void setRecord(Bag bag) {
        Bag current = null;
        for (Bag c : bags) {
            if (c.getName().equalsIgnoreCase(bag.getName())) {
                current = c;
                break;
            }
        }
        if (current == null) {
            bags.add(bag);
            createRecord(bag);
        }
        Collections.sort(bags);

    }

    public Bag createRecord(Bag record) {
        database = getWritableDatabase(); // get access to write the database
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, record.getId());
        values.put(COLUMN_NAME, record.getName());
        values.put(COLUMN_RATING, record.getRating());
        values.put(COLUMN_PRICE, record.getPrice());
        values.put(COLUMN_USERNAME, record.getUserName());
        //long id = database.insert(TABLE_RECORD, null, values);
        database.insert(TABLE_RECORD, null, values);
        //record.setId(id);
        database.close();
        return record;
    }

    public ArrayList<Bag> getAllRecords() {
        database = getReadableDatabase(); // get access to read the database
        bags = new ArrayList<>();
        String sortOrder = COLUMN_ID + " ASC"; // sorting by id
        Cursor cursor = database.query(TABLE_RECORD, allColumns, null, null, null, null, sortOrder); // cursor points at a certain row

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String rating = cursor.getString(cursor.getColumnIndex(COLUMN_RATING));
                int price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE));
                String userName = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                Bag record = new Bag(id, name, Double.parseDouble(rating), price, userName);
                if (record.getUserName().equals(user)){
                    bags.add(record);
                }
            }
        }
        database.close();
        Collections.sort(bags);
        return bags;
    }

    public void deleteItemByRow(long id) {
        database = getWritableDatabase(); // get access to read the data
        database.delete(TABLE_RECORD, COLUMN_ID + " = " + id, null);
        database.close(); // close the database
    }

    public void updateByRow(Bag bag) {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, bag.getId());
        values.put(COLUMN_NAME, bag.getName());
        values.put(COLUMN_RATING, bag.getRating());
        values.put(COLUMN_PRICE, bag.getPrice());
        values.put(COLUMN_USERNAME, bag.getUserName());
        database.update(TABLE_RECORD, values, COLUMN_ID + "=" + bag.getId(), null);
        database.close();
    }


}
