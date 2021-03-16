package com.example.fetch;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Comparator;
import java.util.List;

/**
 * Models JSON data returned from URL endpoint.
 * Knows how to compare itself based on the three main properties in the JSON: id, list id, and name.
 * Can also compare based on both list id and name, with higher priority given to list id.
 **/

public class FetchView implements Comparable<FetchView>{
    int mID;
    int mListID;
    String mName;

    public FetchView(int pID, int pListID, String pName ){
        mID = pID;
        mListID = pListID;
        mName = pName;
    }

    public FetchView(String id, String listID, String name) {
        mID = Integer.parseInt(id);
        mListID = Integer.parseInt(listID);
        mName = name;
    }

    public int getID() {
        return mID;
    }

    public int getListID() {
        return mListID;
    }

    public String getName(){
        return mName;
    }

    @Override
    public int compareTo(FetchView v) {
        return Comparators.LISTID.compare(this, v);
    }

    public static class Comparators{
        public static final Comparator<FetchView> ID = (FetchView v1, FetchView v2) -> Integer.compare(v1.mID, v2.mID);

        public static final Comparator<FetchView> LISTID = (FetchView v1, FetchView v2) -> Integer.compare(v1.mListID, v2.mListID);

        public static final Comparator<FetchView> NAME = (FetchView v1, FetchView v2) -> {
          int name1 = Integer.parseInt(v1.mName.substring(5));
          int name2 = Integer.parseInt(v2.mName.substring(5));
          return Integer.compare(name1, name2);
        };

        public static final Comparator<FetchView> NAMEANDLISTID = (FetchView v1, FetchView v2) ->{

            if(v1.mListID == v2.mListID) // if they're equal then comparison comes down to name
                return Comparators.NAME.compare(v1, v2);
            else
                return Comparators.LISTID.compare(v1, v2); // otherwise give priority to higher list id
        };
    }
}
