<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件共享中心</title>
</head>
<script src="js/jquery-1.11.0.min.js"></script>
<style>
    body{
        font-size: 1.5em;
        text-align: center;
        background-color: antiquewhite;
    }
    #head{
        /*width: 100%;*/
        height: 70px;
        background-color: wheat;
    }
    div{
        border: 1px solid red;
        /*margin: auto;*/
        /*padding: 20px;*/
    }
    #body{
        height:400px;
        background-color: chartreuse;
        text-align: left;
        padding-left: 30px ;
    }
    #body_source ul{
        margin: 30px;
        padding: 50px;
    }
    /*消除黑点*/
    ul{
        list-style-type: none;
        line-height: 50px;
    }
    /*横向排列*/
    #list,#page{
        display: inline-block;
        color:olivedrab;
        font-size: 1.7em;
        line-height: 10px;
        height: 50px;
    }
    #pages > #page{
        color:rosybrown;
        font-size: 1.2em;
        line-height: 10px;
        height: 30px;
    }
    #user{
        color: red;
    }
</style>
<script>
    $(function () {
        $.post("source/querySourceType",{},function (data) {
            //接收json数据
            var lists='<li id="list"><a href="index.jsp?tid=0" id="btn" >首页&nbsp&nbsp&nbsp</a></li>'
            //布局页面资源分类导航栏
            // alert(data[0].ttype)
            for(var i=0;i<data.length;i++){
                var list='<li id="list"><a href="index.jsp?tid='+data[i].tid+'" >'+data[i].ttype+'</a></li>&nbsp&nbsp&nbsp&nbsp'
                lists +=list;
            }
            $("#head_source").html(lists)
        });

    })//end
    //定义方法查询资源
    function querySource(tid,currentPage,pageSize) {
        //   发送Ajax请求
        $.get("source/querySource",{tid:tid,currentPage:currentPage,pageSize:pageSize},function(data) {
                $("#totalCount").html(data.totalCount);
                $("#totalPage").html(data.totalPage);
                //拼接分页
            var pages='<li id="page"><a href="javascript:void(0);" onclick="javascript:querySource('+tid+',1)">首页&nbsp</a></li>';
            for (var i=1;i<=data.totalPage;i++){
                 var page='<li id="page" ><a href="javascript:void(0);" onclick="javascript:querySource('+tid+','+i+')">第'+i+'页&nbsp</a></li>';
                 pages+=page;
            }
            pages+='<li id="page"><a href="javascript:void(0);"onclick="javascript:querySource('+tid+','+data.totalPage+')" >尾页</a></li>';
            $("#pages").html(pages);
            //拼接资源显示
            var sources='更多资源等你哦';

            for(var i=0;i< data.list.length;i++){
                var source='<li id="source"><a href="javascript:void(0);" >'+data.list[i].fname+'</a>&nbsp&nbsp&nbsp' +
                    '<span>资源描述: '+data.list[i].fdescribe+'</span>&nbsp&nbsp&nbsp<span>上传时间:'+data.list[i].uploadTime+'</span>&nbsp&nbsp&nbsp' +
                    '<a href="${pageContext.request.contextPath}/source/downloadSource?fid='+data.list[i].fid+'">下载</a>&nbsp&nbsp&nbsp' +
                    '<a href="javascript:void(0)">很抱歉，暂不支持在线打开</a>&nbsp&nbsp&nbsp</li>'
                sources+=source;
            }
            $("#body_source").html(sources);
        })
    }//
    //   定义第二个入口函数，等第一个入口函数执行完毕之后，获取tid(资源id),然后查询资源并展示
        $(function () {
            var search=location.search;//?tid=1
            var tid=search.split("=")[1];//获取tid(资源id)
            querySource(tid)//调用方法查询资源并展示
        })//end
</script>
<body>
<%--头部及登用户的功能--%>
<p> <span>欢迎<span id="user">${user.name}</span>浏览本站</span>
    <c:if test="${user==null}">
        <a href="login.jsp">登录</a>
        <a href="login.jsp">注册</a>
    </c:if>
    <c:if test="${user!=null}">
        <a id="exit" href="javascript:void(0)">退出</a>
        <a href="${pageContext.request.contextPath}/updateUser.jsp">修改个人信息</a>
        <a href="sourceUpload.jsp">上传资源</a>
    </c:if>
    <c:if test="${user.score==1}">
        <a href="${pageContext.request.contextPath}/user/userManage?currentPage=1&&pageSize=5">用户管理</a>
        <a href="#">资源管理</a>
<%--        用户管理（删除用户/查询用户/修改用户权限);资源管理（修改资源，删除资源）--%>
    </c:if>
</p>
<%--    资源导航栏--%>
    <div id="head">
        <ul id="head_source">
        </ul>
    </div>
<%--文件模块--%>
<div id="body">
    <ul id="body_source">
    </ul>
</div>
<%--分页栏模块--%>
<div>
    <ul id="pages"></ul>
    <p>一共<span id="totalCount" color="red"></span>个资源，共<span id="totalPage" color="red"></span>页</p>
</div>
<%--在前端设置一些友好的提示--%>
<script>
    $("#exit").click(function () {
       if(confirm("您确认退出吗？")){
          window.location.href="${pageContext.request.contextPath}/user/exit";
       }
    });
</script>
</body>
</html>
