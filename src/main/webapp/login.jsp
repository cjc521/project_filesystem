<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<div  width="400px" height="400px" text-align="center" font-size="1.2em">
    <form id="login_form" action="${pageContext.request.contextPath}/user/login" method="post"  >
        <span>${errmsg}</span><br>
        用户名：<input type="text" id="name" name="name"><span id="name_err"></span> <br>
        密码：<input type="text" id="password" name="password"><span id="password_err"></span><br>
        <input type="submit" id="submit" value="登录"> <br>
    </form>
    <a href="${pageContext.request.contextPath}/register.jsp" >没有帐号？点击注册</a>
</div>
</body>
</html>
