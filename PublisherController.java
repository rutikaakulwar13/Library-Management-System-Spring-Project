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

import com.project.libraryManagementSystem.lms.Service.PublisherService;
import com.project.libraryManagementSystem.lms.entity.Author;
import com.project.libraryManagementSystem.lms.entity.Categories;
import com.project.libraryManagementSystem.lms.entity.Publisher;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
	@Autowired
	private PublisherService publisherService;
	
	@GetMapping
	public ResponseEntity<List<Publisher>> listOfPublishers()
	{
		List<Publisher> publishers=this.publisherService.getAllPublishers();
		return ResponseEntity.ok(publishers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Publisher> getPublisherById(@PathVariable int id)
	{
		Publisher publisher=this.publisherService.getPublisherById(id);
		if(publisher==null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(publisher);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Publisher> savePublisher(@PathVariable int id,@RequestBody Publisher publisher)
	{
		Publisher existingPublisher=this.publisherService.getPublisherById(id);
		if(existingPublisher==null)
		{
			return ResponseEntity.notFound().build();
		}
		else {
			publisher.setId(id);
			this.publisherService.saveOrUpdatePublisher(publisher);
			return ResponseEntity.ok(publisher);
		}
	}
	
	@PostMapping
	public ResponseEntity<Publisher> saveOrUpdate(@RequestBody Publisher publisher)
	{
		Publisher createdPublisher=publisherService.saveOrUpdatePublisher(publisher);
		return ResponseEntity.ok(createdPublisher);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Categories> deletePublisher(@PathVariable int id)
	{
		this.publisherService.deletePublisherById(id);
		return ResponseEntity.notFound().build();
		
	}
}
