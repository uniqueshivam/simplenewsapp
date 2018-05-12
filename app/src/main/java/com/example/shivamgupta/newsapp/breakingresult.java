package com.example.shivamgupta.newsapp;

/**
 * Created by shivam gupta on 02-10-2017.
 */

public class breakingresult {
    String author;
    String tittle;
    String description;
   String url;
    String imageurl;

    public breakingresult(String author,String tittle,String description,String url,String imageurl)
    {
        this.author=author;
        this.tittle=tittle;
        this.description=description;
        this.url=url;
        this.imageurl=imageurl;

    }
    public String getAuthor()
    {
        return author;

    }
    public String getTittle()
    {
        return tittle;

    }
    public String getDescription()
    {
        return description;
    }

    public String getUrl()
    {
        return url;
    }
    public String getImageurl()
    {
        return imageurl;

    }
}
