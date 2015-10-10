package com.zeeh.testexpandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * For ExpandableListView, 3 layouts are needed: main(contains the ExpandableListView). Both parent and child views contain the TextView.
 * Parent layout is used to list the categories. Child layout is used to list the items.
 * HashMap is used to carry the key-value pair for catagories. List is used to carry individual items.
 * A note on getBaseContext() below. The method can be replaced by MainActivity.this. They mostly behave/work the same way.
 *  Use ApplicationContext on traversing multiple Activity classes and intents, ActivityContext tends to work better when staying inside the same Activity class
 *  http://stackoverflow.com/questions/1026973/android-whats-the-difference-between-the-various-methods-to-get-a-context
 */

public class MainActivity extends AppCompatActivity {

    HashMap<String,List<String>> movieCollection;
    List<String> movieGenre;
    ExpandableListView expandableListView;
    MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = (ExpandableListView)findViewById(R.id.exp_list);

        movieCollection = DataProvider.getInfo();
        movieGenre = new ArrayList<String>(movieCollection.keySet());  //save the movie genres in the movieGenre ArrayList

        // sending the context, list of categories, and list of item to adapter
        adapter = new MoviesAdapter(this, movieCollection, movieGenre);
        expandableListView.setAdapter(adapter);

        //Fire an event when user expand a category to see a list of items
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int movieGenreIdxPosition) {
//                Toast.makeText(getBaseContext(), movieGenre.get(groupPosition) + " is expanded.", Toast.LENGTH_SHORT).show();
// Instead of using getBaseContext(), try using MainActivity.this below:

                Toast.makeText(MainActivity.this, movieGenre.get(movieGenreIdxPosition) + " is expanded from "+movieGenreIdxPosition, Toast.LENGTH_SHORT).show();
            }
        });

        //Fire an event when user collapse the category
        expandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int movieGenreIdxPosition) {
                Toast.makeText(getBaseContext(), movieGenre.get(movieGenreIdxPosition) + " is collapsed.", Toast.LENGTH_SHORT).show();
            }
        });

        // Fire an event when user clicks on a list item
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int movieGenreIdxPosition, int movieItemIdxPosition, long id) {
                Toast.makeText(getBaseContext(),
                        movieCollection.get(movieGenre.get(movieGenreIdxPosition)).get(movieItemIdxPosition),Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),"movieGenre.get(movieGenreIdxPosition) = "+ movieGenre.get(movieGenreIdxPosition),Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),"movieGenreIdxPosition = "+ movieGenreIdxPosition+", "+"movieItemIdxPosition"+movieItemIdxPosition,Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), "Returns an array of movie items: "+movieCollection.get(movieGenre.get(movieGenreIdxPosition)),Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

}
