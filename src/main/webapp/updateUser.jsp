<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" errorPage="err.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改个人信息</title>
</head>
<style>
    body{
        font-size: 1.3em;
    }
</style>
<body>
  <form method="post" action="${pageContext.request.contextPath}/user/updateUser">
      <input type="hidden" name="id" value="${user.id}">
    名字:<input type="text" name="name" value="${user.name}"><br>
    密码:<input type="password" name="password" value="${user.password}"><br>
    用户身份:
    <c:if test="${user.score==0}">
        不好意思，你不具备修改此选项的权限，请联系管理员！<br>
      <input type="radio" name="score" checked="checked" disabled value="0">普通用户
      <input type="radio" name="score" value="1" disabled>管理员
    </c:if>
    <c:if test="${user.score==1}">
        您是尊贵的管理员，请慎重选择！！！<br>
        <input type="radio" name="score" value="0">普通用户
        <input type="radio" name="score" checked="checked" value="1">管理员
    </c:if>
      <br>
      <input type="submit" value="提交修改">
  </form>
</body>
</html>
