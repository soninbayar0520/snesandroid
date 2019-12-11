package www.irlee.snesreceipts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public void RegisterUserIntoDatabase(View view){
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        User user = new User();
        user.UserName="Soninbayar";
        user.UserEmail="soninmunkh@gmail.com";
        user.FirstName="Soninbayar";
        user.LastName="Munkhbayar";
        user.Password="qwerty1234";
        user.UserPicture="null";
        user.CreatedTime=dateFormat.format(currentTime);
        user.UpdatedTime=dateFormat.format(currentTime);
        long id= userDbAdapter.insertData(user);
        SaveUserIntoServer(user);
        Message.message(this,Long.toString(id));
    }

    public void CreateUserTable(View view){
        userDbAdapter.myhelper.onCreate(userDbAdapter.myhelper.getReadableDatabase());
    }

    public void getUserInfo(View view){
      User user=  userDbAdapter.getUserInfo("Zaya8906@gmail.com");
      Message.message(this, user.UserEmail+user.FirstName);
    }

    public void SaveUserIntoServer(final User user) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://snes.irlee.net/snes/CreateUser");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    Gson gson = new Gson();
                    String json = gson.toJson(user);

                    Log.i("JSON", json);
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(json);

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
