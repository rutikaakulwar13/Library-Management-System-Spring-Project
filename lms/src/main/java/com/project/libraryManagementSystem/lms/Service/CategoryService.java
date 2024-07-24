package com.project.libraryManagementSystem.lms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.libraryManagementSystem.lms.entity.Categories;
import com.project.libraryManagementSystem.lms.repo.CategoriesRepo;
@Service
public class CategoryService {
	
	@Autowired
	private CategoriesRepo categoriesRepo;
	
	public List<Categories> getAllCategories()
	{
		return this.categoriesRepo.findAll();
	}
	
	public Categories getCategoryById(int id)
	{
		return this.categoriesRepo.findById(id).orElseThrow(()->new RuntimeException("Given id is incorrect"));
	}
	
	public Categories saveOrUpdateCategory(Categories category)
	{
		return this.categoriesRepo.save(category);
	}
	
	public void deleteCategoryById(int id)
	{
		 this.categoriesRepo.findById(id).orElseThrow(()->new RuntimeException("Given id is incorrect"));
		 this.categoriesRepo.deleteById(id);
	}
}
