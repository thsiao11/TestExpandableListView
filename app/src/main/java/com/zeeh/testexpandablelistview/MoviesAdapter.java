package com.zeeh.testexpandablelistview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MoviesAdapter extends BaseExpandableListAdapter {

    private Context ctx;
    private HashMap<String,List<String>> movieCollection;
    private List<String> movieGenre;

    public MoviesAdapter(Context ctx, HashMap<String,List<String >> movieCollection,List<String> movieGenre) {
        this.ctx = ctx;
        this.movieCollection = movieCollection;
        this.movieGenre = movieGenre;
    }

    @Override
    public Object getChild(int parent, int child) {
        //return the name of the movie
        return movieCollection.get(movieGenre.get(parent)).get(child);
    }

    @Override
    public long getChildId(int parent, int child) {
        // just return the idx position of the movie/item
        return child;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View convertView, ViewGroup parentView) {
        // get each movie name and inflate TextView to insert name into it
        String child_title = (String) getChild(parent,child);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_layout, parentView, false);
        }
        TextView child_textview = (TextView)convertView.findViewById(R.id.child_txt);
        child_textview.setText(child_title);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //For each genre, returns the number of movies in that genre ?
        return movieCollection.get(movieGenre.get(groupPosition)).size();
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView) {
        // Get the movie genre name and inflate TextView to insert each genre into it
        String group_title = (String)getGroup(parent);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_layout, parentView,false);
        }
        TextView parent_textview = (TextView)convertView.findViewById(R.id.parent_txt);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertView;
    }

    @Override
    public int getGroupCount() {
        //return the number of movie genres
        return movieGenre.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // return the name of movie genre
        return movieGenre.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        //returns the idx position of movie genre
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        //default is false here.
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // set return to true if you want to listen for user clicks on each item
        return true;
    }
}
