package com.project.libraryManagementSystem.lms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.libraryManagementSystem.lms.entity.Book;
import com.project.libraryManagementSystem.lms.repo.BookRepo;
@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	public List<Book> getAllBooks()
	{
		return this.bookRepo.findAll();
	}
	
	public Book getBookById(int id)
	{
		return this.bookRepo.findById(id).orElseThrow(()->new RuntimeException("Given id is incorrect"));
	}
	
	public Book saveOrUpdateBook(Book book)
	{
		return this.bookRepo.save(book);
	}
	
	public void deleteBookById(int id)
	{
		this.bookRepo.findById(id).orElseThrow(()->new RuntimeException("Given id is incorrect"));
		this.bookRepo.deleteById(id);
	}
}
