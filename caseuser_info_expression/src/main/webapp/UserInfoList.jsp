<%@ page import="Domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.impl.UserDaoImpl" %>
<%@ page import="Dao.UserDao" %>
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
<%--疑问：内部样式无效，内联样式有效--%>
    <style type="text/css">
        td，th{
            text-align: center;
        }
    </style>
</head>

<body>

<%
    UserDao userDao = new UserDaoImpl();
    List<User> UserInfo = userDao.findAll();
    System.out.println("UserInfo");
    System.out.println(UserInfo);
    request.setAttribute("UserInfo", UserInfo);
%>
<div class="container">
    <h3 style="text-align: center">用户信息表</h3>
    <div style="float: left">
        <form class="form-inline" action="${pageContext.request.contextPath}/FindUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName1">姓名</label>
                <%--注意name是加在input标签中的--%>
                <%--将查询条件回显到jsp页面上，否则一查询，查询条件的内容会消失--%>
                <input type="text" class="form-control" id="exampleInputName1" name="name" value="${requestScope.condition.name[0]}">
            </div>
            <div class="form-group">
                <label for="exampleInputName2" >籍贯</label>
                <input type="text" class="form-control" id="exampleInputName2" name="place_of_birth" value="${requestScope.condition.place_of_birth[0]}">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2" >邮箱</label>
                <input type="email" class="form-control" id="exampleInputEmail2" name="email" value="${requestScope.condition.email[0]}">
<%-- 标签对应输入框，点击标签选中输入框，不需要placehold今后还要回写数据--%>
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div style="float: right">
<%--属于绝对路径--%>
        <%--不用UserListServlet类啦--%>
        <a href="${pageContext.request.contextPath}/addUser.jsp" class="btn btn-info">添加联系人</a>
<%--知识点,调用JS函数,返回值为void 没有传入参数--%>
<%--该部分有两个重要的知识点，一、点击事件，提交表单，二、取消href的跳转页面功能--%>
<%--    href="javascript:void(0)" 代表该超链接标签点击不跳转页面--%>
        <a class="btn btn-info" id="delSelected" href="javascript:void(0)">删除选中</a>
        <%--            <button type="button" class="btn btn-info" href="addUser.jsp">添加联系人</button>--%>
    </div>
