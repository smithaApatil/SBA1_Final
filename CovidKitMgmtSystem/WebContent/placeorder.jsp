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
<title>Place Order</title>
</head>
<body>
<%	List<Product> cart= (List<Product>) session.getAttribute("cart");
	request.setAttribute("cart",cart);
	Customer cust = (Customer) session.getAttribute("cust");
%>
	<form action="ordersummary">
	
	<jsp:include page="header.jsp" />
	
	<p> Please confirm the address here : </p>
	
	<input type="text" name="address" minlength="3" maxlength="20" required />
	
	<button> Order Summary</button>

</form>
<hr>
	<jsp:include page="footer.jsp"/>
	</hr>
</body>
</html>