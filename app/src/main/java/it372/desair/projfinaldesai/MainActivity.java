package it372.desair.projfinaldesai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/*
Ronak Desai
Final Project
Submitted 6/8/2021
 */
public class MainActivity extends AppCompatActivity {
    // variables for controls, and buttons form layout
    private EditText name;
    private EditText highSchool;
    private EditText email;
    private RadioButton graduate;
    private RadioButton undergraduate;
    private Spinner spnr_majors;
    private CheckBox honorsChecked;
    // default option for honors program
    private String honors = "No";
    private String program;
    private RadioGroup rdgroup;
    private TextView forms;
    // number of forms starts at 0
    private String major;
    private EditText lastName;
    private EditText gpa;
    private Cursor cursor;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editTextFirstName);
        highSchool = findViewById(R.id.editTextHighSchool);
        undergraduate = findViewById(R.id.rad_Undergraduate);
        graduate = findViewById(R.id.rad_Graduate);
        email = findViewById(R.id.editTextEmailAddress);
        spnr_majors = findViewById(R.id.spnr_Majors);
        honorsChecked = findViewById(R.id.chkHonors);
        rdgroup = findViewById(R.id.radioGroup2);
        lastName = findViewById(R.id.editTextLastName);
        gpa = findViewById(R.id.editTextGPA);

        // if the phone has a saved state
        if (savedInstanceState != null)
        {

        }
    }

    // keeps values when phone is rotated
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

    }

    // runs when the submit button is clicked on.
    public void submit(View view) {
        // gets the selected major from the spinner drop down.
        major = spnr_majors.getSelectedItem().toString();
        // sets honors equal to yes/no depending on what is checked.
        if (honorsChecked.isChecked())
        {
            honors = getString(R.string.yes);
        }
        else
        {
            honors = getString(R.string.no);
        }
        // sets program depending on what radio button is selected.
        if (graduate.isChecked())
        {
            program = getString(R.string.graduate);
        }
        else
        {
            program = getString(R.string.undergraduate);
        }

        // creates a SQLite open helper equal to the accounts database helper class
        SQLiteOpenHelper dbh = new AccountsDBHelper(this);
        try {
            // allows the database to be written to
            db = dbh.getWritableDatabase();
        }
        //shows a toast if the database is not created
        catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    getString(R.string.dbNotCreated), Toast.LENGTH_LONG);
            toast.show();
        }
        // content values object to put values into the accounts database.
        ContentValues accountValues = new ContentValues();
        // requires the first name to be filled out
        if (name.getText().toString().equals(getString(R.string.blank)))
        {
            Toast toast = Toast.makeText(this,
                    R.string.firstnamerequired, Toast.LENGTH_LONG);
            toast.show();
        }
        //puts the firstname entered into the account values object
        else
        {
            accountValues.put(getString(R.string.firstNameValue), name.getText().toString());
        }
        //requires the last name to be filled out
        if (lastName.getText().toString().equals(getString(R.string.blank)))
        {
            Toast toast = Toast.makeText(this,
                    R.string.lastnamerequired, Toast.LENGTH_LONG);
            toast.show();
        }
        // puts the lastname entered into the account values object
        else
        {
            accountValues.put(getString(R.string.lastNameValue), lastName.getText().toString());
        }
        //requires highschool to be filled out
        if (highSchool.getText().toString().equals(getString(R.string.blank)))
        {
            Toast toast = Toast.makeText(this,
                    R.string.highschoolrequired, Toast.LENGTH_LONG);
            toast.show();
        }
        //puts the highschool entered into the account values object
        else
        {
            accountValues.put(getString(R.string.highSchoolValue), highSchool.getText().toString());
        }
        //requires the gpa to be filled out
        if (gpa.getText().toString().equals(getString(R.string.blank)))
        {
            Toast toast = Toast.makeText(this,
                    R.string.gparequired, Toast.LENGTH_LONG);
            toast.show();
        }
        //puts the gpa entered into the account values object
        else
        {
            accountValues.put(getString(R.string.gpaValue), gpa.getText().toString());
        }
        //puts program major and honors into account values object
        accountValues.put(getString(R.string.programValue), program);
        accountValues.put(getString(R.string.majorValue), major);
        accountValues.put(getString(R.string.honorsValue), honors);
        //requires the email to be filled out
        if (email.getText().toString().equals(getString(R.string.blank)))
        {
            Toast toast = Toast.makeText(this,
                    R.string.emailRequired, Toast.LENGTH_LONG);
            toast.show();
        }
        //puts the email entered into account values object
        else
        {
            accountValues.put(getString(R.string.emailValue), email.getText().toString());
        }


        //inserts record into database and starts new activity if all required fields are filled out
        if (!name.getText().toString().equals(getString(R.string.blank)) && !lastName.getText().toString().equals(getString(R.string.blank)) &&
                !highSchool.getText().toString().equals(getString(R.string.blank))
        && !gpa.getText().toString().equals(getString(R.string.blank)) && !email.getText().toString().equals(getString(R.string.blank)))
        {
            db.insert(getString(R.string.accountsTable), null, accountValues);
            Intent intent = new Intent(this, DisplayAccounts.class);
            startActivity(intent);
        }
    }
    // runs  when the reset button is clicked to clear the array, empty the edit text and reset the radio buttons and checkbox.
    public void reset(View view) {
        name.setText(R.string.blank);
        lastName.setText(R.string.blank);
        highSchool.setText(R.string.blank);
        gpa.setText(R.string.blank);
        email.setText(R.string.blank);
        honorsChecked.setChecked(false);
        rdgroup.clearCheck();

    }
}