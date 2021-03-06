package comp3710.gzh0020.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "note.db";
    public static final String TABLE_NAME = "Note";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TEXT_IN";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 4);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TEXT_IN varchar(250))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean createNote(String text_in){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, text_in);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) { return false; }
        return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return result;
    }
}
