package com.wellsfargo.sba1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import com.wellsfargo.sba1.dao.DBConnection;
import com.wellsfargo.sba1.entity.Product;
import com.wellsfargo.sba1.exception.sba1Exception;

public class ProductDAOJdbcImple implements ProductDAO {

	
	public static final String INS_PRODUCT_QRY = "INSERT INTO CORONAKITPRODUCTS(id,productName,cost,productDescription) values(?,?,?,?)";
	public static final String UPD_PRODUCT_QRY = "UPDATE CORONAKITPRODUCTS SET productName=?,cost=?,productDescription=? WHERE id=?";
	public static final String DEL_PRODUCT_QRY = "DELETE FROM CORONAKITPRODUCTS WHERE id=?";
	public static final String SEL_PRODUCT_QRY_BY_ID = "SELECT id,productName,cost,productDescription FROM CORONAKITPRODUCTS WHERE id=?";
	public static final String SEL_ALL_PRODUCTS_QRY = "SELECT id,productName,cost,productDescription FROM CORONAKITPRODUCTS";
	
	
	public Product addProduct(Product prod) throws sba1Exception  {	
		if (prod != null) {
			try (Connection con = DBConnection.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_PRODUCT_QRY)) {

				pst.setInt(1, prod.getId());
				pst.setString(2, prod.getProductName());
				pst.setDouble(3, prod.getCost());
				pst.setString(4, prod.getProductDescription());
				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new sba1Exception("Saving the item failed!");
			}
		}
		return prod;
		
	}

	public Product saveProduct(Product prod) throws sba1Exception {
		if (prod != null) {
			try (Connection con = DBConnection.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_PRODUCT_QRY)) {							
				pst.setString(1, prod.getProductName());
				pst.setDouble(2, prod.getCost());
				pst.setString(3, prod.getProductDescription());	
				pst.setLong(4, prod.getId());
				pst.executeUpdate();
			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new sba1Exception("Saving the item failed!");
			}
		}
		return prod;
	}

	public List<Product> listAllProducts() throws sba1Exception {
		List<Product> prods = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ALL_PRODUCTS_QRY)) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt(1));
				prod.setProductName(rs.getString(2));
				prod.setCost(rs.getDouble(3));
				prod.setProductDescription(rs.getString(4));
				
				prods.add(prod);
			}
			
			if(prods.isEmpty()) {
				prods=null;
			}
		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new sba1Exception("Retrival the item failed!");
		}
		return prods;
	}

	public boolean deleteByID(Integer id) throws sba1Exception {
		boolean isDeleted = false;
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_PRODUCT_QRY)) {

			pst.setInt(1, id);
			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new sba1Exception("Deleting the item failed!");
		}

		return isDeleted;
	}

	public Product getByID(Integer id) throws sba1Exception {
		Product prod = null;

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_PRODUCT_QRY_BY_ID)) {

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				prod = new Product();
				prod.setId(rs.getInt(1));
				prod.setProductName(rs.getString(2));
				prod.setCost(rs.getDouble(3));
				prod.setProductDescription(rs.getString(4));
			}

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new sba1Exception("Retrival the item failed!");
		}

		return prod;
	}

}
