package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.repositories.LoginRepository;
import com.example.demo.repositories.UserRepository;

@Component
public class AuthenticateUser {
	

	@Autowired
	LoginRepository loginRepository;
	public boolean AuthenticateUserFromDataBase(String UserName, String Password){
		

		System.out.println("field="+UserName +" field="+Password);
			
				System.out.println("Printing Data From DataBase");
				for(Login user : loginRepository.findAll()){
					System.out.println("db="+user.getEmail()+" db="+user.getPassword());
					if(UserName.equalsIgnoreCase(user.getEmail()) && Password.equalsIgnoreCase(user.getPassword()) ){
						return true;
					}
				}
		
			
	return false;
		
	}
	

}
