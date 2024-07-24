package com.project.libraryManagementSystem.lms.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.libraryManagementSystem.lms.Service.AuthorService;
import com.project.libraryManagementSystem.lms.Service.BookService;
import com.project.libraryManagementSystem.lms.Service.CategoryService;
import com.project.libraryManagementSystem.lms.Service.PublisherService;
import com.project.libraryManagementSystem.lms.entity.Author;
import com.project.libraryManagementSystem.lms.entity.Book;
import com.project.libraryManagementSystem.lms.entity.Categories;
import com.project.libraryManagementSystem.lms.entity.Publisher;


@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PublisherService publisherService;
	@GetMapping
	public ResponseEntity<List<Book>> listBooks()
	{
		List<Book> books=this.bookService.getAllBooks();
		System.out.println(ResponseEntity.ok(books));
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id)
	{
		Book book=this.bookService.getBookById(id);
		if(book==null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(book);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id,@RequestBody Book book)
	{
		Book existingBook=this.bookService.getBookById(id);
		if(existingBook==null)
		{
			return ResponseEntity.notFound().build();
		}
		else {
			List<Author> authors=new ArrayList<>();
			for(Author author: book.getAuthors())
			{
				Author foundAuthor=authorService.getAuthorById(author.getId());
				if(foundAuthor==null)
				{
					return ResponseEntity.notFound().build();
				}
				authors.add(foundAuthor);
			}
			book.setAuthors(authors);
			
			List<Categories> categories=new ArrayList<>();
			for(Categories category: book.getCategories())
			{
				Categories foundCategory=categoryService.getCategoryById(category.getId());
				
				if(foundCategory==null)
				{
					return ResponseEntity.notFound().build();
				}
				categories.add(foundCategory);
			}
			book.setCategories(categories);
			
			List<Publisher> publishers=new ArrayList<>();
			for(Publisher publisher: book.getPublishers())
			{
				Publisher foundPublisher=this.publisherService.getPublisherById(publisher.getId());
				if(foundPublisher==null)
				{
					return ResponseEntity.notFound().build();
				}
				publishers.add(foundPublisher);
			}
			book.setPublishers(publishers);
			book.setId(id);
		 bookService.saveOrUpdateBook(book);
		 return ResponseEntity.ok(book);
		}
	}

	@PostMapping
	public ResponseEntity<Book> saveBook(@RequestBody Book book)
	{
		List<Author> authors=new ArrayList<>();
		for(Author author: book.getAuthors())
		{
			Author foundAuthor=authorService.getAuthorById(author.getId());
			if(foundAuthor==null)
			{
				return ResponseEntity.notFound().build();
			}
			authors.add(foundAuthor);
		}
		book.setAuthors(authors);
		
		List<Categories> categories=new ArrayList<>();
		for(Categories category: book.getCategories())
		{
			Categories foundCategory=categoryService.getCategoryById(category.getId());
			
			if(foundCategory==null)
			{
				return ResponseEntity.notFound().build();
			}
			categories.add(foundCategory);
		}
		book.setCategories(categories);
		
		List<Publisher> publishers=new ArrayList<>();
		for(Publisher publisher: book.getPublishers())
		{
			Publisher foundPublisher=this.publisherService.getPublisherById(publisher.getId());
			if(foundPublisher==null)
			{
				return ResponseEntity.notFound().build();
			}
			publishers.add(foundPublisher);
		}
		book.setPublishers(publishers);
		Book createdBook=bookService.saveOrUpdateBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> deleteAuthor(@PathVariable int id)
	{
		this.bookService.deleteBookById(id);
		return ResponseEntity.noContent().build();
	}

}