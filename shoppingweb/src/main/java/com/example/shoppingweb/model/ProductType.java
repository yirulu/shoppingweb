package com.example.shoppingweb.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "producttype")

public class ProductType {

	@Id
	private String ptypeid;
	private String ptypename;
	private String employeeid;
	private Boolean enabled;

	@OneToMany
	@JoinColumn(name = "ptypeid")
	private List<Product> products;

	public ProductType() {

	}

	public ProductType(String ptypeid, String ptypename, String employeeid, Boolean enabled, List<Product> products) {
		this.ptypeid = ptypeid;
		this.ptypename = ptypename;
		this.employeeid = employeeid;
		this.enabled = enabled;
		this.products = products;
	}

	public String getPtypeid() {
		return ptypeid;
	}

	public void setPtypeid(String ptypeid) {
		this.ptypeid = ptypeid;
	}

	public String getPtypename() {
		return ptypename;
	}

	public void setPtypename(String ptypename) {
		this.ptypename = ptypename;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
