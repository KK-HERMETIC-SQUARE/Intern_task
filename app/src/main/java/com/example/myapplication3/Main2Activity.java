package com.example.myapplication3;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {
    private TextView stopka;
    private ImageButton imageButton;
    private ImageButton imageButton2;
    private static int REQUEST_CODE=1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Recipe");
        //download user name and change log in footer value
        stopka = findViewById(R.id.stopka);
        stopka.setBackgroundColor(Color.argb(0, 201, 189, 188));
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        if(str!=null){
        stopka.setText("Logged as: "+str);}
        else{
            stopka.setText("Log in first!");}
        //additional permissions
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, REQUEST_CODE);



        //add btn activity2
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //image1buttonimage with pizza
        imageButton=(ImageButton) findViewById(R.id.image_view);
        imageButton2=(ImageButton) findViewById(R.id.image_view2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);
                alert.setMessage("Do you want to save the picture?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //yes
                        String Name = String.format("",System.currentTimeMillis());
                        try{downloadByDownloadManager("http://mooduplabs.com/test/pizza3.jpg", Name+".jpg");} catch (Exception e) {
                            e.printStackTrace();
                            Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
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
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);
                alert.setMessage("Do you want to save the picture?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //yes
                        String Name = String.format("",System.currentTimeMillis());
                        try{downloadByDownloadManager("http://mooduplabs.com/test/pizza1.jpg", Name+".jpg");} catch (Exception e) {
                            e.printStackTrace();
                            Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
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
    //active bar btn
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home){
            //ends activity2
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }
    //download image
    public void downloadByDownloadManager(String url, String outputFileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("PIZZA!");
        request.setTitle("Pizza image!");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, outputFileName);

        Log.d("MainActivity: ", "download folder>>>>" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

}