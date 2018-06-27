package com.example.demo.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Student;
import com.example.demo.repositories.StudentRepository;
import com.vaadin.ui.VerticalLayout;

@Component
public class StudentList extends VerticalLayout {

	@Autowired
	StudentRepository studentRepository;
	
	@PostConstruct
	void  init(){
		setSpacing(true);
		
		setStudents(studentRepository.findAll());
		
	}
	StudentList(){
		
	}
	private void setStudents(List<Student> students) {
		
		removeAllComponents();
		
		students.forEach(student->{
			addComponent(new StudentLayout(student));
		
		});
		
	}
	
}
