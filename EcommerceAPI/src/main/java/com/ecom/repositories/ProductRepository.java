package com.ecom.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Products;

public interface ProductRepository extends JpaRepository<Products,Integer>{
	
	public List<Products> findByNameContaining(String query);

}
