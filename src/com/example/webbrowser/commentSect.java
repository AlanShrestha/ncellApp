package com.example.webbrowser;

public class commentSect {
	private String Name;
	private String Comment;
	
	public commentSect(String Name, String Comment){
		this.Name = Name;
		this.Comment = Comment;	
	}
	
	public String getName(){
		return this.Name;
	}
	
	public String getComment(){
		return this.Comment;
	}

}
