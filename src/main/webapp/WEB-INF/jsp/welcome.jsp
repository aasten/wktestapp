<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	<c:url value="/css/main.css" var="cssPath" />
	<link href="${cssPath}" rel="stylesheet" />
</head>
<body>

<c:choose>
    <c:when test="${authorized}">
        <c:out value="Authorized" />
    </c:when>
    <c:otherwise>
        <form method="post" action="<c:url value="${pageContext.request.contextPath}" />">
            <input type="text" name="name" required/>
            <input type="password" name="password" required/>
            <label><input type="checkbox" name="rememberme" />Remember me</label>
            <input type="submit" value="Login" />
        </form>
    </c:otherwise>
</c:choose>

</body>
</html>
