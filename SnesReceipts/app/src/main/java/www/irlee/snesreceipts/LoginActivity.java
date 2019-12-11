package www.irlee.snesreceipts;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class LoginActivity extends AppCompatActivity {
    UserDbAdapter userDbAdapter;
    public static final String EXTRA_MESSAGE = "www.irlee.snesreceipts.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("logged", MODE_PRIVATE);
        setContentView(R.layout.activity_login);
        userDbAdapter = new UserDbAdapter(this);
    }

    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.Txt_UserName);
        EditText password = (EditText) findViewById(R.id.Txt_Password);
        Boolean allRequired = true;
        if (username.getText().toString().isEmpty()) {
            username.setError("Email is required!");
            allRequired = false;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Email is required!");
            allRequired = false;
        }
        SharedPreferences sp;
        sp = getSharedPreferences("name", MODE_PRIVATE);
        int result = userDbAdapter.CheckUserNamePassword(username.getText().toString(), password.getText().toString());
        if (result == 1 && allRequired) {
            Toast.makeText(getApplicationContext(), "Logging..", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "TEST DATA");
            sp.edit().putBoolean("logged", true).apply();
            sp.edit().putString("name", username.getText().toString()).apply();
            Log.i("SNES", "logged in info");
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Password Wrong!!", Toast.LENGTH_LONG).show();
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
        Toast.makeText(getApplicationContext(), "Welcome back " + name, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "TEST DATA");
        startActivity(intent);
    }
    public void gotoRegister(View view) {

        Intent intent = new Intent(this, Register.class);
        intent.putExtra(EXTRA_MESSAGE, "TEST DATA");
        startActivity(intent);
    }
}
