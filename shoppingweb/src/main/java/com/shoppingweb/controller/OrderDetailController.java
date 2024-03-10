package com.shoppingweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingweb.model.OrderDetail;
import com.shoppingweb.model.OrderDetailDAO;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
	@Autowired
	OrderDetailDAO dDao;

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
	public List<OrderDetail> createDetail(@ModelAttribute("orderDetail") OrderDetail detail){
		ModelAndView model = new ModelAndView("detailIndex");
		dDao.save(detail);
		return getDetail();
	}
}
