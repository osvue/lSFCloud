<%--
  Created by IntelliJ IDEA.
  User: THE GIFTED
  Date: 2019/7/23
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户个人信息表</title>
    <%String path = request.getContextPath();%>
    <script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="<%=path %>/js/echarts.js"></script>
    <script type="text/javascript" src="<%=path %>/js/my.js"></script>
    <script type="text/javascript" src="<%=path %>/js/download.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=path %>/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>
<body>
<%--页面内的layout 布局--%>
<div  class="easyui-layout" data-options="fit:true">
    <!-- 左侧菜单 -->
    <div data-options="region:'west'" style="width:1000px;">
        <table id="user_table", data-options="toolbar:'#tb'" ></table>
        <div id="dd"></div>

    </div>

    <!-- 右侧数据-->
    <div data-options="region:'east'" style="width:400px;">
        <div id="10010" style="width: 400px;height: 400px"></div>
    </div>



    <!-- 工具栏 导入导出 -->
    <div id="tb" style="padding:5px;height:auto" >
        <div style="margin-bottom:5px">
            <a href="javascript:addStu('批量新增');"  class="easyui-linkbutton" iconCls="icon-add" plain="true">批量新增</a>
            <a href="javascript:changeStu();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
            <a href="javascript:exportExcel();" class="easyui-linkbutton" iconCls="icon-save" plain="true">Excel</a>
            <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-cut" plain="true">Table</a>
            <a href="javascript:test();" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </div>
        <div>
            <form id="userTbale_form1">
                <input type="hidden" name="page" id="1010">
                <input type="hidden" name="rows" id="1011">
            Date From: <input class="easyui-datebox" name="begin" style="width:150px">
            To: <input class="easyui-datebox" name="end" style="width:150px">
            </form>
        </div>
    </div>
</div>
<script>
    var test=()=>{
        let message = $('#user_table').datagrid('getData');
        $("#1010").val(message.page);
        $("#1011").val(message.info)
        let serializeJSON = $('#userTbale_form1').serializeJSON();
        console.log(serializeJSON)
        $.download("<%=path%>/user/export/page",serializeJSON)
    }

    var addStu=(title)=>{
        $('#dd').dialog({
            title:title,
            width: 500,
            height: 500,
            closed: false,
            cache: false,
            href: '<%=path%>/upload',
            modal: true
        });

    }




    $(function () {




        $('#user_table').datagrid({
            url:'<%=path%>/getMe',
            columns:[[
                {field:'bankId',title:'number',width:100},
                {field:'bankNo',title:'卡号',width:100},
                {field:'bankType',title:'卡类型',width:100,
                    formatter: function(value,row,index){
                        if(value == 1){
                             return "储蓄卡";
                        }else if(value == 2){
                            return "信用卡";
                        }else {
                            return "NOT FOUND";
                        }
                    }
                },
                {field:'bankBelong',title:'归属银行',width:100,
                    formatter: function(value,row,index){
                        if(value == 1){
                            return "中国银行";
                        }else if(value == 2){
                            return "建设银行";
                        }else if(value == 3){
                            return "民生银行";
                        }else if(value == 4){
                            return "招商银行";
                        }else {
                            return "七里香";
                        }
                    }
                },
                {field:'bankName',title:'持卡人',width:100},
                {field:'bankMoney',title:'账户余额',width:100,
                    formatter: function(value,row,index){
                        return value+" ￥";
                    }
                },
                {field:'bankDate',title:'开户时间',width:100,
                    formatter: function(value,row,index){
                       return formatData(value);
                    }
                },
                {
                    field: 'userId', title: '操作', width: 100, align: 'right',
                    formatter: function (value, row, index) {
                        if (row.bankType == 1) {
                            return '<a href="javascript:inputsFun('+row.bankType+','+row.bankNo+');">存款</a> &nbsp;&nbsp;&nbsp;||&nbsp;&nbsp;&nbsp;<a href="javascript:outputsFun('+row.bankType+','+row.bankNo+');">取款</a> &nbsp;&nbsp;&nbsp;';
                        }
                        if (row.bankType == 2) {
                            return '<a href="javascript:inputsFun('+row.bankType+','+row.bankNo+');">还款</a> &nbsp;&nbsp;&nbsp;||&nbsp;&nbsp;&nbsp;<a href="javascript:outputsFun('+row.bankType+','+row.bankNo+');">取款</a> &nbsp;&nbsp;&nbsp;';
                        }
                    }
                }
            ]],
            fit:true,
            fitColumns:true,
            singleSelect:true,
            pagination:true,
            pageSize:5,
            pageList:[3,5,7,9],
            onLoadSuccess:(data)=>{
                var  das = data.rows.map((val,index)=>{
                    console.log(val.bankMoney)
                    return val.bankMoney;
                })
                var  names = data.rows.map((val,index)=>{

                    console.log(val.bankNo)
                    return val.bankNo;
                })

                // 初始化echarts对象
                var myContainer = echarts.init(document.getElementById('10010'));
                option = {
                    title : {
                        text: '2019余额走势',
                        subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        data:'正式'
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : names
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'当前卡',
                            type:'pie',
                            data:das,

                        }
                    ]
                };

                myContainer.setOption(option)



            }
        });
    })
    var outputsFun=(val,bnum)=>{
        var title = ""
        if(val == 1){
            title = "储蓄卡取款";
        }
        if(val == 2){
            title = "信用卡取款";
        }
        $('#dd').dialog({
            title:title,
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            href: '<%=path%>/tbank/outMoney/'+val+'/'+bnum,
            modal: true
        });
    }

   function exportExcel(){
        alert("export")
        location.href="<%=path%>/user/export"

    }
    var inputsFun=(val,bnum)=>{
        var title = ""
        if(val == 1){
            title = "储蓄卡存款";
        }
        if(val == 2){
            title = "信用卡还款";
        }
        $('#dd').dialog({
            title:title,
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            href: '<%=path%>/tbank/inMoney/'+val+'/'+bnum,
            modal: true
        });
    }
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
