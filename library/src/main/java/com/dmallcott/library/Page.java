package com.dmallcott.library;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * @author dmallcott
 */
public class Page implements Serializable {

    private String title;
    private String content;
    private @ColorRes int color;
    private @DrawableRes int icon;
    private @DrawableRes int image;

    public Page(String title, String content, int color, int icon, int image) {
        this.title = title;
        this.content = content;
        this.color = color;
        this.icon = icon;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getColor() {
        return color;
    }

    public int getIcon() {
        return icon;
    }

    public int getImage() {
        return image;
    }
}
