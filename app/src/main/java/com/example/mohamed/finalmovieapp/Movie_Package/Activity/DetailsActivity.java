package com.example.mohamed.finalmovieapp.Movie_Package.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.finalmovieapp.R;
import com.example.mohamed.finalmovieapp.Movie_Package.Fragment.DetailsFragment;

/**
 * Created by Mohamed yahia on 12/09/2016.
 */
public class DetailsActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_details);
        if (null==savedInstanceState)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.Detalis_frame_layout, new DetailsFragment()).commit();
        }
    }
}
