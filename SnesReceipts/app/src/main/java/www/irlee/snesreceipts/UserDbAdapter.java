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
        contentValues.put(myDbHelper.UserName, user.userName);
        contentValues.put(myDbHelper.UserEmail, user.userEmail);
        contentValues.put(myDbHelper.FirstName, user.firstName);
        contentValues.put(myDbHelper.LastName, user.lastName);
        contentValues.put(myDbHelper.Password, user.password);
        contentValues.put(myDbHelper.UserPicture, user.userPicture);
        contentValues.put(myDbHelper.CreatedTime, user.createdTime);
        contentValues.put(myDbHelper.UpdatedTime, user.updatedTime);
        long id = db.insert(myDbHelper.TABLE_USER, null, contentValues);
        return id;
    }

    public int CheckUserNamePassword(String User_Email, String Password) {
        String WhereClause = "UserName=? and Password=?";
        String[] condition = new String[]{User_Email, Password};
        try {
            if (!User_Email.equals("") && !Password.equals("")) {
                SQLiteDatabase db = myhelper.getWritableDatabase();
                String[] columns = {myDbHelper.UID, myDbHelper.UserName, myDbHelper.UserEmail, myDbHelper.FirstName, myDbHelper.LastName, myDbHelper.Password, myDbHelper.UserPicture, myDbHelper.CreatedTime, myDbHelper.UpdatedTime};
                Cursor Users = db.query(myDbHelper.TABLE_USER, columns, WhereClause, condition, null, null, null);
                int count = Users.getCount();
                return count;
            } else {
                return 0;
            }

        } catch (Exception e) {
            return 0;
        }
    }

    public User getUserInfo(String User_Email, String Password) {
        String WhereClause = "UserName=? and Password=?";
        String[] condition = new String[]{User_Email, Password};
        User user = new User();
        try {

            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {myDbHelper.UID, myDbHelper.UserName, myDbHelper.UserEmail, myDbHelper.FirstName, myDbHelper.LastName, myDbHelper.Password, myDbHelper.UserPicture, myDbHelper.CreatedTime, myDbHelper.UpdatedTime};
            Cursor Users = db.query(myDbHelper.TABLE_USER, columns, WhereClause, condition, null, null, null);
            int count = Users.getCount();
            while (Users.moveToNext()) {
                user.userName = Users.getString(Users.getColumnIndex(myDbHelper.UserName));
                user.userEmail = Users.getString(Users.getColumnIndex(myDbHelper.UserEmail));
                user.firstName = Users.getString(Users.getColumnIndex(myDbHelper.FirstName));
                user.lastName = Users.getString(Users.getColumnIndex(myDbHelper.LastName));
                user.password = Users.getString(Users.getColumnIndex(myDbHelper.Password));
                user.userPicture = Users.getString(Users.getColumnIndex(myDbHelper.UserPicture));
                user.createdTime = Users.getString(Users.getColumnIndex(myDbHelper.CreatedTime));
                user.updatedTime = Users.getString(Users.getColumnIndex(myDbHelper.UpdatedTime));
            }
            return user;
        } catch (Exception e) {

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
                Message.message(context, "table created.");
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
