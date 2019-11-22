package www.irlee.snesreceipts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import www.irlee.snesreceipts.Models.Category;


public class MainActivity extends AppCompatActivity {
    CategoryDbAdapter C_helper;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView editText = findViewById(R.id.txt_View);
        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        editText.setText(message);
        C_helper = new CategoryDbAdapter(this);
    }

    public void ShowAllCategories(View view) {
        try {
            context = this;
          List<Category> categories=  C_helper.getCategoryList();
            StringBuffer buffer= new StringBuffer();
            for (Category category : categories) {
                buffer.append(category.categoryName+" \n");
            }
            Message.message(context,buffer.toString());
        } catch (Exception e) {
            Log.i("Error", e.getMessage());
        } finally {

        }
    }

    public void CreateTable(View view) {
        try {
            context = this;
            C_helper.myhelper.onCreate(C_helper.myhelper.getReadableDatabase());

        } catch (Exception e) {
           Message.message(this,e.getMessage());
        }
    }
    public void UndoTable(View view){
        try{
            C_helper.myhelper.onUpgrade(C_helper.myhelper.getReadableDatabase(),1,2);
     } catch (Exception e) {
        Message.message(this,e.getMessage());
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
    public void getdata(String models) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Category> categories = null;
            categories = Arrays.asList(mapper.readValue(models, Category[].class));
            TextView tView = findViewById(R.id.txt_View);
            for (Category category : categories) {
                long id = C_helper.insertData(category);
                tView.setText(tView.getText()+Long.toString(id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


