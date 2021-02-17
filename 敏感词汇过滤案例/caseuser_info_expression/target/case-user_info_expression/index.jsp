<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
      href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>

<html>
<body>
<%--注意EL中的属性必须和Administrator成员变量一模一样--%>
<div>
    <h3 align="center">
        ${user.userName}，欢迎您！
    </h3>
</div>
<div style="text-align: center">
<%--    Servlet中的重定向需要使用动态获取虚拟目录，但是转发不需要--%>
    <a href="${pageContext.request.contextPath}/FindUserByPageServlet" class="btn btn-info">查询所有用户信息</a>

</div>
</body>
</html>
