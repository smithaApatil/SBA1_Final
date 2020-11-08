package com.wellsfargo.sba1.entity;

public class Product {

		private Integer id;
		private String productName;
		private Double cost;
		private String productDescription;
		
		public Product() {
			
		}
		public Product(Integer id, String productName, Double cost, String productDescription) {
			super();
			this.id = id;
			this.productName = productName;
			this.cost = cost;
			this.productDescription = productDescription;
		}
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public Double getCost() {
			return cost;
		}
		public void setCost(Double cost) {
			this.cost = cost;
		}
		public String getProductDescription() {
			return productDescription;
		}
		public void setProductDescription(String productDescription) {
			this.productDescription = productDescription;
		}


		@Override
		public String toString() {
			return "Product [id=" + id + ", productName=" + productName + ", cost=" + cost + ", productDescription="
					+ productDescription + "]";
		}
		
		
		
}
