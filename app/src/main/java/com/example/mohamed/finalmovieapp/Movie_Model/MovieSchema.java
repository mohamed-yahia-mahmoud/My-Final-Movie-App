
package com.example.mohamed.finalmovieapp.Movie_Model;
import java.io.Serializable;

/**
 * Created by Mohamed yahia on 11/09/2016.
 */
public class MovieSchema implements Serializable {
    public String Title;
    public String Desc;
    public String PosterPath ;
    public String Date ;
    public  String BackDrop;
    public String Rate ;
    public  String id;

    public String getTitle() {
        return Title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getPosterPath()
    {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getBackDrop() {
        return BackDrop;
    }

    public void setBackDrop(String backDrop) {
        BackDrop = backDrop;
    }

    public String getRate()
    {
        return Rate;
    }

    public void setRate(String rate) {
        this.Rate = rate;
    }
}
