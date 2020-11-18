package com.androidapp.cookbook.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidapp.cookbook.R;
import com.androidapp.cookbook.view.recipe.CreateRecipeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    public final static int RC_CREATE_RECIPE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab_add_recipe);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreateRecipeActivity.class);
            startActivityForResult(intent, RC_CREATE_RECIPE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        this.handleResult(requestCode, resultCode, data);
    }

    private void handleResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RC_CREATE_RECIPE) {
            if (resultCode == RESULT_OK) {
                Log.d("Result", data.getStringExtra("result"));
            } else if (resultCode == RESULT_CANCELED){
                Log.d("Result", "User cancel the creation");
            }
        }
    }
}