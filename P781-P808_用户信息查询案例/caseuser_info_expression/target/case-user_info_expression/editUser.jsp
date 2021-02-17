<%--注意添加isELIgnored--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--使用JSTL标签--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%--指定字符集--%>
    <meta charset="utf-8">
    <%--使用edge最新的浏览器渲染方式--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%--    viewport视口，网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同，
    width：默认宽度与设备的宽度相同
    initial-scale：初始的缩放比为1:1--%>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <%-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！--%>
    <title>UserInfoList</title>
    <%--导入CSS的全局样式--%>
    <script src="webjars/jquery/3.1.1/jquery.min.js"></script>
    <%--jQuery导入，建议使用1.9以上的版本--%>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--导入bootstrap的js文件--%>
    <link rel="stylesheet"
          href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>

    <title>editUser</title>

    <style>
        div[style="..."]{
            /*在bootstrap表单中设置宽度，默认表单居中*/
            width: 500px;
        }
        h3[style="..."]{
            text-align: center;
        }
    </style>
</head>
<body>
<%--知识点--%>
<div class="container" style="...">
    <h3 style="...">编辑信息</h3>
    <%--知识点--%>
    <form action="${pageContext.request.contextPath}/editUserServlet" method="post">
        <%--隐藏域，用于接收并提交id--%>
        <div class="form-group" >
            <input type="hidden" name="id" value="${user.id}">
        </div>
        <div class="form-group" >
            <label for="name">姓名：</label>
<%--回写信息使用value来承接--%>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="请输入姓名">
        </div>
        <%--知识点--%>
        <div class="form-group">
            <label>性别：</label>
        <%--可以使用JSTL if,但不建议--%>
        <%--<c:if test="user.sex=='男'">
            <input type="radio" name="sex" id="optionsRadios1" value="男" checked/>男
            <input type="radio" name="sex" id="optionsRadios2" value="女" />女
        </c:if>--%>
            <input type="radio" name="sex" id="optionsRadios1" value="男" ${user.sex=="男"?"checked":""}/>男
            <input type="radio" name="sex" id="optionsRadios2" value="女" ${user.sex=="女"?"checked":""}/>女
        </div>
        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="number" class="form-control" id="age" name="age" value="${user.age}" placeholder="请输入年龄">
        </div>
        <div class="form-group">
            <label for="place_of_birth">出生地：</label>
            <input class="form-control" id="place_of_birth" value="${user.place_of_birth}" name="place_of_birth">
        </div>
        <div class="form-group">
            <label for="Email">邮箱：</label>
            <input type="email" class="form-control" id="Email" aria-describedby="emailHelp" name="email" value="${user.email}">
            <%--        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
        </div>
        <div class="form-group">
            <label for="QQ">QQ号：</label>
            <input class="form-control" id="QQ" name="qq_number" value="${user.qq_number}">
        </div>

        <div class="form-group" style="text-align: center">
            <button type="submit" class="btn btn-primary">提交</button>
            <button class="btn btn-primary" href="${pageContext.request.contextPath}/editUser.jsp">重置</button>
            <a href="${pageContext.request.contextPath}/UserInfoList.jsp" class="btn btn-primary">返回</a>
        </div>
    </form>

</div>

</body>
</html>
