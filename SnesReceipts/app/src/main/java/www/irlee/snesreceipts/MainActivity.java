package www.irlee.snesreceipts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import www.irlee.snesreceipts.Models.Category;


public class MainActivity extends AppCompatActivity {
    CategoryDbAdapter C_helper;
    private Context context;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        C_helper = new CategoryDbAdapter(this);

        Button openCamera = findViewById(R.id.BtnOpencamera);
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "www.irlee.snesreceipts.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
           // TextView tView = findViewById(R.id.txt_View);
            for (Category category : categories) {
                long id = C_helper.insertData(category);
             //   tView.setText(tView.getText()+Long.toString(id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


