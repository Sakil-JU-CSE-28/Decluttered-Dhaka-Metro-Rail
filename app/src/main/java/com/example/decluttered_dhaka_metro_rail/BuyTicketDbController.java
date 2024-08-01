package com.example.decluttered_dhaka_metro_rail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BuyTicketDbController extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ticket_database";
    private static final String TABLE_NAME = "tickets";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TICKET_TYPE = "ticket_type";
    private static final String COLUMN_TICKET_QUANTITY = "ticket_quantity";
    private static final String COLUMN_STARTING_STATION = "starting_station";
    private static final String COLUMN_ENDING_STATION = "ending_station";
    private static final String COLUMN_USER = "user";
    private static final int DATABASE_VERSION = 2;

    public BuyTicketDbController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TICKET_TYPE + " TEXT, "
                + COLUMN_TICKET_QUANTITY + " INTEGER, "
                + COLUMN_STARTING_STATION + " TEXT, "
                + COLUMN_ENDING_STATION + " TEXT, "
                + COLUMN_USER + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addTicket(String ticketType, int ticketQuantity, String startingStation, String endingStation, String user) {
        SQLiteDatabase db = null;
        long result = -1;
        try {
            db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_TICKET_TYPE, ticketType);
            contentValues.put(COLUMN_TICKET_QUANTITY, ticketQuantity);
            contentValues.put(COLUMN_STARTING_STATION, startingStation);
            contentValues.put(COLUMN_ENDING_STATION, endingStation);
            contentValues.put(COLUMN_USER, user);

            result = db.insert(TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace(); // Log any exception for debugging
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return result;
    }
    public List<Ticket> getTicketsByUser(String user) {
        List<Ticket> tickets = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USER + " = ?";
            cursor = db.rawQuery(query, new String[]{user});

            if (cursor.moveToFirst()) {
                do {
                    // Create a Ticket object from the cursor
                    Ticket ticket = new Ticket(
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TICKET_TYPE)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TICKET_QUANTITY)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STARTING_STATION)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ENDING_STATION)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER))
                    );
                    tickets.add(ticket);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log any exception for debugging
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return tickets;
    }

}
