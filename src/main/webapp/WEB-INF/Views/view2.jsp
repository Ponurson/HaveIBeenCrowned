<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ponur
  Date: 21.03.2020
  Time: 07:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
<c:choose>
    <c:when test="${not empty contactDates}">
        It is possible you had contact with coronavirus on this dates:
        <c:forEach var="date" items="${contactDates}">
            ${date}<br>
        </c:forEach>
    </c:when>
    <c:otherwise>
        Great News!

        You did not have contact with infected in the last 14 days!
    </c:otherwise>
</c:choose>
</div>
</body>
</html>
