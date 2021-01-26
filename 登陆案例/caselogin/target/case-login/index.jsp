<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.util.Random" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--意义待明确--%>
<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css">

<html>
<head>
    <title>$Login$</title>
    <style>
        span {
            color: red;
            font-size: small;
            margin-bottom: 0;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="page-header" align="center">
    <h1>Welcome to Sirtomato<small>  欢迎您</small></h1>
</div>
<form action="/caselogin_war_exploded/LoginServlet" method="get" class="form-horizontal">
    <div class="form-group">
        <label for="inputuserName" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="inputuserName" placeholder="请填写用户名" name="userName">
        </div>
        <p class="col-sm-1 control-label">
            <span>
                <%--注意<%=%>的用法--%>
                <%=request.getAttribute("msg1") != null ? request.getAttribute("msg1"): ""%>
            </span>
        </p>
    </div>

    </div>
    <div class="form-group">
        <label for="inputPassword" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-6">
            <input type="password" class="form-control" id="inputPassword" placeholder="请输入密码" name="passWord">
        </div>
    </div>
    <div class="form-group">
        <label for="inputcheckCode" class="col-sm-2 control-label">验证码</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="inputcheckCode" placeholder="请输入验证码" name="checkCode">
        </div>
        <p class="col-sm-1 control-label">
                <%--注意<%=%>的用法--%>
                <span>
                    <%=request.getAttribute("msg2") != null ? request.getAttribute("msg2"): "" %>
                </span>
        </p>
    </div>
    <div class="form-group">
        <img src="/caselogin_war_exploded/checkCodeServlet" alt="" id="checkCode" class="img-rounded col-sm-offset-2">
        <a href="#" id="change">看不清换一张</a>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </div>

<%--优化之前的代码--%>
<%--    用户名 <input type="text" name="userName"> <span class="tip"><% if (request.getAttribute("msg1") != null) {--%>
<%--    out.print(request.getAttribute("msg1"));--%>
<%--}%> </span> <br/>--%>
<%--    密码 <input type="text" name="passWord"> <br/>--%>
<%--    验证码 <input type="text" name="checkCode"> <span class="tip"><% if (request.getAttribute("msg2") != null) {--%>
<%--    out.print(request.getAttribute("msg2"));--%>
<%--}%> </span> <br/>--%>
<%--    <img src="/caselogin_war_exploded/checkCodeServlet" alt="" id="checkCode" class="img-rounded">--%>
<%--    <a href="#" id="change">看不清换一张</a>--%>
<%--    <input type="submit" class="btn btn-default">--%>
</form>
<script>
    window.onload = function () {
        //1、给图片绑定单击事件
        var fun=function () {
            //添加时间戳
            var date = new Date().getTime();
            //2、 重新设置图片的src属性值
            img.src = "/caselogin_war_exploded/checkCodeServlet?" + date;
        };
        var img = document.getElementById("checkCode");
        img.onclick = fun;

        var a = document.getElementById("change");
        a.onclick = fun;
    }
</script>


</body>
</html>
