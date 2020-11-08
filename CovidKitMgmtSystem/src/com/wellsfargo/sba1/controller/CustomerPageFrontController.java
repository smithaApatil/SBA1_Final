package com.wellsfargo.sba1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellsfargo.sba1.entity.Customer;
import com.wellsfargo.sba1.entity.Product;
import com.wellsfargo.sba1.exception.sba1Exception;
import com.wellsfargo.sba1.service.ProductService;
import com.wellsfargo.sba1.service.ProductServiceImple;


@WebServlet({"/customerlist","/newuser","/customerDetails","/addProdToCart","/placeorder","/ordersummary"})

public class CustomerPageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductService prodService;
    
	@Override
	public void init() throws ServletException {
			prodService = new ProductServiceImple();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getServletPath();
		String viewName="";
		
		switch(url) {
		
			case "/customerlist":viewName = listAllProductsForCustomer(request, response);break;
			case "/newuser": viewName = showNewUserForm(request, response);break;
			case "/customerDetails": viewName = addNewUser(request, response);break;
			case "/addProdToCart" : viewName=showKitDetails(request,response) ; break;
			case "/placeorder" : viewName=showPlaceOrderForm(request,response) ; break;
			case "/ordersummary" : viewName=displayOrderSummary(request,response) ; break;
		}
		
		request.getRequestDispatcher(viewName).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
    public CustomerPageFrontController() {
        super();
    }
    

	private String listAllProductsForCustomer(HttpServletRequest request, HttpServletResponse response) {
		String view = null;
		
		try {
			List<Product> prods = prodService.getAllProducts();
			request.setAttribute("prods", prods);
			view="listproductsForCustomer.jsp";		
		} catch (sba1Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		
		return view;
	}
	
	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		
		return "newuser.jsp";
	} 
	
	
	private String addNewUser(HttpServletRequest request, HttpServletResponse response) {	
		  Customer cust = new Customer();
		  cust.setPersonName(request.getParameter("CustomerName"));
		  cust.setContactNumber(request.getParameter("PhoneNum"));
		  cust.setEmail(request.getParameter("EmaildID"));
		  HttpSession session=request.getSession(); 
			session.setAttribute("newuser","newuser");
		String view;
		try {
			List<Product> prods = prodService.getAllProducts();
			request.setAttribute("prods", prods);
			view="listproductsForCustomer.jsp";		
//			cust = (Customer) session.getAttribute("cust");
			session.setAttribute("cust", cust);
		} catch (sba1Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}

		return view;
		
	}
	
	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) {
		String view;
		try {
			Product prod = prodService.getProductByID(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			if (session.getAttribute("cart") == null) {
				List<Product> cart = new ArrayList<Product>();
				 cart.add(prod);
				 session.setAttribute("cart", cart);
				
			} else {
				List<Product> cart = (List<Product>) session.getAttribute("cart");
				cart = (List<Product>)session.getAttribute("cart"); 
				cart.add(prod);
				session.setAttribute("cart", cart);
			}
		}catch (sba1Exception   e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		
		return "kitDetails.jsp";
		
	}
	
	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
	/*	HttpSession session = request.getSession();

		List<Product> cart = (List<Product>) request.getAttribute("cart");
		request.setAttribute("cart", cart);*/
		return "placeorder.jsp";
	}
	

	private String displayOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		
		return "ordersummary.jsp";
	}
    
}
