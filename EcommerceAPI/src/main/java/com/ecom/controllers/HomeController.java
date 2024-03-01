package com.ecom.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.entities.Products;
import com.ecom.entities.Response;
import com.ecom.entities.Users;
import com.ecom.repositories.ProductRepository;
import com.ecom.repositories.UserRepository;

@CrossOrigin(origins = "*")
@RestController
public class HomeController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody Users user){
		
		user = userRepo.save(user);
		Optional<Users> o = Optional.of(user);
		if(user!=null)	return ResponseEntity.of(o);
		else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Users user){
		
		Optional<Users> u = userRepo.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
		if(u!=null)	return ResponseEntity.of(Optional.of(u));	
		else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@ModelAttribute Products product,@RequestPart("image") MultipartFile file) throws URISyntaxException{
		if (file.isEmpty()) {
            // Handle empty file
            return ResponseEntity.badRequest().build();
        }
		
		try {
			String directoryPath = "src/main/resources/static/image";
            Path directory = Paths.get(directoryPath);
            
            // Create the directory if it doesn't exist
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Resolve the file path within the directory
            Path filePath = directory.resolve(file.getOriginalFilename());
            // Copy the file to the target directory
            
            product.setFilePath(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            productRepo.save(product);
		}
            catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.of(Optional.of(product));
		
	}
	
	
	@GetMapping("/getProducts")
	public ResponseEntity<List<Products>> getProducts(){
		List<Products> allProducts = productRepo.findAll();
		if(allProducts.size()<0) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.of(Optional.of(allProducts));
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id){
		if(productRepo.existsById(id)) {
			productRepo.deleteById(id);
			Response r = new Response("Deleted Successfully");
			return ResponseEntity.of(Optional.of(r));
		}
		Response r = new Response("No Such Item Found");
		return ResponseEntity.of(Optional.of(r));
		
	}
	
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") int id){
		Optional<Products> product = productRepo.findById(id);
		if(!product.isEmpty()) {
			return ResponseEntity.of(product);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/search/{query}")
	public ResponseEntity<?> searchProducts(@PathVariable("query")String query){
		List<Products> result = productRepo.findByNameContaining(query);
		
		return ResponseEntity.of(Optional.of(result));
	}

}
