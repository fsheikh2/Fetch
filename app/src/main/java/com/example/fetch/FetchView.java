package com.example.fetch;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FetchView {
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
}
