package com.hippsomhapp.huffdroid.model;

import java.util.List;



public class HuffDuffs {
	private String title;
	private String link;
	private List<HuffDuff> items;
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLink(){
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public List<HuffDuff> getItems(){
		return items;
	}
	
	public void setItems(List<HuffDuff> items){
		this.items = items;
	}
	
	public String[] getItemsAsArray(){
		String[] array = new String[items.size()];;
		
		for(int i = 0; i < items.size(); i++) {
			array[i] = items.get(i).getTitle();
		}
		
		return array;
	}
}
