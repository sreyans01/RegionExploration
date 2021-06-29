package com.app.regionexploration.presenter;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class GetRegionData extends AsyncTask<String,Void,String> {
    public RegionDataCallback getRegionCallback;

    public GetRegionData(RegionDataCallback getRegionCallback){
        this.getRegionCallback = getRegionCallback;
    }

    @Override
    protected String doInBackground(String... urls) {

        Log.i("Website Content","Started");
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection)url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while(data != -1){
                char current = (char)data;
                result += current;
                data = reader.read();

            }
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("Website Content","Error");
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if(result==null) {
            return;
        }
        try {
            JSONArray jsonArray;
            jsonArray = new JSONArray(result);
            getRegionCallback.OnGettingRegionData(jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.i("Website content",result);
    }
}
