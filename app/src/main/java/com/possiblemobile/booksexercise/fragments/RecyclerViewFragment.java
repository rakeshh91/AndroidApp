package com.possiblemobile.booksexercise.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.possiblemobile.booksexercise.R;
import com.possiblemobile.booksexercise.Util.APIUtil;
import com.possiblemobile.booksexercise.adapter.BookRecyclerAdapter;
import com.possiblemobile.booksexercise.entity.Book;
import com.possiblemobile.booksexercise.entity.ConnectionDetector;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment implements Callback<List<Book>>{

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BookRecyclerAdapter bookRecyclerAdapter;
    private TextView errorText;
    private RelativeLayout progressLayout;
    private ConnectionDetector connectionDetector;


    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    /* Returns instance of this fragment */
    public static RecyclerViewFragment newInstance() {
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        return recyclerViewFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_recycler_view, container, false);
        connectionDetector = new ConnectionDetector(getActivity());
        errorText = (TextView)rootView.findViewById(R.id.errorText);
        progressLayout = (RelativeLayout) rootView.findViewById(R.id.progressLayout);
        progressLayout.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        customProgressDialog();
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setVisibility(View.GONE);
        return rootView;
    }

    /* Start progress bar and fetch school list from the API */
    public void customProgressDialog(){
        APIUtil.getBooks(this);
        progressLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
        progressLayout.setVisibility(View.GONE);
        if(response!=null && response.body()!=null && !response.body().isEmpty()) {
            errorText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            APIUtil.setBookListArray(response.body());
            bookRecyclerAdapter = new BookRecyclerAdapter(getActivity(), APIUtil.getBookListArray());
            recyclerView.setAdapter(bookRecyclerAdapter);
        }else{
            recyclerView.setVisibility(View.GONE);
            errorText.setText(getString(R.string.no_response));
            errorText.setBackgroundColor(getResources().getColor(R.color.errorBackground,null));
            errorText.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onFailure(Call<List<Book>> call, Throwable t) {
        progressLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        if(connectionDetector.isNetworkConnectedPP()) {
            errorText.setBackgroundColor(getResources().getColor(R.color.errorBackground, null));
            errorText.setText(getString(R.string.error_api_response));
            errorText.setVisibility(View.VISIBLE);
        }else{
            errorText.setBackgroundColor(getResources().getColor(R.color.errorInternet, null));
            errorText.setText(getString(R.string.no_network));
            errorText.setVisibility(View.VISIBLE);
        }
    }
}
