package www.irlee.snesreceipts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import www.irlee.snesreceipts.Models.Category;
import www.irlee.snesreceipts.Message;
import www.irlee.snesreceipts.Models.User;


public class UserDbAdapter {

    myDbHelper myhelper;

    public UserDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long insertData(User user) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.UserName, user.UserName);
        contentValues.put(myDbHelper.UserEmail, user.UserEmail);
        contentValues.put(myDbHelper.FirstName, user.FirstName);
        contentValues.put(myDbHelper.LastName, user.LastName);
        contentValues.put(myDbHelper.Password, user.Password);
        contentValues.put(myDbHelper.UserPicture, user.UserPicture);
        contentValues.put(myDbHelper.CreatedTime, user.CreatedTime);
        contentValues.put(myDbHelper.UpdatedTime, user.UpdatedTime);
        long id = db.insert(myDbHelper.TABLE_USER, null, contentValues);

        return id;
    }

    public User getUserInfo(String User_Email) {
        String Where="UserEmail =";
        String[] condition= new String[]{User_Email};
        User user = new User();
        try {

            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {myDbHelper.UID, myDbHelper.UserName, myDbHelper.UserEmail, myDbHelper.FirstName, myDbHelper.LastName, myDbHelper.Password, myDbHelper.UserPicture, myDbHelper.CreatedTime, myDbHelper.UpdatedTime};
            Cursor Users = db.query(myDbHelper.TABLE_USER, columns, null, null, null, null, null);
            while (Users.moveToNext())
            {
                user.UserName = Users.getString(Users.getColumnIndex(myDbHelper.UserName));
                user.UserEmail = Users.getString(Users.getColumnIndex(myDbHelper.UserEmail));
                user.FirstName = Users.getString(Users.getColumnIndex(myDbHelper.FirstName));
                user.LastName = Users.getString(Users.getColumnIndex(myDbHelper.LastName));
                user.Password = Users.getString(Users.getColumnIndex(myDbHelper.Password));
                user.UserPicture = Users.getString(Users.getColumnIndex(myDbHelper.UserPicture));
                user.CreatedTime = Users.getString(Users.getColumnIndex(myDbHelper.CreatedTime));
                user.UpdatedTime = Users.getString(Users.getColumnIndex(myDbHelper.UpdatedTime));
            }
            return user;
        }catch (Exception e){

        }
        return user;
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "SNES_Database";    // Database Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID = "_id";     // Column I (Primary Key)
        private static final String UserName = "UserName";    //Column II
        private static final String UserEmail = "UserEmail";    //Column II
        private static final String FirstName = "FirstName";    //Column II
        private static final String LastName = "LastName";    //Column II
        private static final String Password = "Password";    //Column II
        private static final String UserPicture = "UserPicture";    // Column III
        private static final String CreatedTime = "CreatedTime";    // Column III
        private static final String UpdatedTime = "UpdatedTime";    // Column III
        private static final String TABLE_USER = "Users";   // Table Name


        private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " " +
                "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + UserName + " VARCHAR(255) ," + UserEmail + " VARCHAR(255) ,"
                + FirstName + " VARCHAR(255) ," + LastName + " VARCHAR(255) ," + Password + " VARCHAR(255) ," + UserPicture + " VARCHAR(255) ,"
                + CreatedTime + " VARCHAR(255) ," + UpdatedTime + " VARCHAR(225),UNIQUE(UserEmail));";


        private static final String DROP_CATEGORY_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
            Message.message(context, "Started...");
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_USER_TABLE);
                Message.message(context,"table created.");
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_CATEGORY_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
    }
}
