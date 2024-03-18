package com.example.shoppingweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.example.shoppingweb.model.OrderDetail;
import com.example.shoppingweb.model.OrderDetailDAO;
import com.example.shoppingweb.model.Product;
import com.example.shoppingweb.model.ProductDAO;
import com.example.shoppingweb.model.ProductService;
import com.example.shoppingweb.model.SalesOrder;
import com.example.shoppingweb.model.SalesOrderDAO;
import com.example.shoppingweb.model.SalesOrderService;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.net.http.HttpRequest;
import java.util.*;
@RestController
@RequestMapping("/sales")
@CrossOrigin("*")
public class SalesOrderController {
	@Autowired
	SalesOrderDAO dao;
	@Autowired
	OrderDetailDAO dDao;	
	@Autowired
	ProductDAO pDao;		

	
	//search all order
	@GetMapping("/querySales")
	public List<SalesOrder> getAll() {
		
		List<SalesOrder> sales=dao.findAll();
		
		return sales;
	}
	
	@GetMapping("/querySalesById/{memberId}")
	public List<SalesOrder> getBymemberid(@PathVariable("memberId")Integer memberId) {
		
		List<SalesOrder> sales=dao.findBymemberId(memberId);
		
		return sales;
	}
	
	//search order by order status 
	@GetMapping("/querySalesByStatus/{status}")
	public List<SalesOrder> getByStatus(@PathVariable("status")String status) {
		
		List<SalesOrder> sales=dao.findBystatusCode(status);
		
		return sales;
	}
	
	//search order by order date
	@GetMapping("/querySalesByDate/{date}")
	public List<SalesOrder> getByDate(@PathVariable("date")String date) {
		
		String d=date.replace("-","")+"%";		
		List<SalesOrder> sales=dao.findBygenerateDateLike(d);
		
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
	public SalesOrder createSales(@ModelAttribute SalesOrder sales) {
//		ModelAndView model=new ModelAndView("salesIndex");
		String sId=SalesOrderService.calculateSalesOrderNum(SalesOrderService.getCurrentDate(),dao.findAll());
		sales.setSorderId(sId);
		sales.setGenerateDate(SalesOrderService.getCurrentDateTime());		
		//dao.save(sales);
//		List<OrderDetail> details=new ArrayList<>();
//		int index=1;
//		for(OrderDetail c:sales.getOrderDetails()) {			
//			c.setSdetailId(sId+index);
//			c.setSorderId(sId);
//			c.setSIndex(index++);			
//			dDao.save(c);
//		}		
		dao.save(sales);
		return sales;
	}
	
	//change order status to specific by be selected
	@PostMapping("/updateStatus/{status}/{uid}")
    public List<SalesOrder> updateSalesStatus(@RequestBody List<String> sorderIds, @PathVariable("status") String status,@PathVariable("uid")String uid) {
        for (String sorderId : sorderIds) {
            SalesOrder salesOrder = dao.findById(sorderId).orElse(null);
            if (salesOrder != null) {
            	salesOrder.setEmployeeId(uid);
                salesOrder.setStatusCode(status);
                if(status.equals("完成")) {
                salesOrder.setComplateDate(SalesOrderService.getCurrentDateTime());	
                }
                if(status.equals("作廢")) {
                salesOrder.setInvalidDate(SalesOrderService.getCurrentDateTime());	
                }
                dao.save(salesOrder);
            }
        }
        return dao.findAll(); 
    }
	@PostMapping("/updateAddress/{sorderId}")
	public void updateAddress(@PathVariable("sorderId") String sorderId, @RequestParam("address") String address) {
	    SalesOrder salesOrder = dao.findById(sorderId).orElse(null);
	    if (salesOrder != null) {
	        salesOrder.setReceiveAddress(address);
	        dao.save(salesOrder);
	    }
	}
	@PostMapping("/updateDetail/{id}")
	public void updateAllDetailByid(@PathVariable("sorderId") String sorderid,@RequestBody List<OrderDetail> details) {
		SalesOrder order =  dao.findById(sorderid).get();
		order.setOrderDetails(details);
		 dao.save(order);
	}

	
	//create cart
//	@GetMapping("/addCart/{pid}")
//	public  List<OrderDetail> addCartById(@PathVariable("pid") String pid,HttpSession session) {		
//		List<OrderDetail> details;	
//		
//		if(session.getAttribute("cart")==null) 
//		{
//			details=new ArrayList<>();
//			Product p=pDao.findById(pid).get();
//			OrderDetail od=new OrderDetail();
//			od.setProductId(p.getProductid());
//			od.setPname(p.getPname());
//			od.setQuantity(1);
//			od.setUnitPrice(p.getUnitprice());			
//			od.setSubTotal(p.getUnitprice());
//			details.add(od);
//			session.setAttribute("cart",details);
//		}
//		else 
//		{
//			details=(List<OrderDetail>)session.getAttribute("cart");
//			OrderDetail od1 = details.stream().filter(d->d.getProductId().equals(pid)).findAny().orElse(null);
//			if(od1!=null)
//			{
//				od1.setQuantity(od1.getQuantity()+1);
//			}
//			else
//			{
//				Product p=pDao.findById(pid).get();
//				OrderDetail od=new OrderDetail();
//				od.setProductId(p.getProductid());
//				od.setPname(p.getPname());
//				od.setQuantity(1);
//				od.setUnitPrice(p.getUnitprice());			
//				od.setSubTotal(p.getUnitprice());
//				details.add(od);				
//			}	
//			session.setAttribute("cart",details);
//		}
//		
//		return details;
//	}

}
