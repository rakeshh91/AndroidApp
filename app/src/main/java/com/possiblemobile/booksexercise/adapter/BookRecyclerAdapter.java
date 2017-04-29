package com.possiblemobile.booksexercise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.possiblemobile.booksexercise.R;
import com.possiblemobile.booksexercise.entity.Book;
import com.squareup.picasso.Picasso;
import java.util.List;



public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.BookViewHolder> {

    private List<Book> mDataSet;
    private Context mContext;

    public BookRecyclerAdapter(Context myContext, List<Book> myDataSet) {
        mContext = myContext;
        mDataSet = myDataSet;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public BookRecyclerAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType){
            default:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_card_view, parent, false);
                break;
        }

        BookViewHolder bookViewHolder = new BookViewHolder(v);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = mDataSet.get(position);
        if(book!=null) {
            holder.bindBookData(book,holder);
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        private TextView bookName;
        private TextView authorName;
        private ImageView imageView;

        public void bindBookData(Book book,BookViewHolder holder) {
                bookName.setText(book.getTitle());
            if(authorName!=null || !authorName.equals("")) {
                authorName.setText(book.getAuthor());
            }
            Picasso.with(mContext).load(book.getImageURL()).into(holder.imageView);
        }

        public BookViewHolder(View itemView) {
            super(itemView);
            bookName = (TextView) itemView.findViewById(R.id.book_title);
            authorName = (TextView) itemView.findViewById(R.id.author_name);
            imageView = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
