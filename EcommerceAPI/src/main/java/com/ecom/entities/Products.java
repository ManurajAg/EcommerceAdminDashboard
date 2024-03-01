package com.ecom.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Products {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	private String name;
	private String description;
	private String price;
	private String filePath;
	public Products(int pid, String name, String description, String price, String filePath) {
		super();
		this.pid = pid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.filePath = filePath;
	}
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
}
