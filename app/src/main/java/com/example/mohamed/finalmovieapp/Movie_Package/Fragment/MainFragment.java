
package com.example.mohamed.finalmovieapp.Movie_Package.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.mohamed.finalmovieapp.Movie_Fetch.FetchingMovies;
import com.example.mohamed.finalmovieapp.Movie_Fetch.FetchingMoviesListener;
import com.example.mohamed.finalmovieapp.My_Movie_Listener;
import com.example.android.finalmovieapp.R;
import com.example.mohamed.finalmovieapp.Movie_Model.MovieSchema;
import com.example.mohamed.finalmovieapp.Movie_Base.TopRatedMovies;
import com.example.mohamed.finalmovieapp.Movie_Base.FavoriteMovies;
import com.example.mohamed.finalmovieapp.Movie_Package.StaticVar;
import com.example.mohamed.finalmovieapp.Movie_Adapter.MyGridViewAdapt;

import java.util.ArrayList;
/**
 * Created by Mohamed Yahia on 13/09/2016.
 */
public class MainFragment extends Fragment {
    private FetchingMovies fetchingMovies;
    private MyGridViewAdapt adapter;
    private SwipeRefreshLayout Swiper;
    private GridView gridView;
    My_Movie_Listener mListener;
    StaticVar staticVar;
    ArrayList<MovieSchema> arrayList;

    public ArrayList<MovieSchema> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<MovieSchema> arrayList) {
        this.arrayList = arrayList;
    }

    //------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        setHasOptionsMenu(true);


        gridView= (GridView) rootView.findViewById(R.id.gridView);
        staticVar =new StaticVar();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieSchema movieSchema = (MovieSchema) adapter.getItem(position);

                mListener.Set_SelectedMovie(movieSchema);
                /*
                Intent i =new Intent(getActivity(),DetailsActivity.class);
                i. putExtra(staticVar.Extra,movieSchema);
                startActivity(i);*/



            }
        });



        //this Methode get called when ever ther
        // user swipe the screen
        //Refreshing the main activity and load the popular movie
        // list.............

        Swiper= (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        Swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SortMyMoviesList(staticVar.Popular);
                Swiper.setRefreshing(false);
            }
        });

        return rootView;
    }

    //this Methode called when ther  need to change sort (Popular or Top Rated)......................
         private void SortMyMoviesList(String SortType) {
           fetchingMovies =new FetchingMovies();
        fetchingMovies.execute(SortType);
        fetchingMovies.setTaskListener(new FetchingMoviesListener() {
            @Override
            public void OnTaskFinish(ArrayList<MovieSchema> arrayList) {
                setArrayList(arrayList);
                if (arrayList.size()<1)
                {
                    staticVar.ToastMessage(getActivity());
                }
                else
                {

                adapter=new MyGridViewAdapt(getActivity(),arrayList);
                gridView.setAdapter(adapter);
                    SaveData();

            }}
        });
    }

    public void SaveData()
    {
        for (int i=0;i<getArrayList().size();i++)
        {
            TopRatedMovies TopRatedMovies = new TopRatedMovies(getActivity());
            TopRatedMovies.openData();
            try {
                if (getArrayList().get(i).id=="206647")
                {
                    continue;
                }
                else{ TopRatedMovies.CasheData(getArrayList().get(i));}

            }
            catch (Exception r)
            {

            }
            TopRatedMovies.close();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        ShowCacheData();

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.menu_item_share)
        {
            SortMyMoviesList(staticVar.Top_Rated);

        }
        else
        if (id==R.id.menu_popular)
        {
            SortMyMoviesList(StaticVar.Popular);
        }
        else
        if (id==R.id.menu_item_favourite)
        {
            Favorite(); // call favourite function to get favourite movie from database
        }
        return super.onOptionsItemSelected(item);
    }

    // This Methode Load favoute Movie from dataBase.................

    private void  Favorite()
    {
        FavoriteMovies dataBaseController=new FavoriteMovies(getActivity());
        dataBaseController.OpenData();
        ArrayList<MovieSchema> movieSchemaArrayList=new ArrayList<>();
        movieSchemaArrayList=dataBaseController.getData();
        if(movieSchemaArrayList.size()<1)
        {
            Toast.makeText(getActivity(),staticVar.Favourite_Message, Toast.LENGTH_SHORT).show();
        }
        else {
            adapter = new MyGridViewAdapt(getActivity(), movieSchemaArrayList);
            gridView.setAdapter(adapter);
            dataBaseController.Close();
        }
    }
    private void ShowCacheData()
    {
        TopRatedMovies topRatedMovies =new TopRatedMovies(getActivity());
        topRatedMovies.openData();
        ArrayList<MovieSchema>array=new ArrayList<>();
        array= topRatedMovies.getchashedData();
        adapter=new MyGridViewAdapt(getActivity(),array);
        gridView.setAdapter(adapter);
        topRatedMovies.close();
    }

    public void SetMovieListener(My_Movie_Listener myMovieListener) {
        mListener= myMovieListener;
    }
}
