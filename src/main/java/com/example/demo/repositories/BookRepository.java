package com.example.demo.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;
import com.example.demo.model.Student;


public interface BookRepository extends JpaRepository<Book, Integer> {
    
}
