package www.irlee.snesreceipts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

    public void RegisterUserIntoDatabase(View view) {
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        boolean allrequired = true;
        EditText in_email = findViewById(R.id.Email);
        EditText in_FirstName = findViewById(R.id.FirstName);
        EditText in_lastName = findViewById(R.id.LastName);
        EditText in_pass1 = findViewById(R.id.Password);
        EditText in_pass2 = findViewById(R.id.RePassword);

        if (in_email.getText().toString().isEmpty()) {
            in_email.setError("Email is required!");
            allrequired = false;
        }
        if (in_FirstName.getText().toString().isEmpty()) {
            in_FirstName.setError("First Name is required!");
            allrequired = false;
        }
        if (in_lastName.getText().toString().isEmpty()) {
            in_lastName.setError("Last Name is required!");
            allrequired = false;
        }
        if (in_pass1.getText().toString().isEmpty()) {
            in_pass1.setError("Password is required!");
            allrequired = false;
        }
        if (in_pass2.getText().toString().isEmpty()) {
            in_pass2.setError("Re Password is required!");
            allrequired = false;
        }
        if (in_pass1.getText().toString().equals(in_pass2.getText().toString())) {
            in_pass2.setError("Password Miss match");
            allrequired = false;
        }
        if (allrequired) {
            User user = new User();
            user.UserName = in_email.getText().toString();
            user.UserEmail = in_email.getText().toString();
            user.FirstName = in_FirstName.getText().toString();
            user.LastName = in_lastName.getText().toString();
            user.Password = in_pass1.getText().toString();
            user.UserPicture = "null";
            user.CreatedTime = dateFormat.format(currentTime);
            user.UpdatedTime = dateFormat.format(currentTime);
            long id = userDbAdapter.insertData(user);
            if (id != 0) {
                SaveUserIntoServer(user);
                Message.message(this, "Successfully registered please login");
                finish();
            }
        }
    }

    public void CreateUserTable(View view) {
        userDbAdapter.myhelper.onCreate(userDbAdapter.myhelper.getReadableDatabase());
    }

    public void getUserInfo(String UserName, String Password) {
        User user = userDbAdapter.getUserInfo(UserName, Password);
        Message.message(this, user.UserEmail + user.FirstName);
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
                    conn.setRequestProperty("Accept", "application/json");
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
                    Log.i("MSG", conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void CheckuserInfo(String UserName) {

        try {

            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://snes.irlee.net/SNES/GetUserInfo?username=" + UserName;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            User user = gson.fromJson(response, User.class);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(stringRequest);


        } catch (Exception e) {
            Log.i("Error", e.getMessage());
        } finally {
        }
    }

}
