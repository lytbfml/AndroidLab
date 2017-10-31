package example.com.lab6;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * This class is used to represent the information in each row of the applications list view
 */
public class ItunesAdapter extends ArrayAdapter<ItunesRecord>{

	private Context context;
	private int layoutResourceId;
	private List<ItunesRecord> data = null;

	/**
	 * Constructor for a new ItunesAdapter
	 * @param context the current context 
	 * @param layoutResourceId the id to represent the layout
	 * @param data a list of information for each row in the list view
	 */
	public ItunesAdapter(Context context, int layoutResourceId, List<ItunesRecord> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@SuppressLint("DefaultLocale")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ItunesHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			//create a new ItunesHolder and set it to the fields the row in the list view
			holder = new ItunesHolder();
			
			holder.itunesAlbumName = (TextView) row.findViewById(R.id.songAlbum);
			holder.itunesSongTitle = (TextView) row.findViewById(R.id.songTitle);
			Log.d("TAGGG", "getView: setted");
			
			row.setTag(holder);
		} else {
			holder = (ItunesHolder) row.getTag();
		}
		
		//get the current position from the list
		ItunesRecord itunesRecord = data.get(position);
		String al = itunesRecord.getAlbumTitle();
		String st = itunesRecord.getSongTitle();
		holder.itunesAlbumName.setText(al);
		holder.itunesSongTitle.setText(st);
		
		return row;
	}

	/**
	 * A class to represent the fields in the row layout
	 */
	static class ItunesHolder
	{
		TextView itunesAlbumName;
		TextView itunesSongTitle;
	}
}