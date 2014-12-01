package com.example.vamshikrishna.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class SearchDataActivity extends Activity {

    /*ArrayList<String> title;
    ArrayList<String> thumbnails;
    ArrayList<String> Authors;
    ArrayList<String> time;
    ArrayList<String> score; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_data);

        ArrayList<String> title;
        ArrayList<String> thumbnails;
        ArrayList<String> Authors;
        ArrayList<String> time;
        ArrayList<String> score;
        ArrayList<String> comments;

        final ArrayList<ListItems> items = new ArrayList<ListItems>();

        Intent in = getIntent();
        title = in.getStringArrayListExtra("title");
        thumbnails = in.getStringArrayListExtra("thumbnail");
        Authors = in.getStringArrayListExtra("Author");
        time = in.getStringArrayListExtra("time");
        score = in.getStringArrayListExtra("score");
        comments = in.getStringArrayListExtra("comments");

        for(int i=0;i< title.size(); i++){

            InputStream is;
            Drawable d = null;
            try {

                Bitmap x = null;
                HttpURLConnection connection = (HttpURLConnection) new URL(thumbnails.get(i)).openConnection();
                connection.connect();

                is = connection.getInputStream();
                if(is != null) {
                    x = BitmapFactory.decodeStream(is);
                }
                if(x != null) {
                    d = new BitmapDrawable(getResources(), x);
                }

            }
            catch(Exception e){
                Log.d("Malformed URL","Cannot Create Thumbnail");
                d = null;
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd: HH-MM", Locale.US);
            String str = df.format(new Date(Long.valueOf(time.get(i))*1000));

            ListItems lt = new ListItems(title.get(i),d, Authors.get(i),str,score.get(i),comments.get(i));
            items.add(i,lt);

        }

         RedditAdapter adap = new RedditAdapter(this, R.layout.activity_search_data, items);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adap);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItems lt;
                Intent in;
                Log.d("ItemClick","Clicked on item");
                lt = items.get(position);
                Bundle b = new Bundle();
                b.putString("Title",lt.getTitle());
                b.putString("Author",lt.getAuthor());
                b.putString("Time",lt.getTime());
                b.putString("Score",lt.getScore());
                b.putString("comments",lt.getComments());
                in = new Intent(SearchDataActivity.this, DisplayActivity.class);
                in.putExtra("Bundle",b);
                startActivity(in);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_data, menu);
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
}
