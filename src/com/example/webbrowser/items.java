package com.example.webbrowser;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class items extends ListActivity{
	
	JSONParser jsonParser = new JSONParser();
	private static final String ITEMS_URL = "http://192.168.43.184/items.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	private static final String TAG_ITEMS = "Items";
	private static final String TAG_ITEM_ID = "food_id";
	private static final String TAG_ITEM_NAME = "name";
	private static final String TAG_ITEM_ICON = "food_icon";
	List<String> allItems = new ArrayList<String>();
	private JSONArray elements = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new itemsView().execute();
		//updateList();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(items.this);
		Editor edit = sp.edit();
		edit.putString("clickedItem", allItems.get(position));
		edit.commit();
		Intent newIntent = new Intent(items.this, article.class);
		startActivity(newIntent);
	}
	
	public void updateJSONdata(JSONObject json){
				try {
					elements = json.getJSONArray(TAG_ITEMS);

					for (int i = 0; i < elements.length(); i++) {
						JSONObject c = elements.getJSONObject(i);

						// gets the content of each tag
						String item_id = c.getString(TAG_ITEM_ID);
						String name = c.getString(TAG_ITEM_NAME);
						//String item_icon = c.getString(TAG_ITEM_ICON);
                        allItems.add(name);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
	}
	
	private void updateList(){

		ArrayAdapter adapter = new ArrayAdapter<String>(items.this, android.R.layout.simple_list_item_1, allItems);
	    setListAdapter(adapter);
	    
	}
	
	private class itemsView extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			 int success=0;
	            try {
	            	    JSONObject json = new JSONObject();
	                    List<NameValuePair> param = new ArrayList<NameValuePair>();
	                    param.add(new BasicNameValuePair("request", "food"));

	                    json = jsonParser.makeHttpRequest(
	                       ITEMS_URL, "POST", param);
	                    success = json.getInt(TAG_SUCCESS);
	                
	                if (success == 1){	
	                	updateJSONdata(json);
						return json.getString(TAG_MESSAGE);
	                }else{
	                	return json.getString(TAG_MESSAGE);
	                }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
			return null;
		}
		

		@Override
		protected void onPostExecute(String result) {
			
			updateList();
			
		}

	}

}
