<%--
  Created by IntelliJ IDEA.
  User: THE GIFTED
  Date: 2019/7/23
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单</title>
</head>
<body>
        caidan<%String path = request.getContextPath();%>
        <h3><a href="javascript:;" onclick="showInfo('我的信息')">我的信息</a></h3>
        <h3><a href="javascript:;" onclick="CRUD('Admin')">Admin</a></h3>
        <h3><a href="javascript:;" onclick="accountMoney('第三方转账')">三方转账</a></h3>
        <h3><a href="javascript:;" onclick="accountDisrver('转账记录')">转账记录</a></h3>

    <script>

        var accountDisrver=(title)=>{
            let flag = $("#index_tabs").tabs('exists',title);
            if(!flag){
                $("#index_tabs").tabs('add',{
                    title: title,
                    selected: true,
                    href:'<%=path%>/accountDisrver',
                    closable:true
                });
            }else {
                $("#index_tabs").tabs('select',title);
            }
        }



        <%--转账--%>
        var accountMoney=(title)=>{
            let flag = $("#index_tabs").tabs('exists',title);
            if(!flag){
                $("#index_tabs").tabs('add',{
                    title: title,
                    selected: true,
                    href:'<%=path%>/accountMoney',
                    closable:true
                });
            }else {
                $("#index_tabs").tabs('select',title);
            }
        }


        var CRUD = (title)=>{
            let flag = $("#index_tabs").tabs('exists',title);
            if(!flag){
                $("#index_tabs").tabs('add',{
                    title: title,
                    selected: true,
                    href:'<%=path%>/bankAdd',
                    closable:true
                });
            }else {
                $("#index_tabs").tabs('select',title);
            }
        }
        var showInfo = (title)=>{
            let flag = $("#index_tabs").tabs('exists',title);
            if(!flag){
                $("#index_tabs").tabs('add',{
                    title: title,
                    selected: true,
                    href:"<%=path%>/userTable",
                    closable:true
                });
            }else {
                $("#index_tabs").tabs('select',title);
            }
        }

    </script>

</body>
</html>
