package com.wellsfargo.sba1.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.wellsfargo.sba1.dao.ProductDAO;
import com.wellsfargo.sba1.dao.ProductDAOJdbcImple;
import com.wellsfargo.sba1.entity.Product;
import com.wellsfargo.sba1.exception.sba1Exception;

public class ProductServiceImple implements ProductService {

	ProductDAO prodDao;
	
	public ProductServiceImple() {
		prodDao = new ProductDAOJdbcImple();
	}

	private boolean isIDValid(Integer id) {
		return id > 0;
	}

	private boolean isNameValid(String Name) {
		return Name != null && (Name.length() >= 3 || Name.length() <= 20);
	}
	
	private boolean isDescriptionValid(String description) {
		return description != null && (description.length() >= 3 || description.length() <= 20);
	}
	
	private boolean isCostValid(Double cost) {
		return cost >= 0;
	}
	
	private boolean isValidProd(Product prod) throws sba1Exception {
		List<String> errMsg = new ArrayList<>();

		boolean isValid = true;

		if (!isIDValid(prod.getId())) {
			isValid = false;
			errMsg.add("ID can not be null or negative or zero");
		}

		if (!isNameValid(prod.getProductName())) {
			isValid = false;
			errMsg.add("Name can not be blank, title must be of 3 to 20 chars in length");
		}

		if (!isCostValid(prod.getCost())) {
			isValid = false;
			errMsg.add("Cost price can not be zero or negative");
		}
		
		if (!isDescriptionValid(prod.getProductDescription())) {
			isValid = false;
			errMsg.add("Description can not be blank, title must be of 3 to 20 chars in length");
		}
		if (!isValid) {
			throw new sba1Exception(errMsg.toString());
		}

		return isValid;
	}

	

	@Override
	public Product ValidateAndAdd(Product prod) throws sba1Exception {
		if(prod!=null) {
			if(isValidProd(prod)) {
				prodDao.addProduct(prod);
			}
		}
		return prod;
	}

	@Override
	public Product ValidateAndSave(Product prod) throws sba1Exception {
		if(prod!=null) {
			if(isValidProd(prod)) {
				prodDao.saveProduct(prod);
			}
		}
		return prod;
	}

	@Override
	public boolean deleteProduct(Integer id) throws sba1Exception {
		return prodDao.deleteByID(id);
	}

	@Override
	public List<Product> getAllProducts() throws sba1Exception {
		return prodDao.listAllProducts();
	}

	@Override
	public Product getProductByID(int id) throws sba1Exception {
		return prodDao.getByID(id);
	}
	

}
