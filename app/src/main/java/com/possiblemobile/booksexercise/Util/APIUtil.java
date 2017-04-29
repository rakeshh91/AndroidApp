package com.possiblemobile.booksexercise.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.possiblemobile.booksexercise.entity.Book;
import com.possiblemobile.booksexercise.interfaces.PPAPIInterface;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;





public class APIUtil {

    /*Keep only one copy of the school List by making it static so that the data is consistent throughout the app*/
    private static List<Book> bookList = new ArrayList<Book>();

    public static List<Book> getBookListArray(){
        return bookList;
    }

    /* Sort the list of schools and store it to an array */
    public static void setBookListArray(List<Book> list){
        bookList = list;
    }

    /*Gson is a Java library that can be used to convert Java Objects into their JSON representation and vice versa.*/
    private static Gson gson = new GsonBuilder()
            .create();

    /*The Retrofit class generates an implementation of the GitHubService interface.*/
    /*Define base url for your API using retrofit*/
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://de-coding-test.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    //create a function for creation of service if you want to access creating the service from any other class
    private static PPAPIInterface service = retrofit.create(PPAPIInterface.class);


    public static void getBooks(Callback<List<Book>> callback){
        Call<List<Book>> apiResponse = service.getBooksAPI();

        apiResponse.enqueue(callback);
    }



}


