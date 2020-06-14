<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>上传资源</title>
</head>
<style type="text/css">
    div{

        font-size: 1.3em;
    }

</style>
<body>
<div>
<form action="${pageContext.request.contextPath}/source/uploadSource" method="post" enctype="multipart/form-data" >
    <p>${errmsg}</p>
    上传 资源：<input type="file" name="fname"><br>
    文件类型：<input type="radio" name="tid" value="1" checked="checked">视频 <input type="radio" name="tid" value="2">音乐
    <input type="radio" name="tid" value="3">文件<input type="radio" name="tid" value="4">图片
    <input type="radio" name="tid" value="5">其它资源<br>
    文件描述：<input type="text" name="fdescribe" style="width:240px;height:80px"><br>
    <input type="submit" value="确定上传">
</form>
<a href="index.jsp">返回</a>
</div>
</body>
</html>
