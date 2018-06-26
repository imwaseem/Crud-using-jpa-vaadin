package com.example.demo.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AuthenticateUser;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")

public class UserLoginForm extends UI {

	@Autowired
	AuthenticateUser auth;
	@Override
	protected void init(VaadinRequest request) {
	
		TextField userName = new TextField("Enter your Email");
		TextField password = new TextField("Enter your password");
		Button loginButton = new Button("Login");
		VerticalLayout loginLayout = new VerticalLayout(userName,password,loginButton);
		loginLayout.setMargin(true);
		loginLayout.setSpacing(true);
		setContent(loginLayout);
		loginButton.addClickListener(e->Notification.show(" "+auth.AuthenticateUserFromDataBase(userName.getValue(),password.getValue())));
		

	}

}
