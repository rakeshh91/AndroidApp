package com.possiblemobile.booksexercise.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.possiblemobile.booksexercise.R;
import com.possiblemobile.booksexercise.fragments.MainFragment;
import com.possiblemobile.booksexercise.fragments.RecyclerViewFragment;
import com.possiblemobile.booksexercise.interfaces.HandleNavigationListener;

public class MainActivity extends AppCompatActivity implements HandleNavigationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.app_name);

        shouldDisplayHomeUp();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                shouldDisplayHomeUp();
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, MainFragment.newInstance())
                .commit();
    }

    /* Configuration of Home button on toolbar */
    public void shouldDisplayHomeUp(){
        //Enable Up button only  if there are entries in the back stack
        boolean canback = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
        getSupportActionBar().setDisplayShowHomeEnabled(canback);
    }


    @Override
    public void navigateToItemSelected(int buttonId) {
        switch (buttonId){
            case R.id.searchButton:
                fragmentInsert(RecyclerViewFragment.newInstance(),"books");
                setTitle(getString(R.string.recylerView_title));
                break;

            default:
                break;
        }
    }

    /*  Load fragment with name for adding it to stack */
    public void fragmentInsert(Fragment fragment, String name){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, fragment)
                .addToBackStack(name)
                .commit();
    }

    /*  Handle click of home button on toolbar */
    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        setTitle(getString(R.string.app_name));
        return true;
    }


}
