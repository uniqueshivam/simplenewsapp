package com.example.shivamgupta.newsapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class breakingnews extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<breakingresult>> {

    TextView empty ;
    ProgressBar progressBar;


    private static final String url_link = "https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=5e56bc9e6da042579f713b11150f35e0";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakingnews);
        getSupportLoaderManager().initLoader(0,null,this);
        empty =(TextView) findViewById(R.id.empty);
        progressBar =(ProgressBar)findViewById(R.id.progress);
        progressBar.setVisibility(ProgressBar.VISIBLE);



    }

    public void updateui(ArrayList<breakingresult> words) {
        customadapter obj = new customadapter(this, words);
        ListView view = (ListView) findViewById(R.id.list_view);
        view.setAdapter(obj);
        view.setEmptyView(findViewById(R.id.empty));

    }
    @Override
    public android.support.v4.content.Loader<ArrayList<breakingresult>> onCreateLoader(int id, Bundle args) {


        return new breakingnewsAsyntask(this,url_link);



    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<ArrayList<breakingresult>> loader, ArrayList<breakingresult> data) {
        progressBar.setVisibility(ProgressBar.GONE);
        updateui(data);
        empty.setText("Sorry can't load the news feeds.");




    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<ArrayList<breakingresult>> loader) {

    }

}


