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


public class CategoryDbAdapter {

    myDbHelper myhelper;

    public CategoryDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(Category category)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.categoryId, category.categoryId);
        contentValues.put(myDbHelper.categoryName, category.categoryName);
        contentValues.put(myDbHelper.categoryPicture, category.categoryPicture);
        contentValues.put(myDbHelper.categoryDetails, category.categoryDetails);
        contentValues.put(myDbHelper.createdOn, category.createdOn);
        contentValues.put(myDbHelper.updatedOn, category.updatedOn);
        long id = db.insert(myDbHelper.TABLE_CATEGORY, null , contentValues);
        return id;
    }

    public List<Category> getCategoryList()
    {
        List<Category> categories = new ArrayList<>();

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.categoryId,myDbHelper.categoryName,myDbHelper.categoryPicture,myDbHelper.categoryDetails,myDbHelper.createdOn,myDbHelper.updatedOn};
        Cursor cursor =db.query(myDbHelper.TABLE_CATEGORY,columns,null,null,null,null,null);

        while (cursor.moveToNext())
        {
            Category category = new Category();
            category.categoryId =cursor.getString(cursor.getColumnIndex(myDbHelper.categoryId));
            category.categoryName =cursor.getString(cursor.getColumnIndex(myDbHelper.categoryName));
            category.categoryPicture =cursor.getString(cursor.getColumnIndex(myDbHelper.categoryPicture));
            category.categoryDetails =cursor.getString(cursor.getColumnIndex(myDbHelper.categoryDetails));
            category.createdOn =cursor.getString(cursor.getColumnIndex(myDbHelper.createdOn));
            category.updatedOn =cursor.getString(cursor.getColumnIndex(myDbHelper.updatedOn));
            categories.add(category);
        }
        return categories;
    }
    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "SNES_Database";    // Database Name
        private static final String TABLE_CATEGORY = "Categories";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String categoryId = "categoryId";    //Column II
        private static final String categoryName = "categoryName";    //Column II
        private static final String categoryPicture = "categoryPicture";    //Column II
        private static final String categoryDetails = "categoryDetails";    //Column II
        private static final String createdOn = "createdOn";    //Column II
        private static final String updatedOn= "updatedOn";    // Column III

        private static final String TABLE_USER = "Users";   // Table Name


        public static final String CREATE_CATEGORIES_TABLE = "CREATE TABLE "+TABLE_CATEGORY+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+categoryId+" VARCHAR(255) ,"+categoryName+" VARCHAR(255) ,"+categoryPicture+" VARCHAR(255) ,"+categoryDetails+" VARCHAR(255) ,"+updatedOn+" VARCHAR(255) ,"+ createdOn+" VARCHAR(225),  UNIQUE(categoryId));";

        private static final String DROP_CATEGORY_TABLE ="DROP TABLE IF EXISTS "+TABLE_CATEGORY;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
            Message.message(context,"Started...");
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_CATEGORIES_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_CATEGORY_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}
