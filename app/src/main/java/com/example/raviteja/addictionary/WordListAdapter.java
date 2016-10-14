package com.example.raviteja.addictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class WordListAdapter extends BaseAdapter {

    private ArrayList<String> data;
    private Context context;

    WordListAdapter(Context context) {
        super();
        this.context = context;
        data = new ArrayList<>();
    }

    void addItem(String word) {
        data.add(word);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        if(position <= data.size())
            return data.get(position);
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row;
        if(position%2 == 0)
            row = layoutInflater.inflate(R.layout.listview_row_item_user, null); //EXP
        else
            row = layoutInflater.inflate(R.layout.listview_row_item_system, null);

        TextView textView = (TextView) row.findViewById(R.id.textView);
        /* ImageView imageView = (ImageView) row.findViewById(R.id.imageView); */
        textView.setText(data.get(position));
        convertView = row;
        return convertView;
    }
}
