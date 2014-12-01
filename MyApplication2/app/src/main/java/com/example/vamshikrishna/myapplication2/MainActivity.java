package com.example.vamshikrishna.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends Activity {

    ArrayList<String> title;
    ArrayList<String> thumbnails;
    ArrayList<String> Authors;
    ArrayList<String> time;
    ArrayList<String> score;
    ArrayList<String> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final EditText text = (EditText) findViewById(R.id.editText);
        Button bt = (Button) findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = text.getText().toString();
                String[] res = str.split("\\s");
                str = "";
                for(int i=0;i<res.length;i++){
                    str = str+res[i]+"%20";
                }
                new SearchTask().execute("http://www.reddit.com/r/pics/search.json?q="+str);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SearchTask extends AsyncTask<String, String, String>{

        protected String doInBackground(String... uri){
            HttpClient client = new DefaultHttpClient();
            HttpResponse response;
            String str = null;
            try{
                response = client.execute(new HttpGet(uri[0]));
                StatusLine sl = response.getStatusLine();
                Log.d("Http",sl.toString());
                if(sl.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    str = out.toString();
                }
                else{
                    response.getEntity().getContent().close();
                    throw new IOException(sl.getReasonPhrase());
                }
            }
            catch(Exception e){
                Log.d("HttpRequest","Unsuccesfull Request");
            }

            return str;
        }

        protected void onPostExecute(String response){
                super.onPostExecute(response);

            try{
                    if(response == null) {
                        Toast.makeText(getApplicationContext(),"Cannot Reach Server Try Again",Toast.LENGTH_LONG).show();
                        throw new NullPointerException("Response is Null");
                    }
                    JSONArray childArray = new JSONObject(response).getJSONObject("data").getJSONArray("children");
                    title = new ArrayList<String>();
                    thumbnails = new ArrayList<String>();
                    Authors = new ArrayList<String>();
                    time = new ArrayList<String>();
                    score = new ArrayList<String>();
                    comments = new ArrayList<String>();
                    for(int i=0; i< childArray.length(); i++){
                        JSONObject dataobj = childArray.getJSONObject(i).getJSONObject("data");
                        title.add(i,dataobj.getString("title"));
                        thumbnails.add(i,dataobj.getString("thumbnail"));
                        Authors.add(i,dataobj.getString("author"));
                        time.add(i,Long.toString(dataobj.getLong("created")));
                        score.add(i,dataobj.getString("score"));
                        String x = dataobj.getString("num_comments");
                        if(x.equals(null)) x = "0";
                        comments.add(i,x);
                        //Log.d("Titles",str);
                    }
                    //Toast msg = Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG);
                    //msg.show();

            }
            catch(JSONException e){
                    Log.d("JSONException","Unable to parse");
                    return;
            }
            catch(NullPointerException e){
                Log.d("NullPointer Exception","Null Response");
                return;
            }

            Intent in = new Intent(MainActivity.this, SearchDataActivity.class);
            in.putStringArrayListExtra("title",title);
            in.putStringArrayListExtra("thumbnail",thumbnails);
            in.putStringArrayListExtra("Author",Authors);
            in.putStringArrayListExtra("time",time);
            in.putStringArrayListExtra("score",score);
            in.putStringArrayListExtra("comments",comments);
            startActivity(in);

        }
    }
}
