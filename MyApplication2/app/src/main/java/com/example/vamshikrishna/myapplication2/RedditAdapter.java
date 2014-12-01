package com.example.vamshikrishna.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by VamshiKrishna on 11/29/2014.
 */
public class RedditAdapter extends ArrayAdapter<ListItems> {

        int ResourceId;
        Context c;
        ArrayList<ListItems> data;
        public RedditAdapter(Context c, int ResourceId, ArrayList<ListItems> data){
            super(c,ResourceId,data);
            this.c = c;
            this.ResourceId = ResourceId;
            this.data = data;

        }

        public View getView(int pos, View convertView, ViewGroup parent){


            LayoutInflater inflater =  (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view, parent, false);

            //ImageView iv = (ImageView) convertView.findViewById(R.id.imgIcon);
            TextView tv = (TextView) convertView.findViewById(R.id.txtTitle);
            TextView tv1 = (TextView) convertView.findViewById(R.id.txtAuthor);
            TextView tv2 = (TextView) convertView.findViewById(R.id.txtScore);
            TextView tv3 = (TextView) convertView.findViewById(R.id.txtTime);




            ListItems lt = data.get(pos);

            tv.setText(lt.getTitle());
            tv1.setText(lt.getAuthor());
            tv2.setText(lt.getScore());
            tv3.setText(lt.getTime());
            //if(lt.getThumbnail() != null)
            //iv.setImageDrawable(lt.getThumbnail());
            //else
            //iv.setImageResource(R.drawable.ic_launcher);

            return convertView;
        }


}
