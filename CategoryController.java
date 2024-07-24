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

import com.project.libraryManagementSystem.lms.Service.CategoryService;
import com.project.libraryManagementSystem.lms.entity.Author;
import com.project.libraryManagementSystem.lms.entity.Categories;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Categories>> listOfCategories()
	{
		List<Categories> categories=this.categoryService.getAllCategories();
		System.out.println( ResponseEntity.ok(categories));
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categories> getBookById(@PathVariable int id)
	{
		Categories category=this.categoryService.getCategoryById(id);
		if(category==null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(category);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categories> saveCategory(@PathVariable int id,@RequestBody Categories categories)
	{
		Categories existingCategory=this.categoryService.getCategoryById(id);
		if(existingCategory==null)
		{
			return ResponseEntity.notFound().build();
		}
		else {
			categories.setId(id);
			this.categoryService.saveOrUpdateCategory(categories);
			return ResponseEntity.ok(categories);
		}
	}
	
	@PostMapping
	public ResponseEntity<Categories> saveOrUpdate(@RequestBody Categories categories)
	{
		Categories createdCategory=this.categoryService.saveOrUpdateCategory(categories);
		return ResponseEntity.ok(createdCategory);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Categories> deleteCategory(@PathVariable int id)
	{
		this.categoryService.deleteCategoryById(id);
		return ResponseEntity.notFound().build();
		
	}
}
