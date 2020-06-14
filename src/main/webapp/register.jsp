<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
 <form id="register_form" action="${pageContext.request.contextPath}/user/register" method="post" width="400px" height="400px">
     <span>${errmsg}</span><br>
     用户名：<input type="text" name="name" method="post"  width="400px" height="400px" ><br>
     密码：<input type="password" name="password"><br>
     <input type="submit" value="注册"><br>
 </form>
<a href="${pageContext.request.contextPath}/login.jsp" >已有帐号？点击登录</a>

</body>
</html>
