package com.example.mohamed.finalmovieapp.Movie_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.android.finalmovieapp.R;
import com.example.mohamed.finalmovieapp.Movie_Model.MovieSchema;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mohamed yahia on 13/08/2016.
 */

public class MyGridViewAdapt extends BaseAdapter {
    Context mconext;
    private final LayoutInflater Inflater;
    public ArrayList<MovieSchema> arraylist;

    public MyGridViewAdapt(Context context, ArrayList<MovieSchema> arraylist) {
        this.Inflater = LayoutInflater.from(context);
        this.arraylist = arraylist;
        this.mconext = context;
    }
    @Override
    public int getCount()
    {
        return arraylist.size();
    }
    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
       ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = Inflater.inflate(R.layout.single_movie_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_view_mainScreen);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MovieSchema movieSchema = arraylist.get(position);
        Picasso.with(mconext).load(movieSchema.getPosterPath()).into(holder.imageView);
        return convertView;
    }
}
