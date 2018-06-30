package com.example.demo.view;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.model.Book;
import com.example.demo.model.Student;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.StudentRepository;
import com.vaadin.ui.VerticalLayout;
@Component
public class BookList extends VerticalLayout {

	@Autowired
	BookRepository bookRepository ;
	
	@PostConstruct
	void  init(){
		setSpacing(true);
		
		 setBooks( bookRepository.findAll());
		
	}
	public Optional<Book> findBookById(int id){
	return bookRepository.findById(id);
		
	}
	
	private void setBooks(List<Book> books) {
		 
		removeAllComponents();
		
		books.forEach(book->{
			addComponent(new BookLayout(book));
		
		});
		
	}
	
}
