package mani.atar.com.issues.ui;

/**
 * Created by atarmanipandey on 22/2/17.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import mani.atar.com.issues.R;
import mani.atar.com.issues.view.BucketProgressBarView;

public class CustomViewListViewAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private String[] name;
    private String[] okcount={"1","2","3","4","5","6","7","8","9"};
    private String[] kocount={"9","8","7","6","5","4","3","2","1"};

    public CustomViewListViewAdapter(Activity context , String[] name) {
        super(context, R.layout.customview_listview_row,name);
        this.context = context;
        this.name = name;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.customview_listview_row, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.tv_bucket_name);
        BucketProgressBarView hbv_my = (BucketProgressBarView) rowView.findViewById(R.id.layout_bucket_performance_mine);
        txtTitle.setText(name   [position]);
        hbv_my.setText(okcount[position], kocount[position]);
        return rowView;
    }
}