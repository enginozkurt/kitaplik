package com.tuncamyo.proje_kitaplik.model;

public class Book {
	private int id;
	private String title;
	private String author;
	private String genre;
	private String description;
	
	public Book(){}
	
	public Book(String title, String author,String genre,String description) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author
				+"genre="+genre+", description="+description+ "]";
	}
}
