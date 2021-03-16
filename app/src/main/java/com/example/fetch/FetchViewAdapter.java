package com.example.fetch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple adapter for the recycler view.
 **/
public class FetchViewAdapter extends RecyclerView.Adapter<FetchViewAdapter.FetchHolder>{

    private Context mContext;
    private ArrayList<FetchView> mFetchList;

    public FetchViewAdapter(ArrayList<FetchView> list, Context context){
        mFetchList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public FetchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.fetch_view, parent, false );
        return new FetchHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FetchViewAdapter.FetchHolder holder, int position) {
        FetchView fetchView = mFetchList.get(position);
        holder.mIdView.setText( Integer.toString( fetchView.getID() ) );
        holder.mListId.setText( Integer.toString( fetchView.getListID() ) );
        holder.mName.setText(fetchView.getName());
    }

    @Override
    public int getItemCount() {
        return mFetchList.size();
    }

    public class FetchHolder extends RecyclerView.ViewHolder{
        public TextView mIdView;
        public TextView mListId;
        public TextView mName;

        public FetchHolder(View itemView){
            super(itemView);
            mIdView = (TextView) itemView.findViewById(R.id.fetchIdView);
            mListId = (TextView) itemView.findViewById(R.id.fetchListIdView);
            mName = (TextView) itemView.findViewById(R.id.fetchNameView);
        }
    }
}
