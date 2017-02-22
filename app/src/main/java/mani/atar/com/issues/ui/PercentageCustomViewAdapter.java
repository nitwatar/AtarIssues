package mani.atar.com.issues.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mani.atar.com.issues.R;
import mani.atar.com.issues.view.BucketProgressBarView;


/**
 * Created by atarmanipandey on 22/2/17.
 */
public class PercentageCustomViewAdapter extends
        RecyclerView.Adapter<PercentageCustomViewAdapter.PercentageCustomViewHolder> {


    private Context mContext;
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
    private String[] okcount={"1","2","3","4","5","6","7","8","9"};
    private String[] kocount={"9","8","7","6","5","4","3","2","1"};


    public PercentageCustomViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    @Override
    public PercentageCustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customview_rcyclerview_row, parent, false);
        return new PercentageCustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PercentageCustomViewHolder holder, int position) {

        Log.d("atar", "onBindViewHolder: pos="+position);
        holder.tv_LeverName.setText(names[position]);
        holder.hbv_my.setText(okcount[position], kocount[position]);
        holder.hbv_my.invalidate();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public class PercentageCustomViewHolder extends PercentageCustomViewAdapter.ViewHolder {

        public TextView tv_LeverName;
        BucketProgressBarView hbv_my;

        public PercentageCustomViewHolder(View v) {
            super(v);
            tv_LeverName = (TextView) itemView.findViewById(R.id.tv_bucket_name);
            hbv_my = (BucketProgressBarView) itemView.findViewById(R.id.layout_bucket_performance_mine);
        }
    }


}