<%--这里的id=form是为了便于JS函数找到该form标签--%>
<%--此处使用post，避免URL中携带中文参数，若使用get请求方式，后台接收到的参数为乱码--%>
<%--    <form action="${pageContext.request.contextPath}/DelSeletedUserServlet" method="post" id="form">--%>
    <form action="${pageContext.request.contextPath}/DelSeletedUserServlet?currentPage=${requestScope.pageBean.currentPage+1}&rows=5&name=${requestScope.condition.name[0]}&place_of_birth=${requestScope.condition.place_of_birth[0]}&email=${requestScope.condition.email[0]}" method="post" id="form">
        <table class="table table-hover table-bordered" border="1">

        <tr class="success">
            <th style="text-align: center"><input type="checkbox" name="id" id="selectAll"></th>
            <th style="text-align: center">编号</th>
            <th style="text-align: center">姓名</th>
            <th style="text-align: center">性别</th>
            <th style="text-align: center">年龄</th>
            <th style="text-align: center">籍贯</th>
            <th style="text-align: center">QQ</th>
            <th style="text-align: center">邮箱</th>
            <th style="text-align: center">操作</th>
        </tr>

        <%--域名.键名--%>
        <%--分页：注意变更此处的属性--%>
        <c:forEach items="${requestScope.pageBean.list}" varStatus="s" var="obj">
            <tr>
                <td style="text-align: center"><input type="checkbox" name="uid" value="${obj.id}" ></td>
                <td style="text-align: center">${s.count}</td>
                <td style="text-align: center">${obj.name}</td>
                <td style="text-align: center">${obj.sex}</td>
                <td style="text-align: center">${obj.age}</td>
                <td style="text-align: center">${obj.place_of_birth}</td>
                <td style="text-align: center">${obj.qq_number}</td>
                <td style="text-align: center">${obj.email}</td>
                <td style="text-align: center">
                    <%--注意与下方script中的区别--%>
                    <a href="${pageContext.request.contextPath}/FindUser2EditServlet?id=${obj.id}" class="btn btn-default btn-sm">编辑</a>
                    <%--知识点--%>
                    <a href="javaScript:deleteUser(${obj.id})" class="btn btn-default btn-sm container: 'body'">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </form>

        <div>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <%--上一页显示禁用，但仍能点击，需要在UserServiceImp类中处理--%>
                    <c:if test="${requestScope.pageBean.currentPage <= 1}">
                        <li class="page-item disabled"><a class="page-link" href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${requestScope.pageBean.currentPage}&rows=5&name=${requestScope.condition.name[0]}&place_of_birth=${requestScope.condition.place_of_birth[0]}&email=${requestScope.condition.email[0]}">Previous</a></li>
                    </c:if>
                    <c:if test="${requestScope.pageBean.currentPage > 1}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${requestScope.pageBean.currentPage-1}&rows=5&name=${requestScope.condition.name[0]}&place_of_birth=${requestScope.condition.place_of_birth[0]}&email=${requestScope.condition.email[0]}">Previous</a></li>
                    </c:if>

                    <c:forEach begin="1" end="${requestScope.pageBean.totalPage}" var="i">
                        <%--当前页面激活状态，注意${}是表达式--%>
                        <c:if test="${requestScope.pageBean.currentPage == i}">
                        <%--不再使用UserListServlet类--%>
                            <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=5&name=${requestScope.condition.name[0]}&place_of_birth=${requestScope.condition.place_of_birth[0]}&email=${requestScope.condition.email[0]}">${i}</a></li>
                        </c:if>
                        <c:if test="${requestScope.pageBean.currentPage != i}">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=5&name=${requestScope.condition.name[0]}&place_of_birth=${requestScope.condition.place_of_birth[0]}&email=${requestScope.condition.email[0]}">${i}</a></li>
                        </c:if>

                    </c:forEach>
                    <%--下一页禁用--%>
                    <c:if test="${requestScope.pageBean.currentPage >= requestScope.pageBean.totalPage}">
                    <%--为了避免点击分页，查询结果消失，跳转时需要带上查询条件--%>
                        <li class="page-item disabled"><a class="page-link" href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${requestScope.pageBean.currentPage}&rows=5&name=${requestScope.condition.name[0]}&place_of_birth=${requestScope.condition.place_of_birth[0]}&email=${requestScope.condition.email[0]}">Next</a></li>
                    </c:if>
                    <c:if test="${requestScope.pageBean.currentPage < requestScope.pageBean.totalPage}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${requestScope.pageBean.currentPage+1}&rows=5&name=${requestScope.condition.name[0]}&place_of_birth=${requestScope.condition.place_of_birth[0]}&email=${requestScope.condition.email[0]}">Next</a></li>
                    </c:if>
                    <%--分页：注意span的位置--%>
                    <span style="font-size: 25px; margin-left: 5px">
                        共${requestScope.pageBean.totalCount}条记录，共${requestScope.pageBean.totalPage}页
                    </span>
                </ul>
            </nav>
        </div>


</div>
<script>
    function deleteUser(id) {
        if (confirm("您确定要删除？")){
            //携带查询条件和id跳转至DeleteUserServlet，在那个类中
            location.href="${pageContext.request.contextPath}/DeleteUserServlet?id="+id+"&currentPage=${requestScope.pageBean.currentPage+1}&rows=5&name=${requestScope.condition.name[0]}&place_of_birth=${requestScope.condition.place_of_birth[0]}&email=${requestScope.condition.email[0]}";
            <%--location.href="${pageContext.request.contextPath}/DeleteUserServlet?id="+id;--%>
        }
    }
    <%--function deleteUsers() {--%>
    <%--    location.href="${pageContext.request.contextPath}/DeleteSelectedUserServlet";--%>
    <%--}--%>
    window.onload=function () {
        //给删除选中按钮添加单击事件
        //为了避免未选中任何项目，点击删除选中的情况
        //验证功能尽量交给前端，后端只需要处理数据和返回数据即可
        document.getElementById("delSelected").onclick =function () {
            if (confirm("您确定要删除选中项？")){
                var flag = false;
                var names = document.getElementsByName("uid");
                for (var i = 0; i < names.length; i++) {
                    if (names[i].checked === true){
                        <%--//跳转,启动DeleteSelectedUserServlet--%>
                        <%--location.href="${pageContext.request.contextPath}/DeleteSelectedUserServlet";--%>
                        //提交表单
                        document.getElementById("form").submit();

                        flag = true;
                        break;
                    }
                }
                if (flag === false){
                    confirm("请选择要删除的项目");
                }
            }

        };
        // 知识点，全选，注意this.checked,各类单选框，复选框都是checked状态
        var selectAll = document.getElementById("selectAll");
        selectAll.onclick = function () {
            var names = document.getElementsByName("uid");
            for (var i = 0; i < names.length; i++) {
                names[i].checked= this.checked;
            }
        };

        document.getElementById("page").onclick = function () {
            var submit = document.getElementById("form2").submit();
            alert(submit);
        }
    }
</script>
</body>
</html>
