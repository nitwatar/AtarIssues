package mani.atar.com.issues.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import mani.atar.com.issues.R;

public class MainActivity extends AppCompatActivity {

    private String[] list_items={
            "CustomView with ListView Demo(issue - Drawing not proper)",
            "CustomView with RecyclerView Demo(issue -Drawing not proper)"
    };

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_items);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                         intent = new Intent(MainActivity.this,CustomViewListViewDemo.class);
                        break;
                    case 1:
                         intent = new Intent(MainActivity.this,CustomViewRecyclerViewDemo.class);
                      break;
                }

                startActivity(intent);
            }
        });

    }
}
