package com.example.demo.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AuthenticateUser;
import com.example.demo.model.Login;
import com.example.demo.model.Student;
import com.example.demo.repositories.StudentRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("valo")

public class MainUI extends UI {

	@Autowired
	AuthenticateUser auth;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	StudentList studentList;
	 
	@Override
	protected void init(VaadinRequest request) {
	          	
		LoginForm();

	}
	

	
	private void AddStudentForm(){
		Label header = new Label("Enter the Data To Add New Student");
		header.addStyleName(ValoTheme.LABEL_H1);
		TextField studentId = new TextField("Student ID");
		Button deleteStudentButton = new Button("Delete Student");
		deleteStudentButton.setIcon(FontAwesome.REMOVE);
		deleteStudentButton.addStyleName(ValoTheme.BUTTON_DANGER);
    	//studentId.setCaption("User ID");
		TextField studentName = new TextField("Name");
		TextField studentAge = new TextField("Age");
		TextField studentPhoneNumber= new TextField("Phone number");
		Button addStudentData = new Button("Add");
		addStudentData.addStyleName(ValoTheme.BUTTON_PRIMARY);
		Button deleteStudentData = new Button("delete");
		deleteStudentData.addStyleName(ValoTheme.BUTTON_DANGER);
		HorizontalLayout horizontalLayout = new HorizontalLayout(addStudentData,deleteStudentData);

		VerticalLayout studentDataInsertionLayout = new VerticalLayout(header,studentName,studentAge,studentPhoneNumber,horizontalLayout);
		studentDataInsertionLayout.setMargin(true);
		studentDataInsertionLayout.setSpacing(true);
		setContent(studentDataInsertionLayout);
		
		addStudentData.addClickListener(new Button.ClickListener() {
	            public void buttonClick(ClickEvent event) {
	            Student student = new Student(studentName.getValue(),Integer.parseInt(studentAge.getValue()),studentPhoneNumber.getValue());

			studentRepository.save(student);
			Label studentListLabel = new Label("List Of Registered Students");
			studentListLabel.addStyleNames(ValoTheme.LABEL_COLORED);
			
			studentDataInsertionLayout.addComponent(new VerticalLayout(studentListLabel, studentList));
		
            }
			
        });
		
		
		
		deleteStudentData.addClickListener(new Button.ClickListener() {
	            public void buttonClick(ClickEvent event) {
	            
	            	studentDataInsertionLayout.addComponent(new VerticalLayout(studentId,deleteStudentButton));
	     
            	
            }
			
        });
		deleteStudentButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
	            
            	
            	studentRepository.deleteById(Integer.parseInt(studentId.getValue()));
       
        	
        }
		
    });
	
        
	}
	
	
	private void LoginForm(){
		
		TextField userName = new TextField("Enter your Email");
		TextField password = new TextField("Enter your password");
		Button loginButton = new Button("Login");
		loginButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		VerticalLayout loginLayout = new VerticalLayout(userName,password,loginButton);
		loginLayout.setMargin(true);
		loginLayout.setSpacing(true);
		setContent(loginLayout);
		//loginButton.addClickListener(e->Notification.show(" "+auth.AuthenticateUserFromDataBase(userName.getValue(),password.getValue())));
		
		loginButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	if(auth.AuthenticateUserFromDataBase(userName.getValue(),password.getValue())){
            		AddStudentForm();
            	}
            	else{
            		Notification.show("Entered Email or Password is not Valid");
            	}
            }

        });
	}

}
