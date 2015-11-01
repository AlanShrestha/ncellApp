package com.example.webbrowser;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;

public class CommentList extends ListActivity{
	
	public void onCreate(Bundle mermaid){
		super.onCreate(mermaid);
		
		CustomCommentAdapter myAdapter = new CustomCommentAdapter(this, fetchData());
		
		setListAdapter(myAdapter);
	}

	private ArrayList<commentSect> fetchData(){
		ArrayList<commentSect> items = new ArrayList<commentSect>();
		items.add(new commentSect("Ram", "What the face!"));
		items.add(new commentSect("Harry", "Oh my goodness!"));
		items.add(new commentSect("Hermoine", "Ha ha"));
		
		return items;
	}
}
