package com.dmiiy.wayapp.HelperClasses;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class phonehelper {

    int image,mark;
    String title;


    public phonehelper( int image, String title,int mark) {
        this.image = image;
        this.title = title;
        this.mark=mark;

    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getMark() {
        return mark;
    }

}