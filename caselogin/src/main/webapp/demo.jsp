<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" errorPage="index.jsp" isErrorPage="true" language="java" %>
<%@include file="index.jsp"%>
<%@taglib prefix="c" uri="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Demo</title>
</head>
<body>
<h1>服务器正忙...</h1>
<%
    String message = exception.getMessage();
    Random random = new Random();
%>
<%=message%>
<c:if test="\"122\"1==1">a</c:if>
<\%a
%\>
</body>
</html>
