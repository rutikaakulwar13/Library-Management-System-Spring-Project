package com.project.libraryManagementSystem.lms.entity;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name="books_authors", joinColumns = {@JoinColumn(name="book_id")},
	inverseJoinColumns = {@JoinColumn(name="author_id") })
	private List<Author> authors;
	
	@ManyToMany
	@JoinTable(name="books_catergories",joinColumns= {@JoinColumn(name="book_id")},
	inverseJoinColumns = {@JoinColumn(name="category_id")})
	private List<Categories> categories;
	
	@ManyToMany
	@JoinTable(name="books_publishers",
	joinColumns= {@JoinColumn(name="book_id")},
	inverseJoinColumns = {@JoinColumn(name="publisher_id")})
	private List<Publisher> publishers;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String name, List<Author> authors, List<Categories> categories, List<Publisher> publishers) {
		super();
		this.id = id;
		this.name = name;
		this.authors = authors;
		this.categories = categories;
		this.publishers = publishers;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public List<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", authors=" + authors + ", categories=" + categories
				+ ", publishers=" + publishers + "]";
	}
	
	
}
