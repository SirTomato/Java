<%@ page import="com.sirtomato.Domain.User" %>
<%@ page import="com.sirtomato.Domain.ELdemo" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>EL</title>
</head>
<body>
<h2>1、运算</h2>
<%--isELIgnored="false"--%>
${3>4} <br/>
<%--忽略当前这个EL表达式--%>
\${3>4}
<h3>算数运算符</h3>
${3+4} <br>
${3/4} <br>
${3 div 4} <br>
${3 mod 4} <br>
<h3>比较运算符</h3>
${3==4}
<h3>逻辑运算符</h3>
${3>4 && 3<4} <br>
<h3>空运算符</h3>
${empty list}
<h2>2、获取值</h2>
<%
request.setAttribute("name","路飞");
session.setAttribute("age","18");
session.setAttribute("name","索隆");
%>
${requestScope.name} <br>
${sessionScope.age} <br>
<%--能够替换下方代码--%>
<%--<%=request.getAttribute("name") != null ? request.getAttribute("name"): "" %>--%>
<%--依次从最小的域中查找是否有该键对应的值，直到找到为止--%>
${name}
<h2>3、获取对象、List集合、Map集合的值</h2>
<%
    ELdemo eLdemo = new ELdemo();
    eLdemo.setName("山治");
    eLdemo.setAge("23");
    eLdemo.setBirthday(new Date());
    request.setAttribute("el",eLdemo);

    ArrayList<Object> list = new ArrayList<>();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add(eLdemo);
    session.setAttribute("list",list);

    HashMap<String, Object> map = new HashMap<>();
    map.put("gender","male");
    map.put("eldemo",eLdemo);
    request.setAttribute("map",map);
%>
<h3>获取对象的值</h3>
${requestScope.el.name} <br>
${el.age} <br>
<%--有getBirStr方法即可,注意java类更新后需要重新启动服务器，不像JSP，随改随测--%>
${el.birStr} <br>
<h3>获取List集合</h3>
${list}<br>
${list[0]}<br>
${list[1]}<br>
<%--脚标越界，不报错，仅显示空字符串--%>
${list[10]}<br>
${list[3].name}<br>
<h3>获取map集合</h3>
${map} <br>
${map.gender} <br>
${map["gender"]} <br>
${map.eldemo.name}<br>

<h3>empty运算符</h3>
<%
    String str = "";
    request.setAttribute("str",str);
    ArrayList list1 = new ArrayList();
    request.setAttribute("list",list1);
%>
${not empty str}<br>
<%--list1不为null，长度等于0--%>
${not empty list1}

<h2>3、EL的隐式对象pageContext</h2>
${pageContext.request}<br>
<%--在jsp页面动态获取虚拟目录--%>
${pageContext.request.contextPath}
</body>
</html>
