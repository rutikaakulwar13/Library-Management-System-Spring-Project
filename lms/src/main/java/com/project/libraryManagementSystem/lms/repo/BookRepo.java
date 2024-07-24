package com.project.libraryManagementSystem.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.libraryManagementSystem.lms.entity.Book;

public interface BookRepo extends JpaRepository<Book,Integer>{

}
