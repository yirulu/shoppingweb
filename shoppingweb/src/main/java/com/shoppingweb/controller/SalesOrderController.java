package com.shoppingweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingweb.model.OrderDetailDAO;
import com.shoppingweb.model.SalesOrder;
import com.shoppingweb.model.SalesOrderDAO;
import com.shoppingweb.model.SalesOrderService;

import java.util.*;
@RestController
@RequestMapping("/sales")
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
	
	//search order by order status 
	@GetMapping("/querySales/{status}")
	public List<SalesOrder> getByStatus(@PathVariable("status")String status) {
		ModelAndView model=new ModelAndView("salesIndex");
		List<SalesOrder> sales=dao.findBystatusCode(status);
		model.addObject("sales",sales);
		return sales;
	}
	
	//search order by order date
	@GetMapping("/querySales/{date}")
	public List<SalesOrder> getByDate(@PathVariable("date")String date) {
		ModelAndView model=new ModelAndView("salesIndex");
		List<SalesOrder> sales=dao.findBygenerateDate(date);
		model.addObject("sales",sales);
		return sales;
	}	
	
	//create a order
	@PostMapping("/addSales")
	public List<SalesOrder> createSales(@ModelAttribute("SalesOrder") SalesOrder sales) {
//		ModelAndView model=new ModelAndView("salesIndex");
		String sId=SalesOrderService.calculateSalesOrderNum(SalesOrderService.getCurrentDate(),dao.findAll());
		sales.setSorderId(sId);
		sales.setGenerateDate(SalesOrderService.getCurrentDateTime());		
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
