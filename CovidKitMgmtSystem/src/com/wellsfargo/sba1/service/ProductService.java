package com.wellsfargo.sba1.service;

import java.util.List;

import javax.naming.NamingException;

import com.wellsfargo.sba1.entity.Product;
import com.wellsfargo.sba1.exception.sba1Exception;

public interface ProductService {

		Product ValidateAndAdd(Product prod) throws sba1Exception;
		Product ValidateAndSave(Product prod) throws sba1Exception;
		boolean deleteProduct(Integer id) throws sba1Exception;
		List<Product> getAllProducts() throws sba1Exception;
		Product getProductByID(int id) throws sba1Exception;
}
