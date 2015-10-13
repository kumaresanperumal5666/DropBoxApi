package com.npb.dbapi.domain.core;

import java.util.ArrayList;
import java.util.List;

public class DbMyFolder {
	private String title;
	private boolean isFile;
	private List<DbMyFolder> items = new ArrayList<DbMyFolder>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<DbMyFolder> getItems() {
		return items;
	}

	public void setItems(List<DbMyFolder> items) {
		this.items = items;
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

}
