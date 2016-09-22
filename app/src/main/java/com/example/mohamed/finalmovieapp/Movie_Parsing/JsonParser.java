package com.example.mohamed.finalmovieapp.Movie_Parsing;

/**
 * Created by mohamed yahia on 21/09/2016.
 */

import com.example.mohamed.finalmovieapp.Movie_Model.MovieSchema;
import com.example.mohamed.finalmovieapp.Movie_Model.ReviewSchema;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonParser {
    public ArrayList<MovieSchema>  JsonData(String string)
            throws JSONException {


        final String MDB_MOVIE_POSTER = "poster_path";
        final String MDB_MOVIE_TITLE = "title";
        final String MDB_MOVIE_BACKDROP_PHOTO = "backdrop_path";
        final String MDB_MOVIE_DATE = "release_date";
        final String MDB_MOVIE_DESC = "overview";
        final String MDB_MOVIE_RATE = "vote_average";
        final String MDB_ID = "id";
        final String results = "results";
        String Description;
        String Title;
        String BackDropPhoto;
        String PosterPathPhoto;
        String Date;
        String Rate;
        String id;


        ArrayList<MovieSchema> MoviesList = new ArrayList<MovieSchema>();
        if (string!=null) {
            JSONObject jsonObject = new JSONObject(string);
            JSONArray movieArray = jsonObject.getJSONArray(results);

            for (int i = 0; i < movieArray.length(); i++) {

                MovieSchema movieSchema = new MovieSchema();
                JSONObject jsonObject1 = movieArray.getJSONObject(i);
                BackDropPhoto = FormatBackDropImage(jsonObject1.getString(MDB_MOVIE_BACKDROP_PHOTO));
                PosterPathPhoto = FormatPosterImage(jsonObject1.getString(MDB_MOVIE_POSTER));
                Title = jsonObject1.getString(MDB_MOVIE_TITLE);
                Date = jsonObject1.getString(MDB_MOVIE_DATE);
                Description = jsonObject1.getString(MDB_MOVIE_DESC);
                Rate = jsonObject1.getString(MDB_MOVIE_RATE);
                id = jsonObject1.getString(MDB_ID);

                //===============================================================================================
                movieSchema.setTitle(Title);
                movieSchema.setDate(Date);
                movieSchema.setDesc(Description);
                movieSchema.setRate(Rate);
                movieSchema.setId(id);
                movieSchema.setPosterPath(PosterPathPhoto);
                movieSchema.setBackDrop(BackDropPhoto);
                MoviesList.add(movieSchema);
            }


        }

        return MoviesList;
    }
    public String FormatPosterImage(String imageUrl) {
        final String BASE_URL = "http://image.tmdb.org/t/p/";
        final String IMG_SIZE = "/w342";
        return BASE_URL + IMG_SIZE + imageUrl;
    }
    public String FormatBackDropImage(String imageUrl) {
        final String BASE_URL = "http://image.tmdb.org/t/p/";
        final String IMG_SIZE = "w780/";
        return BASE_URL + IMG_SIZE + imageUrl;
    }

    public String JsonTrailer(String string)
            throws JSONException{
        try {
            //  if (string != null) {

            JSONObject jsonObject = new JSONObject(string);
            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonMoviesArray = jsonObject.optJSONArray("results");
            String code = "";
            for (int i = 0; i < jsonMoviesArray.length(); i++) {
                JSONObject jsonObject1 = jsonMoviesArray.getJSONObject(i);
                String type = jsonObject1.optString("type");

                if (type.equalsIgnoreCase("trailer"))
                    code = jsonObject1.optString("key");
                //  Log.v("ZOKA", code);
            }
            return code;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
    public ArrayList<ReviewSchema> JsonReview(String s)
            throws JSONException {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonMoviesArray = jsonObject.getJSONArray("results");

            ArrayList<ReviewSchema> List = new ArrayList<>();
            for (int i = 0; i < jsonMoviesArray.length(); i++) {
                ReviewSchema reviewSchema= new ReviewSchema();
                JSONObject jsonObject1 = jsonMoviesArray.getJSONObject(i);
                String author = jsonObject1.getString("author");

                String content = jsonObject1.getString("content");
                String url = jsonObject1.getString("url");
                reviewSchema.setAuthor(author);
                reviewSchema.setUrl(url);
                reviewSchema.setContent(content);
                List.add(reviewSchema);

            }
            return List;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
