package com.example.webbrowser;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomCommentAdapter extends ArrayAdapter {
	
	private final Context context;
	private final ArrayList<commentSect> commentsArrayList;
	
	public CustomCommentAdapter(Context context, ArrayList<commentSect> commentsArrayList){
		
		super(context, R.layout.comments, commentsArrayList);
		this.context = context;
		this.commentsArrayList = commentsArrayList;
	
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View commentView = inflater.inflate(R.layout.comments, parent, false);
		
		TextView name = (TextView)commentView.findViewById(R.id.name);
		TextView comment = (TextView)commentView.findViewById(R.id.comment);
		
		name.setText(commentsArrayList.get(position).getName());
		comment.setText(commentsArrayList.get(position).getComment());;
		
		return commentView;
		
	}
	
	
	
}
