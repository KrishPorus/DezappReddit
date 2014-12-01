package com.example.vamshikrishna.myapplication2;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by VamshiKrishna on 11/28/2014.
 */
public class ListItems {
    private String title;
    private Drawable thumbnails;
    private String Authors;
    private String time;
    private String score;
    private String comments;

    public ListItems(String title, Drawable thumbs, String Authors, String time, String score, String comments){
        this.title = title;
        this.thumbnails = thumbs;
        this.Authors = Authors;
        this.time = time;
        this.score = score;
        this.comments = comments;
    }

    public String getTitle(){
        return title;
    }

    public Drawable getThumbnail(){
        return thumbnails;
    }

    public String getAuthor(){
        return Authors;
    }

    public String getTime(){
        return time;
    }

    public String getScore(){
        return score;
    }

    public String getComments() {return comments; }


}
