package com.dmiiy.wayapp.HelperClasses;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class phonehelper {

    int image;
    String title;


    public phonehelper( int image, String title) {
        this.image = image;
        this.title = title;

    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}