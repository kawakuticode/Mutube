package com.example.russeliusernestius.mutube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by russeliusernestius on 05/01/17.
 */
public class YouTubeAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;



    ArrayList<YoutubeBean> myVideos = new ArrayList<YoutubeBean>();

    public YouTubeAdapter(Context context, ArrayList<YoutubeBean> a) {
        mContext = context;
        this.myVideos = a;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return myVideos.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return myVideos.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.video_row, null);
            vh = new ViewHolder();
            vh.tv = (TextView) convertView.findViewById(R.id.tvv);
            vh.iv = (ImageView) convertView.findViewById(R.id.ivv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }


        vh.tv.setText(myVideos.get(position).getTitle());
        vh.iv.setImageBitmap(myVideos.get(position).getThumbnails());
        return convertView;
    }

    static class ViewHolder {
        TextView tv;
        ImageView iv;
    }
}
