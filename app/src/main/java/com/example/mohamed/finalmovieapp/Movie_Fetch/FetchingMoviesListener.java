package com.example.mohamed.finalmovieapp.Movie_Fetch;

import com.example.mohamed.finalmovieapp.Movie_Model.MovieSchema;

import java.util.ArrayList;

/**
 * Created by Mohamed yahia on 10/09/2016.
 */
public interface FetchingMoviesListener {

    public void OnTaskFinish(ArrayList<MovieSchema> arrayList) ;


}
