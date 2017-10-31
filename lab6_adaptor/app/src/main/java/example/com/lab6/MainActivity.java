package example.com.lab6;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements DownloadWebpageTask.ResultHandler {
	EditText name;
	ListView list;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list = getListView();
		
		name = (EditText) findViewById(R.id.username);
		
		Button search = (Button) findViewById(R.id.search);
		search.setOnClickListener(new OnClickListener() {
			
			/* (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			public void onClick(View v) {
				
				String artist = name.getText().toString();
				Log.e("Artist Searched", artist);
				
				DownloadWebpageTask task = new DownloadWebpageTask(MainActivity.this);
				String str = artist.replace(" ", "+");
				Log.e("Artist Searched", str);
				task.execute("https://itunes.apple" +
						".com/search?term="+artist+"&entity=song&limit=20");
			}
		});
	}
	
	
	@Override
	public void handleResult(String result) {
		// Convert string to object
		JSONArray arr = null;
		List<ItunesRecord> data = new ArrayList<>();
		Log.d("dedede", "??");
		JSONObject result_j = null;
		try {
			result_j = new JSONObject(result);
			arr = result_j.getJSONArray("results");
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			for (int i = 0; i < arr.length(); i++) {
				JSONObject objects = arr.getJSONObject(i);
				assert result_j != null;
				String album = objects.getString("collectionName");
				String title = objects.getString("trackName");
				data.add(new ItunesRecord(album, title));
				Log.w("Dta", album + " , " + title);
			}
			
		}
		catch (JSONException ex) {
			Log.e("Exception", "Request not completed" + ex.getMessage());
		}
		
		
		ItunesAdapter itAd = new ItunesAdapter(MainActivity.this, R.layout.simple_list_item_1,
				data);
		setListAdapter(itAd);
		
	}
}
