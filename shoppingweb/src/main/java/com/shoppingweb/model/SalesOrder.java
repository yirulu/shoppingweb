package com.shoppingweb.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="salesorder")
public class SalesOrder {	
	@Id
	@Column(name="sorder_id")
	private String sorderId;//NN
	@Column(name="member_id")
	private String memberId;//NN
	@Column(name="generate_date")
	private String generateDate;//NN
	@Column(name="receive_address")
	private String receiveAddress;//NN
	@Column(name="status_code")
	private String statusCode="SOS_P";//NN
	@Column(name="ship_date")
	private Date shipDate;
	@Column(name="sorder_price")
	private Integer sorderPrice;//NN
	@Column(name="complate_date")
	private Date complateDate;
	@Column(name="employee_id")
	private String employeeId;
	
	@OneToMany(cascade= {CascadeType.ALL})
	@JoinColumn(name="sorder_id")
	private List<OrderDetail> orderDetails;
	
	
	
	public SalesOrder( String memberId, String receiveAddress,Integer sorderPrice) {		
		this.memberId = memberId;
//		this.generateDate = SalesOrderService.getCurrentDateTime();
		this.receiveAddress = receiveAddress;		
		this.sorderPrice = sorderPrice;
	}
	
	
}
