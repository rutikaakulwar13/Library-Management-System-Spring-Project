package com.project.libraryManagementSystem.lms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.libraryManagementSystem.lms.entity.Author;
import com.project.libraryManagementSystem.lms.repo.AuthorRepo;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepo authorRepo;
	
	public List<Author> getAllAuthors()
	{
		return authorRepo.findAll();
	}
	
	public Author getAuthorById(int id)
	{
		return this.authorRepo.findById(id).orElseThrow(()->new RuntimeException("Given id is incorrect"));
	}
	
	
	public Author saveOrUpdateAuthor(Author author)
	{
		return authorRepo.save(author);
	}
	
	public void deleteAuthorById(int id)
	{
		this.authorRepo.findById(id).orElseThrow(()->new RuntimeException("Given id is incorrect"));
		authorRepo.deleteById(id);
	}
}
