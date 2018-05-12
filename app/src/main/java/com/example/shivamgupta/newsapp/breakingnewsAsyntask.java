package com.example.shivamgupta.newsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by shivam gupta on 03-10-2017.
 */

public class breakingnewsAsyntask extends AsyncTaskLoader<ArrayList<breakingresult>> {
    String urlpi;
    public breakingnewsAsyntask(Context context,String urlapi) {
        super(context);
       this.urlpi=urlapi;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<breakingresult> loadInBackground() {
        URL url = seturl(urlpi);
        String jsonResponse = null;
        try
        {
            jsonResponse=httprequest(url);
        }
        catch(IOException e)
        {

        }
       ArrayList<breakingresult> words = extractfromjson(jsonResponse);
        return words;


    }
    public URL seturl(String stringurl)
    {
        URL url =null;
        try
        {
            url = new URL(stringurl);
        }

         catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }
    public String httprequest(URL url1) throws IOException
    {
        String jsonresponse="";
        HttpsURLConnection makeurlconnection =null;
        InputStream inputStream = null;
        try{
            makeurlconnection = (HttpsURLConnection)url1.openConnection();
            makeurlconnection.setRequestMethod("GET");
            makeurlconnection.setReadTimeout(10000);
            makeurlconnection.setConnectTimeout(15000);
            makeurlconnection.connect();
            inputStream=makeurlconnection.getInputStream();
            jsonresponse = readfromstream(inputStream);


            }
            catch(Exception e)
            {

        }
        finally {
            if(makeurlconnection != null)
            {
                makeurlconnection.disconnect();
            }
            if (inputStream!=null)
            {
                inputStream.close();
            }
        }
        return jsonresponse;

    }
    public String readfromstream(InputStream inputStream) throws IOException
    {
        StringBuilder output = new StringBuilder();
        if(inputStream!=null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line =reader.readLine();
            while(line!=null)
            {
                output.append(line);
                line = reader.readLine();

            }

        }
        return output.toString();

    }
    public ArrayList<breakingresult> extractfromjson(String json)  {
        ArrayList<breakingresult> newsfeed = new ArrayList<>();
        try {

            JSONObject basejson = new JSONObject(json);
            JSONArray articlesarray = basejson.getJSONArray("articles");
            if (articlesarray.length() > 0) {
                for(int i =0;i<articlesarray.length();i++) {
                    JSONObject firstobject = articlesarray.getJSONObject(i);
                    String jsonauthor = firstobject.getString("author");
                    String jsontittle = firstobject.getString("title");
                    String jsondescription = firstobject.getString("description");
                    String jsonurl = firstobject.getString("url");
                    String jsonimageurl = firstobject.getString("urlToImage");



                    newsfeed.add(new breakingresult(jsonauthor, jsontittle, jsondescription, jsonurl, jsonimageurl));



                }
                return newsfeed;
            }
        }
        catch(JSONException e)
        {

        }



        return null;


    }

}
