package com.example.shoppingweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.example.shoppingweb.model.OrderDetail;
import com.example.shoppingweb.model.OrderDetailDAO;
import com.example.shoppingweb.model.Product;
import com.example.shoppingweb.model.SalesOrder;
import com.example.shoppingweb.model.SalesOrderDAO;
import com.example.shoppingweb.model.SalesOrderService;

import java.util.*;
@RestController
@RequestMapping("/sales")
@CrossOrigin("*")
public class SalesOrderController {
	@Autowired
	SalesOrderDAO dao;
	@Autowired
	OrderDetailDAO dDao;
	@GetMapping("/")
	public ModelAndView gotoIndex() {
		ModelAndView model=new ModelAndView("salesIndex");
		return model;
	}
	
	//search all order
	@GetMapping("/querySales")
	public List<SalesOrder> getAll() {
		ModelAndView model=new ModelAndView("salesIndex");
		List<SalesOrder> sales=dao.findAll();
		model.addObject("sales",sales);
		return sales;
	}
	
	@GetMapping("/querySalesById/{memberId}")
	public List<SalesOrder> getBymemberid(@PathVariable("memberId")Integer memberId) {
		ModelAndView model=new ModelAndView("querySalesById");
		List<SalesOrder> sales=dao.findBymemberId(memberId);
		model.addObject("sales",sales);
		return sales;
	}
	
	//search order by order status 
	@GetMapping("/querySalesByStatus/{status}")
	public List<SalesOrder> getByStatus(@PathVariable("status")String status) {
		ModelAndView model=new ModelAndView("salesIndex");
		List<SalesOrder> sales=dao.findBystatusCode(status);
		model.addObject("sales",sales);
		return sales;
	}
	
	//search order by order date
	@GetMapping("/querySalesByDate/{date}")
	public List<SalesOrder> getByDate(@PathVariable("date")String date) {
		ModelAndView model=new ModelAndView("salesIndex");
		String d=date.replace("-","")+"%";		
		List<SalesOrder> sales=dao.findBygenerateDateLike(d);
		model.addObject("sales",sales);
		return sales;
	}
	
	//search order by order date
		@GetMapping("/querysorderId/{sorderId}")
		public SalesOrder getBySorderId(@PathVariable("sorderId") String sorderId) {
		    SalesOrder sales = dao.findById(sorderId).get();
		    return sales;
		}

	
	//create a order & orderDetail
	@PostMapping("/addSales")
	public List<SalesOrder> createSales(@RequestBody SalesOrder sales) {
//		ModelAndView model=new ModelAndView("salesIndex");
		String sId=SalesOrderService.calculateSalesOrderNum(SalesOrderService.getCurrentDate(),dao.findAll());
		sales.setSorderId(sId);
		sales.setGenerateDate(SalesOrderService.getCurrentDateTime());		
		//dao.save(sales);
//		List<OrderDetail> details=new ArrayList<>();
		int index=1;
		for(OrderDetail c:sales.getOrderDetails()) {			
			c.setSdetailId(sId+index);
			c.setSorderId(sId);
			c.setSIndex(index++);			
			dDao.save(c);
		}		
		dao.save(sales);
		return getAll();
	}
	
	//change order status to specific by be selected
	@PostMapping("/updateStatus/{status}")
	public List<SalesOrder> updateSales(@RequestBody List<SalesOrder>sales,@PathVariable("status") String status ){
		//ModelAndView model=new ModelAndView("salesIndex");
		List<SalesOrder>update= sales.stream().map(s->
				{s.setStatusCode(status);
				return s;}).toList();
		update.stream().forEach(u->dao.save(u));
		return getAll();
	}

}
