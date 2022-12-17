package com.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "category_id")
	private String productName;
	
	private String category_id;
	private String price;
	private String quantity;
	
	@OneToMany(casecade = Casecade.All)
	@joinColumn(name = "fk_category_id",referencedColumnName="category_id")

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String productName, String category_id, String price, String quantity) {
		super();
		this.productName = productName;
		this.category_id = category_id;
		this.price = price;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", productName=" + productName + ", category_id=" + category_id + ", price="
				+ price + ", quantity=" + quantity + "]";
	}

}
