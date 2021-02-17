<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <title>login</title>

</head>
<body>
<%--知识点--%>
<%--在bootstrap表单中设置宽度，默认表单居中--%>
<div class="container" style="width: 500px">
        <h3 style="text-align: center">管理员登录</h3>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <div class="form-group">
                <label for="userName">用户名：</label>
                <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label for="passWord">密码：</label>
                <input type="password" class="form-control" id="passWord" name="passWord" placeholder="请输入密码">
            <%--Administrator{userName='aaa', passWord='null', checkCode='s17w'},该错误说明，各表单项中的name必须要和Administrator类中的成员变量一模一样--%>
            </div>
            <%--知识点 form-inline使得输入框的宽度变小，和标签在同一行--%>
            <div class="form-inline">
                <label for="checkCode">验证码：</label>
                <input type="text" id="checkCode" name="checkCode" placeholder="请输入验证码" class="form-control">
            <%--知识点 href="javascript:refreshCode()"--%>
            <%--知识点 src="${pageContext.request.contextPath}/CheckCodeServlet 主动索要二维码--%>
                <a href="javascript:refreshCode()">
                    <img src="${pageContext.request.contextPath}/CheckCodeServlet" title="看不清点击刷新" id="vcode">
                </a>
            </div>
            <hr/>
            <%--知识点,按钮居中，或者说div中的组件居中--%>
            <div class="form-group" style="text-align: center">
<%--                <button type="submit" class="btn btn-primary" value="登录">登录</button>--%>
                <%--知识点,a input button都可以--%>
                <input type="submit" class="btn btn-primary" value="登录" id="login">
            </div>
        </form>
<%--class中新增hide即可隐藏--%>
    <div class="alert alert-warning hide alert-dismissible" role="alert" id="alert">
<%--    <div class="alert alert-warning hide alert-dismissible" role="alert" id="alert">--%>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span></button>
        <strong>${login_msg}</strong>
    </div>
</div>

<script>
    //和之前的案例有所区别，这里通过超链接a标签的src执行checkCodeServlet，time=可省略
    function refreshCode(){
        var vcode = document.getElementById("vcode");
        vcode.src="${pageContext.request.contextPath}/CheckCodeServlet?time="+new Date().getTime();
    }
    //警告窗第一次登陆时不显示,
    window.onload=function () {
        <c:if test="${login_msg!= null}">
            document.getElementById("alert").setAttribute("class","alert alert-warning alert-dismissible");
        </c:if>
    }
</script>
</body>
</html>
