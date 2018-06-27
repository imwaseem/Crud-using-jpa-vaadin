package com.example.demo.view;

import org.springframework.stereotype.Component;

import com.example.demo.model.Student;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;


public class StudentLayout extends HorizontalLayout{

	private final TextField oid;
	private final TextField name;
	private final TextField age;
	private final TextField phone;
	public StudentLayout(Student student) {
		oid = new TextField();
		oid.setValue(student.getOid()+"");
		name = new TextField();
		name.setValue(student.getName());
		age = new TextField();
		age.setValue(student.getAge()+"");
		phone = new TextField();
		phone.setValue(student.getPhone());
		
	 
		addComponents(oid,name,age,phone);
	}
}
