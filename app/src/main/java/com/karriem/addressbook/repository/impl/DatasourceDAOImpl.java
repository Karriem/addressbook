package com.karriem.addressbook.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.karriem.addressbook.domain.Contact;
import com.karriem.addressbook.repository.DBadapter;
import com.karriem.addressbook.repository.DatasourceDAO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karriem on 8/19/14.
 */
public class DatasourceDAOImpl implements DatasourceDAO {

    private SQLiteDatabase database;
    private DBadapter dbHelper;

    List<Contact> contactList = new ArrayList<Contact>();

    public DatasourceDAOImpl(Context context) {
        dbHelper = new DBadapter(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        Log.d("DAOIMPL", "CLOSE DATABASE");
        dbHelper.close();
    }

    @Override
    public void createContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(DBadapter.COLUMN_FIRST_NAME, contact.getFirstName());
        values.put(DBadapter.COLUMN_LAST_NAME, contact.getLastName());
        values.put(DBadapter.COLUMN_EMAIL_ADDRESS, contact.getEmailAddress());
        values.put(DBadapter.COLUMN_CELL_PHONE_NUMBER, contact.getCellPhoneNumber());
        values.put(DBadapter.COLUMN_HOME_ADDRESS, contact.getHomeAddress());

        try {
            open();
        } catch (SQLException e) {
            e.getErrorCode();
        }
        Log.d("DAOIMPL", "INSERTED INTO DATABASE");
        database.insert(DBadapter.TABLE_CONTACTS, null, values);

        close();
    }

    @Override
    public void deleteDB() {
        database.execSQL("DROP TABLE IF EXISTS " + DBadapter.TABLE_CONTACTS);
    }

    @Override
    public List<Contact> getContactList() {
        String selectQuery = "select * from " + DBadapter.TABLE_CONTACTS;

        try {
            open();
        } catch (SQLException e) {
            e.getErrorCode();
        }

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                final Contact contact = new Contact.Builder()
                        .firstName(cursor.getString(0))
                        .lastName(cursor.getString(1))
                        .emailAddress(cursor.getString(2))
                        .cellPhoneNumber(cursor.getString(3))
                        .homeAddress(cursor.getString(4))
                        .build();

                contactList.add(contact);

            } while (cursor.moveToNext());
        }

        close();

        return contactList;
    }

    @Override
    public String searchLastName(String value) {

        int counter = 0;
        contactList = getContactList();

        for (Contact con : contactList) {
            if (con.getLastName().equalsIgnoreCase(value)) {
                String count = String.valueOf(counter);
                return count;
            }
            else {
                counter++;
            }
        }
        System.out.println("NO Record");
        return null;
    }
}
