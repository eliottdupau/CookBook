package com.androidapp.cookbook.view.recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidapp.cookbook.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class CreateRecipeActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private MaterialButton btnNext;
    private MaterialButton btnCreateRecipe;

    private FragmentManager fragmentManager;
    private int flowStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        toolbar = findViewById(R.id.toolbar);
        btnNext = findViewById(R.id.btn_next);
        btnCreateRecipe = findViewById(R.id.btn_createRecipe);

        if (savedInstanceState != null)  flowStep = savedInstanceState.getInt("flowStep");
        else flowStep = 1;

        fragmentManager = getSupportFragmentManager();

        initToolbar();

        displayActualStepFragment(flowStep);

        btnNext.setOnClickListener(view -> {
            flowStep++;
            displayActualStepFragment(flowStep);
        });

        btnCreateRecipe.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "Recipe Created");
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });
    }

    private void initToolbar() {
        setToolbarTitle(getString(R.string.main_data));
        initNavigationListener();
        initMenuItemListener();
    }

    private void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    private void initNavigationListener() {
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void initMenuItemListener() {
        toolbar.setOnMenuItemClickListener(menuItem -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.alert_dialog_title_cancel))
                    .setPositiveButton(getString(R.string.yes), (dialogInterface, i) -> {
                        setResult(Activity.RESULT_CANCELED);
                        finish();
                    })
                    .setNegativeButton(getString(R.string.no), (dialogInterface, i) -> {})
                    .show();

            return true;
        });
    }

    private void displayActualStepFragment(int flowStep) {
        switch (flowStep) {
            case 1:
                displayFragment(RecipeDataFragment.newInstance());
                setToolbarTitle(getString(R.string.main_data));
                break;
            case 2:
                displayFragment(RecipeIngredientFragment.newInstance());
                setToolbarTitle(getString(R.string.add_ingredients));
                break;
            case 3:
                displayFragment(RecipeStepFragment.newInstance());
                setToolbarTitle(getString(R.string.add_step));
                break;
            case 4:
                displayFragment(RecipeFragment.newInstance());
                setToolbarTitle(null);
                btnNext.setVisibility(View.GONE);
                btnCreateRecipe.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void displayFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (flowStep == 1) finish();
        else {
            if (flowStep == 4) {
                btnNext.setVisibility(View.VISIBLE);
                btnCreateRecipe.setVisibility(View.GONE);
             }
            flowStep--;
            displayActualStepFragment(flowStep);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("flowStep", flowStep);

        super.onSaveInstanceState(outState);
    }
}