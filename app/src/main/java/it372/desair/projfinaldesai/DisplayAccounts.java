package it372.desair.projfinaldesai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
/*
Ronak Desai
Final Project
Submitted 6/8/2021
 */
public class DisplayAccounts extends AppCompatActivity {
    private String output;
    private String firstName;
    private String lastName;
    private String highSchool;
    private String gpa;
    private String program;
    private String major;
    private String honors;
    private String email;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        output = getString(R.string.blank);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_accounts);
        //gets the intent from the main activity
        SQLiteOpenHelper dbh = new AccountsDBHelper(this);
        try {
            // alows the database to be read
            SQLiteDatabase db = dbh.getReadableDatabase();
            //cursor to move through each record in the database
            cursor = db.query(getString(R.string.accountsTable),
                    new String[] {getString(R.string.firstNameValue),getString(R.string.lastNameValue),getString(R.string.highSchoolValue),getString(R.string.gpaValue),
                            getString(R.string.programValue),getString(R.string.majorValue),getString(R.string.honorsValue),getString(R.string.emailValue)},
                    null, null, null,null,null);
            //sets cursor to first record in database
            cursor.moveToFirst();
            // adds database record to output except for last one
            while (!cursor.isLast())
            {
               firstName = cursor.getString(0);
               lastName = cursor.getString(1);
               highSchool = cursor.getString(2);
               gpa = cursor.getString(3);
               program = cursor.getString(4);
               major = cursor.getString(5);
               honors = cursor.getString(6);
               email = cursor.getString(7);
               output += String.format(getString(R.string.dbOutputStringFormat),
                       firstName,lastName,highSchool,gpa,program,major,honors,email);
                cursor.moveToNext();
           }
            //gets last record from database
            firstName = cursor.getString(0);
            lastName = cursor.getString(1);
            highSchool = cursor.getString(2);
            gpa = cursor.getString(3);
            program = cursor.getString(4);
            major = cursor.getString(5);
            honors = cursor.getString(6);
            email = cursor.getString(7);
            output += String.format(getString(R.string.dbOutputStringFormat),
                    firstName,lastName,highSchool,gpa,program,major,honors,email);

        }
        // if database is not created
        catch(SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    R.string.dbNotCreated,
                    Toast.LENGTH_LONG);
            toast.show();
        }

        // adds all information from the array into the info string so it can be displayed.
        // shows output on new activity
        TextView txtDisplay = findViewById(R.id.txtDisplay);
        txtDisplay.setText(output);

    }
}