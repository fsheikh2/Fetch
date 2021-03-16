package com.example.fetch;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JsonRetriever extends AsyncTask<String, String, JSONArray> {

    private AsyncResponse mDelegate = null;

    public AsyncResponse getDelegate(){
        return mDelegate;
    }

    public void setDelegate(MainActivity mainActivity){
        mDelegate = mainActivity;

    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        Log.d("TAG: ", " doInBackground INVOKED");
        try{
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            //ArrayList<String> responseArray = new ArrayList<String>();
            JSONArray responseArray = new JSONArray();

            while( (line = reader.readLine()) != null ){
                buffer.append(line+"\n");
                Log.d("Response: ", ">" + line);

                //responseArray.add(line);
                if(!line.equals("[") && !line.equals("]"))
                    responseArray.put(new JSONObject(line));
            }

            return responseArray;//buffer.toString();

        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("JSON ERROR: ", "Printing JSON ERROR STACK TRACE\n");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray result){
        if(result != null)
            mDelegate.processFinish(result);
        else
            Log.d("POST EXECUTE MESSAGE: ", " RESULT WAS NULL");
    }
}
