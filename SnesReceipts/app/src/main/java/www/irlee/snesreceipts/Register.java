package www.irlee.snesreceipts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import www.irlee.snesreceipts.Models.User;

public class Register extends AppCompatActivity {
    UserDbAdapter userDbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userDbAdapter = new UserDbAdapter(this);

    }
    public void RegisterUser(View view){
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        User user = new User();
        user.UserName="Batzaya";
        user.UserEmail="Zaya890609@gmail.com";
        user.FirstName="Batzaya";
        user.LastName="Zorigtbaatar";
        user.Password="qwerty1234";
        user.UserPicture="null";
        user.CreatedTime=dateFormat.format(currentTime);
        user.UpdatedTime=dateFormat.format(currentTime);
        long id= userDbAdapter.insertData(user);
        Message.message(this,Long.toString(id));
    }

    public void CreateUserTable(View view){
        userDbAdapter.myhelper.onCreate(userDbAdapter.myhelper.getReadableDatabase());
    }

    public void getUserInfo(View view){
      User user=  userDbAdapter.getUserInfo("Zaya8906@gmail.com");
      Message.message(this, user.UserEmail+user.FirstName);
    }
}
