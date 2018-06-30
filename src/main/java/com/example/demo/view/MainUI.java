package com.example.demo.view;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
	Label booksListLabel = new Label("List Of Registered Books");
	VerticalLayout list = new VerticalLayout();
	Label studentListLabel = new Label("List Of Registered Students");
	@Override
	protected void init(VaadinRequest request) {

		LoginForm();

	}


	private void AddBookForm(){
		Button bookMenuButton = new Button("Book");
		bookMenuButton.addStyleName(ValoTheme.BUTTON_DANGER);
		Button updateBookButton = new Button("Update Book");
		Button update = new Button("Update");
		Button Home = new Button("Home");
		updateBookButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);

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
		HorizontalLayout horizontalLayout = new HorizontalLayout(addBookData,updateBookButton,deleteBookData,booksListButton,Home);

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
		Home.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				menu();

			}

		});
		bookMenuButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				AddBookForm(); 

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
		updateBookButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				studentDataInsertionLayout.removeAllComponents();
				studentDataInsertionLayout.addComponents(header,bookId,bookName,bookAuthor,update,bookMenuButton);
				booksList.init();
				studentDataInsertionLayout.addComponent(new VerticalLayout(booksListLabel, booksList));



			}

		});
		deleteBookButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {


				bookRepository.deleteById(Integer.parseInt(bookId.getValue()));


			}

		});
		update.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {

				Book book = new Book(Integer.parseInt(bookId.getValue()),bookName.getValue(),bookAuthor.getValue());

				bookRepository.save(book);

				studentDataInsertionLayout.removeAllComponents();
				studentDataInsertionLayout.addComponents(header,bookName,bookAuthor,horizontalLayout);
				booksList.init();
				studentDataInsertionLayout.addComponent(new VerticalLayout(booksListLabel, booksList));

			}});


	}
	private void AddStudentForm()
	{
		Button studentMentButton = new Button("Student");
		studentMentButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		Button Home = new Button("Home");

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
		HorizontalLayout horizontalLayout = new HorizontalLayout(addStudentData,updateStudentButton,deleteStudentData,studentsListButton,Home);

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
		studentMentButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				AddStudentForm(); 

			}
		});
		Home.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				menu();

			}

		});
		updateStudentButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				studentDataInsertionLayout.removeAllComponents();
				studentDataInsertionLayout.addComponents(header,studentId,studentName,studentAge,studentPhoneNumber,update,studentMentButton);
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
		studentMentButton.setWidth("300");
		Button bookMenuButton = new Button("Book");
		bookMenuButton.addStyleName(ValoTheme.BUTTON_DANGER);
		bookMenuButton.setWidth("500");
		bookMenuButton.setWidth("300");
		Button issueBook = new Button("Issued Books List");
		issueBook.setWidth("500");
		issueBook.setWidth("300");
		VerticalLayout menuLayout = new VerticalLayout(studentMentButton,bookMenuButton,issueBook);
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
		issueBook.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				issueBookForm();

			}
		});

	}
	private void issueBookForm(){

		TextField studentId = new TextField("Enter Student ID");
		TextField bookId = new TextField("Enter Book ID");
		Button Home = new Button("Home");
		Button issue = new Button("Issue Book");

		issue.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		list.removeAllComponents();
		studentList.init();
		booksList.init();
		list.addComponents(new VerticalLayout(studentId,bookId,new HorizontalLayout (issue,Home),studentListLabel, studentList,booksListLabel,booksList));
		setContent(list);

		issue.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {

				if(studentId.getValue() != null && bookId.getValue() != null){
				Optional<Student> std = studentList.findStudentById(Integer.parseInt(studentId.getValue()));
				Optional<Book> b = booksList.findBookById(Integer.parseInt(bookId.getValue()));
				if(std.isPresent()&&b.isPresent()){
					Student student =std.get();
					Book book = b.get();
					Book bb = new Book();
					bb.setOid(book.getOid());
					bb.setName(book.getName());
					bb.setAuthor(book.getAuthor());
					Student std1 = new Student();

					std1.setOid(student.getOid());
					std1.setAge(student.getAge());
					std1.setPhone(student.getPhone());
					std1.setBooks(student.getBooks());
					std1.setName(student.getName());

					std1.getBooks().add(bb);

					studentRepository.save(std1);
				
				}
				else if(std.isPresent()){
					Notification.show("Invalid Student Id");	
				}
				else if(b.isPresent()){
					Notification.show("Invalid Book Id");
				}
				else{
					Notification.show("Invalid userId and Book Id");
				}

			}
			}
		});
		Home.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				menu();

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
					loginLayout.removeAllComponents();
					menu();


				}
				else{
					Notification.show("Entered Email or Password is not Valid");
				}
			}

		});

	}


}
