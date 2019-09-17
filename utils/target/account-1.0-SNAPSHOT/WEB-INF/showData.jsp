<%--
  Created by IntelliJ IDEA.
  User: THE GIFTED
  Date: 2019/7/29
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据可视化</title> <%String path = request.getContextPath();%>
    <script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="<%=path %>/js/echarts.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=path %>/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<div id="showData_layout" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',title:'数据可视化'" style="padding:1px;background:#eee;">
        <div id="p"  style="padding:10px;">

        </div>

    </div>

</div>


<script>

    $('#p').panel({
        fit:true,border:false,
        title:'1001',
        iconCls: '10012020',
        halign:'left',
        tools: [{
            iconCls:'icon-add',
            handler:function(){

                $('#p').panel('open').panel('refresh','<%=path%>/pie');


            }
        },{
            iconCls:'icon-save',
            handler:function(){
                $('#p').panel('open').panel('refresh','<%=path%>/bar');
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
                $('#p').panel('open').panel('refresh','<%=path%>/line');
            }
        }]
    });

</script>
</body>
</html>
