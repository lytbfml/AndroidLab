package com.hw3.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author Yangxiao on 10/16/2017.
 */

public class ChoicesAdaptor extends ArrayAdapter<String>{
	
	private Context context;
	private int resource;
	private List<String> objects = null;
	
	public ChoicesAdaptor(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
		super(context, resource, objects);
		this.resource = resource;
		this.context = context;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		TextView tv;
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(resource, parent, false);
			
			tv = (TextView) row.findViewById(R.id.choiceHolder);
			
			row.setTag(tv);
		} else {
			tv = (TextView) row.getTag();
		}
		
		String choice = objects.get(position);
		
		tv.setText(choice);
		
		return row;
		
	}
}
