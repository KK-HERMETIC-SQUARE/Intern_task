package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabFacebook,fabPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("RecipeMaster");

        fabFacebook = findViewById(R.id.fabFacebook);
        fabPizza = findViewById(R.id.fabPizza);

        fabFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });



        fabPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipe();
            }
        });
    }
    public void openRecipe(){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    public void openLogin(){
        Intent intent = new Intent(this,Main3Activity.class);
        startActivity(intent);
    }
}
