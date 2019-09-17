<%--
  Created by IntelliJ IDEA.
  User: THE GIFTED
  Date: 2019/7/25
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>记录</title>
    <%String path = request.getContextPath();%>
    <script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=path %>/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=path %>/js/echarts.js" type="text/javascript" ></script>
</head>
<body>
<h1>转账记录表</h1>

<table id="acc_Dis_table"></table>

<script>
        $(function () {
            $('#acc_Dis_table').datagrid({
                url:'<%=path%>/accdis/selall',
                columns:[[
                    {field:'accdate',title:'转账时间',width:100,
                        formatter: function(value,row,index){
                            return formatData(value);
                        }
                    },
                    {field:'outId',title:'转账人',width:100},
                    {field:'outNum',title:'转账卡号',width:100},
                    {field:'inId',title:'收款人',width:100},
                    {field:'inNum',title:'收款账号',width:100},
                    {field:'money',title:'金额',width:100,align:'right'}
                ]],
                fit:true,
                fitColumns:true

            });
        })
        function formatData(val) {
            let date = new Date(val);
            let year = date.getFullYear();
            let month = date.getMonth()+1;
            let day = date.getDay();
            // let hours = date.getHours();
            // let milliseconds = date.getMilliseconds();
            return year+"-"+month+"-"+day;
        }

</script>

</body>
</html>
