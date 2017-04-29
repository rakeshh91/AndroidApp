package com.possiblemobile.booksexercise.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("imageURL")
    @Expose
    private String imageURL;


    @SerializedName("author")
    @Expose
    private String author;

    public String getTitle() {
        return title;
    }


    public String getImageURL() {
        return imageURL;
    }


    public String getAuthor() {
        return author;
    }

}
