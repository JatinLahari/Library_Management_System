package com.gui.model;

public class LibraryModel {
	private int bookId;
	private String bookTitle;
	private String ISBN;
	private int availableCopies;
	
	private String author;
	private String contact;
	private String address;
	
	public LibraryModel(int id, String title, String isbn, int copies, String author, String contact, String address) {
		bookId = id;
		bookTitle = title;
		ISBN = isbn;
		availableCopies = copies;
		this.author = author;
		this.contact = contact;
		this.address = address;
	}
	public int getBookId() {
		return bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public String getISBN() {
		return ISBN;
	}
	public String getContact() {
		return contact;
	}
	public String getAuthor() {
		return author;
	}
	public int getAvailableCopies() {
		return availableCopies;
	}
	public String getAddress() {
		return address;
	}
}
