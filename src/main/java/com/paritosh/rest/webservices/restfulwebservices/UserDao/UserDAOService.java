package com.paritosh.rest.webservices.restfulwebservices.UserDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.paritosh.rest.webservices.restfulwebservices.bean.Users;

@Component
public class UserDAOService {

	private static List<Users> usersList = new ArrayList<>();
	private int userCount=3;
	
	static{
		usersList.add(new Users(1, "A", new Date()));
		usersList.add(new Users(2, "B", new Date()));
		usersList.add(new Users(3, "C", new Date()));
	}
	
	
	public List<Users> findAll(){
		return this.usersList;
	}
	
	public Users save(Users users){
		if(users.getUserId()==null){
			this.userCount+=1;
			users.setUserId(userCount);
		}
		this.usersList.add(users);
		return users;
	}
	
	public Users find(int id){
		for(Users user : usersList){
			if(user.getUserId()==id)
				return user;
		}
		return null;
	}
	
}
