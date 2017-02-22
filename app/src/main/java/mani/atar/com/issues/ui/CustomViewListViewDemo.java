package mani.atar.com.issues.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import mani.atar.com.issues.R;

/**
 * Created by atarmanipandey on 22/2/17.
 */
public class CustomViewListViewDemo extends AppCompatActivity {

    private String[] names={
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",

    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomViewListViewAdapter adapter = new
                CustomViewListViewAdapter(CustomViewListViewDemo.this,names);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
