package com.example.shoppingweb.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductDAO dao;

	public Page<Product> getUsersPageable(int page) {
		Pageable pageable = PageRequest.of(page, 5); // 每頁顯示 5 筆資料
		return dao.findAll(pageable);
	}

	//查詢所有商品清單
	public List<Product> getAllProducts() {
		return dao.findAll();
	}

	//查詢所有商品by類別id
	public List<Product> getAllProductsByPtypeid(String ptypeid) {
		return dao.findByPtypeid(ptypeid);
	}

	//查詢所有商品by名稱
	public List<Product> getAllProductsByPname(String pname) {
		return dao.findByPname(pname);
	}
	
	//新增商品
	public void addProduct(Product product) {
		dao.save(product);
	}
	
	//修改商品
	public void updateProduct(Product product )throws Exception {
		String productid=product.getProductid();
		
		boolean productExists=dao.existsByProductid(productid);
		
		if(productExists) {
			Product existingProduct=dao.findByProductid(productid);
			existingProduct.setPname(product.getPname());
			existingProduct.setPqty(product.getPqty());
			existingProduct.setPtext(product.getPtext());
			existingProduct.setUnitprice(product.getUnitprice());
			dao.save(existingProduct);
		}else {
			throw new Exception("Product does not exist");
		}	
	}
	
	//刪除商品
	public void deleteProductBtId(String productid)throws Exception{
		
		boolean productExists=dao.existsByProductid(productid);
		
		if(productExists) {
			dao.deleteById(productid);
		}else {
			throw new Exception("Product does not exist");
		}
	}
	
	
	
	
	
}
	
