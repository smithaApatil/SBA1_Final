package com.wellsfargo.sba1.dao;

import java.util.List;

import javax.naming.NamingException;

import com.wellsfargo.sba1.entity.Product;
import com.wellsfargo.sba1.exception.sba1Exception;

public interface ProductDAO {

	
		Product addProduct(Product prod) throws sba1Exception;
		Product saveProduct(Product prod) throws sba1Exception;
		List<Product> listAllProducts() throws sba1Exception;
		boolean deleteByID(Integer id) throws sba1Exception;
		Product getByID(Integer id)throws sba1Exception;
		
}
