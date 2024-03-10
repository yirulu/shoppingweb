package com.shoppingweb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orderdetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
	@Id
	@Column(name="sdetail_id")
	private String sdetailId;//NN
	@Column(name="sorder_id")
	private String sorderId;//NN FK from salesOrder
	@Column(name="s_index")
	private Integer sIndex;//NN
	@Column(name="product_id")
	private String productId;//NN FK from product
	@Column(name="unit_price")
	private Integer unitPrice;//NN
	private Integer quantity;//NN
	@Column(name="sub_total")
	private Integer subTotal;//NN
}
