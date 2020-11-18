package com.androidapp.cookbook.view.recipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidapp.cookbook.R;

public class RecipeIngredientFragment extends Fragment {

    public RecipeIngredientFragment() {}

    public static RecipeIngredientFragment newInstance() {
        return new RecipeIngredientFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_ingredient, container, false);

        return view;
    }
}