package www.irlee.snesreceipts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn =findViewById(R.id.LoginBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Username= findViewById(R.id.Txt_UserName);
                String value =Username.getText().toString();
                Toast.makeText(getApplicationContext(),"HI "+value, Toast.LENGTH_LONG).show();
            }
        });
    }
}
