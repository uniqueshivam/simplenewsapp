package com.example.shivamgupta.newsapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shivam gupta on 03-10-2017.
 */

public class customadapter extends ArrayAdapter<breakingresult> {



    public customadapter(Activity context, ArrayList<breakingresult> breakingresults) {
        super(context,0, breakingresults);
    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View news =convertView;
        if(news ==null)
        {
            news = LayoutInflater.from(getContext()).inflate(R.layout.customadapterxml,parent,false);
        }
    breakingresult currentresult = getItem(position);
        TextView author = (TextView) news.findViewById(R.id.author);
        TextView title =(TextView) news.findViewById(R.id.titlenews);
        TextView description =(TextView) news.findViewById(R.id.description);

        author.setText("Source- "+currentresult.getAuthor());
        title.setText("News Title : "+currentresult.getTittle());
        description.setText(currentresult.getDescription());
        TextView url =(TextView) news.findViewById(R.id.url);
        url.setText(currentresult.getUrl());
        ImageView urlimage = (ImageView) news.findViewById(R.id.imagetourl);
       // Picasso.with(getContext()).load(currentresult.getImageurl()).into(urlimage);//I have loaded picasso dependencies in gradel(module:app)
        Picasso.with(getContext()).load(currentresult.getImageurl()).placeholder(R.drawable.abc).into(urlimage);




        return  news;



    }
}
