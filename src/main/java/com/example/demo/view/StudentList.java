package com.example.demo.view;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.model.Student;
import com.example.demo.repositories.StudentRepository;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Component
public class StudentList extends VerticalLayout {

	
	@Autowired
	StudentRepository studentRepository;
	
	@PostConstruct
	void  init(){
		setSpacing(true);
		
		 setStudents( studentRepository.findAll());
		
	}
	public Optional<Student> findStudentById(int id){
	return studentRepository.findById(id);
		
	}
	
	

	private void setStudents(List<Student> students) {
		 
		removeAllComponents();
		if(students != null)
		students.forEach(student->{
			addComponent(new StudentLayout(student));
		
		});
		
	}
	
}
