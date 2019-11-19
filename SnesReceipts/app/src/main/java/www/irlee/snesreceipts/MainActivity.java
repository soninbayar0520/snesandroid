package www.irlee.snesreceipts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView editText = findViewById(R.id.txt_View);
        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        editText.setText(message);
    }

    public void getdata(String models) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Category> categories = null;
            categories = Arrays.asList(mapper.readValue(models, Category[].class));
            for (Category category : categories) {
                System.out.println(category.categoryName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GetAllCategories(View view) {
        try {

            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://snes.irlee.net/snes/GetAllCategories";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            TextView tView = findViewById(R.id.txt_View);
                            tView.setText("done");
                            getdata(response);
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


