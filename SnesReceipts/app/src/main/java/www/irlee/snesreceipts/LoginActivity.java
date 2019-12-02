package www.irlee.snesreceipts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import www.irlee.snesreceipts.Models.User;


public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "www.irlee.snesreceipts.message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("logged",MODE_PRIVATE);
        setContentView(R.layout.activity_login);
        Button btn =findViewById(R.id.LoginBtn);
        Button RgstrBtn = findViewById(R.id.btn_register);


        //if(!sp.getBoolean("logged",false )){
          // String passval= sp.getString("name", "john");
            //Log.i("SNES",passval);
            //gotoRegister(passval);
        //}else{
        //}

    }



    public void login(View view){
        EditText username = (EditText)findViewById(R.id.Txt_UserName);
        EditText password = (EditText)findViewById(R.id.Txt_Password);
        SharedPreferences sp;
        sp = getSharedPreferences("name",MODE_PRIVATE);
        if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
            Toast.makeText(getApplicationContext(),"Logging..", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "TEST DATA");
            sp.edit().putBoolean("logged",true).apply();
            sp.edit().putString("name", username.getText().toString()).apply();
            Log.i("SNES","logged in info");
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Password Wrong!!", Toast.LENGTH_LONG).show();
        }
    }


        public void sendMessage(View view) {
            EditText Username= findViewById(R.id.Txt_UserName);
            String value =Username.getText().toString();
            Toast.makeText(getApplicationContext(),"HI "+value, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "TEST DATA");
            startActivity(intent);
        }

        public void gotomain(String name){
            Toast.makeText(getApplicationContext(),"Welcome back "+name, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "TEST DATA");
            startActivity(intent);
        }
    public void gotoRegister(View view){

        Intent intent = new Intent(this, Register.class);
        intent.putExtra(EXTRA_MESSAGE, "TEST DATA");
        startActivity(intent);
    }


}
