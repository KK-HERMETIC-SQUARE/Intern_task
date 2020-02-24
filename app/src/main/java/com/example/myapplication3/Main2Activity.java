package com.example.myapplication3;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class Main2Activity extends AppCompatActivity {
    private ImageButton imageButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //add btn activity2
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //image1buttonimage
        imageButton=(ImageButton) findViewById(R.id.image_view);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);
                alert.setMessage("Do you want to save the picture?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //yes
                        Drawable drawable = getDrawable(R.drawable.pizza3);
                        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
                        File file = wrapper.getDir("Images",MODE_PRIVATE);
                        String Name = String.format(".",System.currentTimeMillis());
                        file = new File(file, Name +".jpg");
                        try{
                            OutputStream stream = null;
                            stream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                            stream.flush();
                            stream.close();
                            Toast.makeText(Main2Activity.this, "Your image waiting for you!", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }


                    }

                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no
                    }
                });
                alert.create().show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home){
            //ends activity2
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
