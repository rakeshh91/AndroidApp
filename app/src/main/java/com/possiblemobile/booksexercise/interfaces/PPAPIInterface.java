package com.possiblemobile.booksexercise.interfaces;

import com.possiblemobile.booksexercise.entity.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;




public interface PPAPIInterface {
    @GET("books.json")
    Call<List<Book>> getBooksAPI();

}
