package com.ecom.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom.entities.Users;




public interface UserRepository extends JpaRepository<Users,Integer>{
	
	
	public Optional<Users> getUserByEmailAndPassword(String email,String password);

}
