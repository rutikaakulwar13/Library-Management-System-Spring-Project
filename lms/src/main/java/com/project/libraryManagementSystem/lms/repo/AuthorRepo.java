package com.project.libraryManagementSystem.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.libraryManagementSystem.lms.entity.Author;

public interface AuthorRepo extends JpaRepository<Author,Integer>{

}
