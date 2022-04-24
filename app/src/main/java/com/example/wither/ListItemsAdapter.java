package com.example.wither;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class ListItemsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mItems;

    public ListItemsAdapter(Context mContext, ArrayList<String> mItems) {
        this.mContext= mContext;
        this.mItems = mItems;
    }

    @Override
    public int getCount(){
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_list_item, parent, false);
        }

        TextView txt_number = (TextView) convertView.findViewById(R.id.txt_number);
        TextView txt_item = (TextView) convertView.findViewById(R.id.txt_item);

        txt_number.setText("" + position);
        txt_item.setText(mItems.get(position));
        return convertView;
    }
}
