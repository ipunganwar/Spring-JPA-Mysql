package com.ipung.training.db.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipung.training.db.model.Users;
import com.ipung.training.db.repository.UsersRepository;

@RestController
@RequestMapping(value="/rest/users")
public class UsersResources {
	
	@Autowired
	private UsersRepository ur;
	
	@GetMapping(value="/all")
	private List<Users> getAll(){
		return ur.findAll();	
	}
	
	@PostMapping(value="/load")
	private List<Users> insert(@RequestBody final Users users){
		ur.save(users);
		return ur.findAll();
	}

}
