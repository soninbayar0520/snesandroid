package www.irlee.snesreceipts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import www.irlee.snesreceipts.Models.Category;
import www.irlee.snesreceipts.Models.User;

public class LoginActivity extends AppCompatActivity {
    UserDbAdapter userDbAdapter;
    public static final String EXTRA_MESSAGE = "www.irlee.snesreceipts.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("logged", MODE_PRIVATE);
        setContentView(R.layout.activity_login);
        userDbAdapter = new UserDbAdapter(this);

        Button loginBtn = findViewById(R.id.LoginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.Txt_UserName);
                EditText password = (EditText) findViewById(R.id.Txt_Password);
                Boolean allRequired = true;
                if (username.getText().toString().isEmpty()) {
                    username.setError("username is required!");
                    allRequired = false;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("password is required!");
                    allRequired = false;
                }
                SharedPreferences sp;
                sp = getSharedPreferences("name", MODE_PRIVATE);
                int result = userDbAdapter.CheckUserNamePassword(username.getText().toString(), password.getText().toString());
                if (result == 1 && allRequired) {
                    gotomain(username.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Password Wrong!!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    public void GetUserInfo(String userEmail) {
        try {

            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://snes.irlee.net/SNES/GetUserInfo?username=" + userEmail;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            InserUserData(response);
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

    public void InserUserData(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = null;
            user = mapper.readValue(response, User.class);
          //  TextView tView = findViewById(R.id.txt_View);
            long id = userDbAdapter.insertData(user);
            Message.message(getApplicationContext(),""+id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CheckuserInfo(String UserName) {

        try {
            final String result = "";
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://snes.irlee.net/SNES/GetUserInfo?username=aburaham@gmail.com";// + UserName;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // result = response;
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(stringRequest);
            //  return user;

        } catch (Exception e) {
            Log.i("Error", e.getMessage());
        } finally {
            //  return null;
        }
    }


    public void sendMessage(View view) {
        EditText Username = findViewById(R.id.Txt_UserName);
        String value = Username.getText().toString();
        Toast.makeText(getApplicationContext(), "HI " + value, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "TEST DATA");
        startActivity(intent);
    }

    public void gotomain(String name) {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }


    public void gotoRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        intent.putExtra(EXTRA_MESSAGE, "TEST DATA");
        startActivity(intent);
    }
}
