package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

public class ShowProfileAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on contact for show its detailed profile";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View contact = view.findViewById(R.id.item_list_name); //ID de la view du contact
        // Maybe check for null
        contact.performClick();
    }
}