<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona  Kit Management System !!</title>
</head>
<body>
<div>

	<jsp:include page="header.jsp" />
			
			<c:if test="${msg != null}">
				<p><strong>${msg }</strong>
		</c:if>

<br>
<br>
	<jsp:include page="footer.jsp"/>

</body>
</html>