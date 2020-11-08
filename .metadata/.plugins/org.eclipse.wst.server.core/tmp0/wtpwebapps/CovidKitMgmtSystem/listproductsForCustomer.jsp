<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.wellsfargo.sba1.entity.Product"%>
<%@page import="com.wellsfargo.sba1.entity.Customer"%>
<%@page import="java.util.List"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products list</title>
</head>
<body>
<form action="placeorder" method="POST">

<%	List<Product> cart= (List<Product>) session.getAttribute("cart");
	request.setAttribute("cart",cart);
	Customer cust = (Customer) session.getAttribute("cust");
%>
<jsp:include page="header.jsp" />
	<h3>Products</h3>
	<c:choose>
		<c:when test="${prods==null || prods.isEmpty() }">
			<p>No Items Found</p>
			
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>ID</th>
					<th>Product Name</th>
					<th>Cost</th>
					<th>Product Description</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${prods }" var="prod">
					<tr>
					<td>${prod.id }</td>
					<td>${prod.productName }</td>
					<td>${prod.cost }</td>
					<td>${prod.productDescription }</td>
					<td>
						<a href="addProdToCart?id=${prod.id }">Add To Cart</a> <span>|</span>					
					</td>
				</tr>				
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<button>Place your Order</button>
</form>

<hr>
	<jsp:include page="footer.jsp"/>
	</hr>
</body>
</html>