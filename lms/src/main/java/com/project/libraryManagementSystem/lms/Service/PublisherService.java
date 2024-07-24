package com.project.libraryManagementSystem.lms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.libraryManagementSystem.lms.entity.Publisher;
import com.project.libraryManagementSystem.lms.repo.PublisherRepo;
@Service
public class PublisherService {
	@Autowired
	private PublisherRepo publisherRepo;
	
	public List<Publisher> getAllPublishers()
	{
		return this.publisherRepo.findAll();
	}
	
	public Publisher getPublisherById(int id)
	{
		return this.publisherRepo.findById(id).orElseThrow(()->new RuntimeException("Given is is incorrect"));
	}
	
	public Publisher saveOrUpdatePublisher(Publisher publisher)
	{
		return this.publisherRepo.save(publisher);
	}
	
	public void deletePublisherById(int id)
	{
		this.publisherRepo.findById(id).orElseThrow(()->new RuntimeException("Given is is incorrect"));
		this.publisherRepo.deleteById(id);
	}
}
