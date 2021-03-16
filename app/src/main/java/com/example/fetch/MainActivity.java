package com.example.fetch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements AsyncResponse{
    private RecyclerView mFetchRecycler;
    private FetchViewAdapter mFetchViewAdapter;
    private JsonRetriever mJsonRetriever;
    private ArrayList<FetchView> mFVList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFetchRecycler = findViewById(R.id.fetch_recycler);
        mFetchRecycler.setLayoutManager(new LinearLayoutManager(this));

        mFVList = new ArrayList<FetchView>();

        mJsonRetriever = new JsonRetriever();
        mJsonRetriever.setDelegate(this);
        mJsonRetriever.execute("https://fetch-hiring.s3.amazonaws.com/hiring.json");
    }

    @Override
    public void processFinish(JSONArray output){
        if(output != null) {
            for (int i = 0; i < output.length(); i++) {
                try {
                    JSONObject singleFetchInfo = (JSONObject) output.get(i);


                    // If any of the methods below throw an exception due to improper formatting or if
                    // the name element is either an empty string or null we'll skip adding it to the recycler view
                    Integer tempID = singleFetchInfo.getInt("id");
                    Integer tempListID = singleFetchInfo.getInt("listId");
                    String tempName = singleFetchInfo.getString("name");
                    if(!tempName.equals("") && !tempName.equals("null")){
                        FetchView temp = new FetchView(tempID.toString(), tempListID.toString(), tempName );
                        mFVList.add(temp);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
        Collections.sort(mFVList, FetchView.Comparators.NAMEANDLISTID);
        mFetchViewAdapter = new FetchViewAdapter(mFVList, this);
        mFetchRecycler.setAdapter(mFetchViewAdapter);
    }
}