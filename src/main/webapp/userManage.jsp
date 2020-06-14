<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理</title>
</head>
<style>
  *{
      font-size: 1.2em;
  }
</style>
<body>
<h3>用户管理</h3>
<a href="${pageContext.request.contextPath}/index.jsp">返回主页</a>

    <table border="2px solid black" cellspacing="0">
        <tr><th>用户编号</th><th>用户名字</th><th>用户身份</th><th colspan="2">操作</th></tr>
        <c:forEach items="${userPageBean.list}" var="user" varStatus="s">
            <c:if test="${user.score==0}">
               <tr><td>${s.count}</td><td>${user.name}</td><td>普通用户</td><td>
                   <a href="${pageContext.request.contextPath}/user/deleteUser?id=${user.id}">删除用户</a></td><td>
                   <a href="${pageContext.request.contextPath}/user/changeScore?id=${user.id}&&score=${user.score}">设为管理员</a></td></tr>
            </c:if>
            <c:if test="${user.score==1}">
                <tr><td>${s.count}</td><td>${user.name}</td><td>管理员</td><td><p>无法删除</p></td><td>
                    <a href="${pageContext.request.contextPath}/user/deleteUser?id=${user.id}&&score=${user.score}">移除管理员</a></td></tr>
            </c:if>
        </c:forEach>
    </table>
<ul>
    <c:forEach begin="1" end="${userPageBean.totalPage}" var="i" step="1" varStatus="s">
        <a href="${pageContext.request.contextPath}/user/userManage?currentPage=${i}&&pageSize=5">第${i}页</a>
    </c:forEach>
</ul
<p>共${userPageBean.totalCount}条记录，${userPageBean.totalPage}页</p>
</body>
</html>
