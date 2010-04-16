package com.hippsomhapp.huffdroid.controller;

import java.util.List;

import com.hippsomhapp.huffdroid.R;
import com.hippsomhapp.huffdroid.R.id;
import com.hippsomhapp.huffdroid.R.layout;
import com.hippsomhapp.huffdroid.model.HuffDuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HuffAdapter extends ArrayAdapter<HuffDuff> {
	
	private List<HuffDuff> mItems; 
	private Context mContext;
	
	public HuffAdapter(Context context, int textViewResourceId, List<HuffDuff> items) {
		super(context, textViewResourceId, items);
		mItems   = items;
		mContext = context;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row, null);
        }
        HuffDuff hd = mItems.get(position);
        
        if (hd != null) {
        	TextView tt = (TextView) v.findViewById(R.id.toptext);
            TextView bt = (TextView) v.findViewById(R.id.bottomtext);
        	
            if (tt != null) { tt.setText(hd.getTitle());}
            if (bt != null) { bt.setText(hd.getDescription());}
        }
        return v;
		
	}

}
