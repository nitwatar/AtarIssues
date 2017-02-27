package mani.atar.com.issues.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import mani.atar.com.issues.R;

/**
 * Created by atarmanipandey on 22/2/17.
 */
public class CustomViewRecyclerViewDemo extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PercentageCustomViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_recyclerview_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(CustomViewRecyclerViewDemo.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new PercentageCustomViewAdapter(this);

        mRecyclerView.setAdapter(mAdapter);


    }
}