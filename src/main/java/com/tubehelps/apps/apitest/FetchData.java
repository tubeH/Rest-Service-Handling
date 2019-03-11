package com.tubehelps.apps.apitest;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchData extends AsyncTask<Void ,Void ,Void> {

    ProgressDialog pd;
    String data ="";
    String dataParsed="";
    String singleParsed="";


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://itunes.apple.com/search?term=jack+johnson&limit=25");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream =connection.getInputStream();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            while (line != null){
                line = bufferedReader.readLine();
                data = data +line;
            }

            JSONArray jsonArray = new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++){

                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                singleParsed = "Artist Name :"+jsonObject.get("artistName") + "\n" +
                        "Collection Name :"+jsonObject.get("collectionName") + "\n" +
                        "Track Name:"+jsonObject.get("trackName") + "\n" +
                        "ArtWork Url:"+jsonObject.get("artworkUrl30") + "\n" ;

                dataParsed = dataParsed + singleParsed + "\n";


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.dataParsed);

    }
}
