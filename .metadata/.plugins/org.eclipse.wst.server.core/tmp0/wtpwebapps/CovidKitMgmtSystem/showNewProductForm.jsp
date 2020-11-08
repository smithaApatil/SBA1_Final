<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add or Update Product </title>
</head>
<body>

<jsp:include page="headeradmin.jsp" />
	<h3>${prod.id==null?"New Item":"Edit Item" }</h3>
	
	<form action='${prod.id==null?"addProd":"updateProd" }' method="POST">
		<div>
			<label>ID: </label>
			<input type="number" value="${prod.id }" name="id" required 
			 ${item.icode==null?"":"readonly" } />
		</div>
		<div>
			<label>Product Name: </label>
			<input type="text" value="${prod.productName }" name="prodName" minlength="3" maxlength="20" required />
		</div>
		
			<label>Cost : </label>
			<input type="decimal" value="${prod.cost }" name="prodCost" required />
		</div>
		
		<div>
			<label>Product Description: </label>
			<input type="text" value="${prod.productDescription }" name="prodDesc" minlength="3" maxlength="20" required />
		</div>
		
		</div>
		<button>Add</button>	
		
			
	</form>
</body>
</html>