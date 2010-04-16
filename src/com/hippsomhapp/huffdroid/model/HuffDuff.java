package com.hippsomhapp.huffdroid.model;



public class HuffDuff {
	private String title;
	private String description;
	private String link;
	private String url;
	private String date;
	private String tags;
	private Author author;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*
	 * Huffduffer link
	 */
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	/*
	 * Audio url
	 */
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	
}
