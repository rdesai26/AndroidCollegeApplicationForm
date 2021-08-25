package it372.desair.projfinaldesai;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*

Ronak Desai
Final Project
Submitted 6/8/2021
 */
public class AccountsDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "accounts.db";
    private static final int DB_VERSION = 1;

    public AccountsDBHelper(Context context)

    {
        super(context, DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // accounts table with the column names and column types.
        db.execSQL("create table accounts(" +
                "firstname text, lastname text, highschool text, gpa text, program text, major text, honors text, email text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {

    }

}
