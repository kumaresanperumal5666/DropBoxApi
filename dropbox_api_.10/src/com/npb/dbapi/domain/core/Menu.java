package com.npb.dbapi.domain.core;

import java.util.ArrayList;

public class Menu {
	String title;
	
	ArrayList<String> items=new ArrayList<String>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<String> getItems() {
		return items;
	}
	public void setItems(ArrayList<String> items) {
		this.items = items;
	}
		

}
