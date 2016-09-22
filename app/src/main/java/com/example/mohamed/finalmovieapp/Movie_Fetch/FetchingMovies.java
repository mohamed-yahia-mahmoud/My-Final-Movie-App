package com.example.mohamed.finalmovieapp.Movie_Fetch;

import android.os.AsyncTask;

import com.example.mohamed.finalmovieapp.Movie_Parsing.JsonParser;
import com.example.mohamed.finalmovieapp.Movie_Package.StaticVar;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mohamed yahia on 10/9/2016.
 */
public class FetchingMovies extends AsyncTask<String, Void, String> {
    StaticVar  staticVar =new StaticVar();

private FetchingMoviesListener fetchingMoviesListener;


        JsonParser parser = new JsonParser();

public void setTaskListener(FetchingMoviesListener fetchingMoviesListener) {
    this.fetchingMoviesListener = fetchingMoviesListener;
}
protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String forecastJsonStr = null;

        try {

   URL url = new URL(staticVar.BASE_URI+params[0]+staticVar.Fetch_S+staticVar.API_KEY);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        // Read the input stream into a String
        InputStream inputStream = urlConnection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
        // Nothing to do.
        return null;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {

        buffer.append(line + "\n");
        }

        if (buffer.length() == 0) {
        // Stream was empty.  No point in parsing.
        return null;
        }
        forecastJsonStr = buffer.toString();

        return forecastJsonStr;
        } catch (IOException e) {

        return null;
        } finally {
        if (urlConnection != null) {
        urlConnection.disconnect();
        }
        if (reader != null) {
                try {
                        reader.close();
                } catch (final IOException e) {
                }
        }
        }
        }

@Override
protected void onPostExecute(String s) {

        super.onPostExecute(s);
        try {
        parser.JsonData(s);
            fetchingMoviesListener.OnTaskFinish(parser.JsonData(s));
        }
        catch (JSONException e) {
        e.printStackTrace();
        }
        }
}
