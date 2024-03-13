package com.example.shoppingweb.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.shoppingweb.model.OrderDetail;
import com.example.shoppingweb.model.OrderDetailDAO;
import com.example.shoppingweb.model.SalesOrderDAO;

@RestController
@CrossOrigin("*")
@RequestMapping("/orderDetail")
public class OrderDetailController {
	@Autowired
	OrderDetailDAO dDao;
	@Autowired
	SalesOrderDAO dao;
	@GetMapping("/")
	public ModelAndView gotoIndex() {
		ModelAndView model = new ModelAndView("detailIndex");
		return model;
	}
	
	//search all detail
	@GetMapping("/queryDetail")
	public List<OrderDetail> getDetail() {
		ModelAndView model = new ModelAndView("detailIndex");
		List<OrderDetail> details=dDao.findAll();
		return details;
	}
	
	//search detail by sorderId
	@GetMapping("/queryDetail/{id}")
	public List<OrderDetail> getDetailByOrderid(@PathVariable("id") String id) {
		ModelAndView model = new ModelAndView("detailIndex");
		List<OrderDetail> od=dDao.findBysorderId(id);
		return od;
	}
	
	//create detail
	@PostMapping("/addDetail")
	public List<OrderDetail> createDetail(@RequestBody List<OrderDetail> details){
		ModelAndView model = new ModelAndView("detailIndex");
		String soId=dao.findAll().stream().map(t->t.getSorderId()).toList().stream()
				.max(Comparator.comparing(s->s)).get();
		System.out.print(soId);
		details.stream().forEach(d->d.setSorderId(soId));		
		return getDetail();
	}
}
