
package com.example.mohamed.finalmovieapp.Movie_Package.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;


import com.example.mohamed.finalmovieapp.My_Movie_Listener;
import com.example.android.finalmovieapp.R;
import com.example.mohamed.finalmovieapp.Movie_Model.MovieSchema;
import com.example.mohamed.finalmovieapp.Movie_Package.Fragment.DetailsFragment;
import com.example.mohamed.finalmovieapp.Movie_Package.Fragment.MainFragment;
import com.example.mohamed.finalmovieapp.Movie_Package.StaticVar;

/**
 * Created by mohamed yahia on 13/09/2016.
 */

public class MainActivity extends AppCompatActivity implements My_Movie_Listener {

    Boolean mTowPan;
    StaticVar staticVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        staticVar =new StaticVar();
        FrameLayout flpanel2= (FrameLayout) findViewById(R.id.f_panel_2);
        if (null==flpanel2) {mTowPan=false;} else {mTowPan=true;}


        if (null==savedInstanceState)
        {MainFragment mainFragment=new MainFragment();
            mainFragment.SetMovieListener(this);


           getSupportFragmentManager().beginTransaction().replace(R.id.f_panel_1, mainFragment).commit();
        }
    }

    @Override
    public void Set_SelectedMovie(MovieSchema movieDtails) {

        //Single pan Ui
        if(mTowPan) //Case Tow pan Ui
        {

            DetailsFragment dFragment=new DetailsFragment();
            Bundle extra=new Bundle();
         extra.putSerializable(staticVar.Extra, movieDtails);
            dFragment.setArguments(extra);
            getSupportFragmentManager().beginTransaction().replace(R.id.f_panel_2,dFragment ).commit();

        }
        else { //Case One  pan Ui
            Intent i = new Intent(this, com.example.mohamed.finalmovieapp.Movie_Package.Activity.DetailsActivity.class);
            i.putExtra(staticVar.Extra, movieDtails);
            startActivity(i);

        }

    }
}
