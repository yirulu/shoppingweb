package com.example.shoppingweb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.shoppingweb.model.*;

@RestController
public class ProductController {

	@Autowired
	ProductService srv;

	//查詢所有商品清單
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return srv.getAllProducts();
	}
	
	//查詢所有商品by類別id
	@GetMapping("/products/ptypeid")
	public List<Product> getAllProductsByPtypeid(@RequestParam String ptypeid) {
		return srv.getAllProductsByPtypeid(ptypeid);
	}

	//查詢所有商品by名稱
	@GetMapping("/products/bypname")
	public List<Product> getAllProductsByPname(@RequestParam String pname) {
		return srv.getAllProductsByPname(pname);
	}
	
	//新增商品
	@PostMapping("/products/add")
	public ResponseEntity<String> addProduct(@RequestBody Product product ){
		try {
			srv.addProduct(product);
			return ResponseEntity.ok("Product added successfully");		
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("Product ID is not unique");
		}			
	}
	
	//修改商品
	@PostMapping("/products/update")
	public ResponseEntity<String> updateProduct(@RequestBody Product product ){
		try {
			srv.updateProduct(product);
            return ResponseEntity.ok("Product updated successfully"); 	
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("Product ID is not unique");
		}
                     
	}	
	
	//刪除商品
	@DeleteMapping("/products/{productid}")
	public ResponseEntity<String> deleteProductBtId(@PathVariable String productid){
		try {
			srv.deleteProductBtId(productid);
			return ResponseEntity.ok("Product deleted successfully");
		}catch (Exception e){
			return ResponseEntity.badRequest().body("Error deleting product:"+e.getMessage());
		}
		
	
		
	}
}
