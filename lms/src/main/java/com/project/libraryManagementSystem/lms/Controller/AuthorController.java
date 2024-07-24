package com.project.libraryManagementSystem.lms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.project.libraryManagementSystem.lms.entity.Author;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@GetMapping
	public ResponseEntity<List<Author>> listAuthors()
	{
		List<Author> authors=this.authorService.getAllAuthors();
		return ResponseEntity.ok(authors);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable int id)
	{
		Author author=this.authorService.getAuthorById(id);
		if(author==null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(author);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable int id,@RequestBody Author author)
	{
		Author existingAuthor=this.authorService.getAuthorById(id);
		if(existingAuthor==null)
		{
			return ResponseEntity.notFound().build();
		}
		else {
			author.setId(id);
		 authorService.saveOrUpdateAuthor(author);
		 return ResponseEntity.ok(author);
		}
	}

	@PostMapping
	public ResponseEntity<Author> saveAuthor(@RequestBody Author author)
	{
		Author createdAuthor=authorService.saveOrUpdateAuthor(author);
		return ResponseEntity.ok(createdAuthor);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable int id)
	{
		this.authorService.deleteAuthorById(id);
		return ResponseEntity.noContent().build();
	}

}
