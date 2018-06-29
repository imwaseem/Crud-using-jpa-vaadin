package com.example.demo.view;

import org.springframework.stereotype.Component;

import com.example.demo.model.Book;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class BookLayout extends HorizontalLayout{

	private final TextField oid;
	private final TextField name;
	private final TextField author;
	
	public BookLayout(Book book) {
		oid = new TextField();
		oid.setValue(book.getOid()+"");
		name = new TextField();
		name.setValue(book.getName());
		author = new TextField();
		author.setValue(book.getAuthor()+"");
	
	 
		addComponents(oid,name,author);
	}
}
