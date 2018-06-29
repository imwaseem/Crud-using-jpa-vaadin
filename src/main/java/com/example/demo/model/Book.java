package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.vaadin.annotations.AutoGenerated;

@Entity
@Table(name="book")
public class Book {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int oid;
	private String name;
	private String author;

    //@ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "book_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn
    private Student student;

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Book(String name,String author){
		
		this.name = name;
		this.author = author;
	}
	public Book(String name,String author,Student student){
		
		//this.student = student;
		this.name = name;
		this.author = author;
	}
	public Book(){
		
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		author = author;
	}
	
}