package com.example.demo.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AuthenticateUser;
import com.example.demo.model.Book;
import com.example.demo.model.Login;
import com.example.demo.model.Student;
import com.example.demo.repositories.BookRepository;
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
	@Autowired
	BookList booksList;
	@Autowired
	BookRepository bookRepository;

	@Override
	protected void init(VaadinRequest request) {
	          	
		LoginForm();

	}
	
	
	private void AddBookForm(){
		
    	Label booksListLabel = new Label("List Of Registered Books");
    	booksListLabel.addStyleNames(ValoTheme.LABEL_COLORED);
		Label header = new Label("Enter the Data To Add New Books");
		header.addStyleName(ValoTheme.LABEL_H1);
		TextField bookId = new TextField("Book ID");
		Button deleteBookButton = new Button("Delete Book");
		deleteBookButton.setIcon(FontAwesome.REMOVE);
		deleteBookButton.addStyleName(ValoTheme.BUTTON_DANGER);
    	//studentId.setCaption("User ID");
		TextField bookName = new TextField("Name");
		TextField bookAuthor= new TextField("Author");
		
	
		Button addBookData = new Button("Add");
		addBookData.addStyleName(ValoTheme.BUTTON_PRIMARY);
		Button deleteBookData = new Button("delete");
		deleteBookData.addStyleName(ValoTheme.BUTTON_DANGER);
		Button booksListButton = new Button("Show All Books");
		booksListButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		HorizontalLayout horizontalLayout = new HorizontalLayout(addBookData,deleteBookData,booksListButton);

		VerticalLayout studentDataInsertionLayout = new VerticalLayout(header,bookName,bookAuthor,horizontalLayout);
		studentDataInsertionLayout.setMargin(true);
		studentDataInsertionLayout.setSpacing(true);
		
		setContent(studentDataInsertionLayout);
		
		addBookData.addClickListener(new Button.ClickListener() {
	            public void buttonClick(ClickEvent event) {
	            Book book = new Book(bookName.getValue(),bookAuthor.getValue());

			bookRepository.save(book);
			
			studentDataInsertionLayout.removeAllComponents();
			
			studentDataInsertionLayout.addComponents(header,bookName,bookAuthor,horizontalLayout);
			
		
            }
			
        });
		
		booksListButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
 
             	studentDataInsertionLayout.removeAllComponents();
    			
    			studentDataInsertionLayout.addComponents(header,bookName,bookAuthor,horizontalLayout);
    			booksList.init();
            	studentDataInsertionLayout.addComponent(new VerticalLayout(booksListLabel, booksList));
        	
        }
		
    });
		
		deleteBookData.addClickListener(new Button.ClickListener() {
	            public void buttonClick(ClickEvent event) {
	           
	            	studentDataInsertionLayout.addComponent(new VerticalLayout(bookId,deleteBookButton));
	     
            	
            }
			
        });
		deleteBookButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
	            
            	
            	bookRepository.deleteById(Integer.parseInt(bookId.getValue()));
       
        	
        }
		
    });
	
        
	}
	private void AddStudentForm(){
    	Label studentListLabel = new Label("List Of Registered Students");
		studentListLabel.addStyleNames(ValoTheme.LABEL_COLORED);
		Label header = new Label("Enter the Data To Add New Student");
		header.addStyleName(ValoTheme.LABEL_H1);
		TextField studentId = new TextField("Student ID");
		Button deleteStudentButton = new Button("Delete Student");
		//TextField bookId1 = new TextField("book1 ");
		//TextField bookId2= new TextField("book2");
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
		Button studentsListButton = new Button("Show All Students");
		studentsListButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		Button updateStudentButton = new Button("Update Student");
		Button update = new Button("Update");
		
		updateStudentButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		HorizontalLayout horizontalLayout = new HorizontalLayout(addStudentData,updateStudentButton,deleteStudentData,studentsListButton);

		VerticalLayout studentDataInsertionLayout = new VerticalLayout(header,studentName,studentAge,studentPhoneNumber,horizontalLayout);
		studentDataInsertionLayout.setMargin(true);
		studentDataInsertionLayout.setSpacing(true);
		
		setContent(studentDataInsertionLayout);
		
		addStudentData.addClickListener(new Button.ClickListener() {
	            public void buttonClick(ClickEvent event) {
	            Student student = new Student(studentName.getValue(),Integer.parseInt(studentAge.getValue()),studentPhoneNumber.getValue());

			studentRepository.save(student);
			
			studentDataInsertionLayout.removeAllComponents();
			
			studentDataInsertionLayout.addComponents(header,studentName,studentAge,studentPhoneNumber,horizontalLayout);
		
            }
			
        });
		
		updateStudentButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	studentDataInsertionLayout.removeAllComponents();
        		studentDataInsertionLayout.addComponents(header,studentId,studentName,studentAge,studentPhoneNumber,update);
    			studentList.init();
            	studentDataInsertionLayout.addComponent(new VerticalLayout(studentListLabel, studentList));
      
        }
		
    });
	  	
     	update.addClickListener(new Button.ClickListener() {
        public void buttonClick(ClickEvent event) {


		
        	   Student student = new Student(Integer.parseInt(studentId.getValue()),studentName.getValue(),Integer.parseInt(studentAge.getValue()),studentPhoneNumber.getValue());

        		studentRepository.save(student);
        		
        		studentDataInsertionLayout.removeAllComponents();
        		
        		studentDataInsertionLayout.addComponents(header,studentName,studentAge,studentPhoneNumber,horizontalLayout);
        		studentList.init();
            	studentDataInsertionLayout.addComponent(new VerticalLayout(studentListLabel, studentList));
      
        }
    
	
});
		studentsListButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
 
             	studentDataInsertionLayout.removeAllComponents();
    			
    			studentDataInsertionLayout.addComponents(header,studentName,studentAge,studentPhoneNumber,horizontalLayout);
    			studentList.init();
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
	
	private void menu(){
		Button studentMentButton = new Button("Student");
		studentMentButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		studentMentButton.setWidth("500");
		Button bookMenuButton = new Button("Book");
		bookMenuButton.addStyleName(ValoTheme.BUTTON_DANGER);
		bookMenuButton.setWidth("500");
		VerticalLayout menuLayout = new VerticalLayout(studentMentButton,bookMenuButton);
		setContent(menuLayout);
		studentMentButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
	           AddStudentForm(); 
           
            }
        });
		bookMenuButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
	           AddBookForm(); 
           
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
		loginButton.addClickListener(e->Notification.show(" "+auth.AuthenticateUserFromDataBase(userName.getValue(),password.getValue())));
		
		loginButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	if(auth.AuthenticateUserFromDataBase(userName.getValue(),password.getValue())){
            	//	AddStudentForm();
            	//	AddBookForm();
            		menu();
            		
            	}
            	else{
            		Notification.show("Entered Email or Password is not Valid");
            	}
            }

        });
	}

}
