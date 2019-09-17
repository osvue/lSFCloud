<%@ page import="com.bz.pojo.Users" %><%--
  Created by IntelliJ IDEA.
  User: THE GIFTED
  Date: 2019/7/18
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<% String path = request.getContextPath();%>
<head>
    <title>Echarts</title>
    <script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=path %>/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=path %>/js/echarts.js" type="text/javascript" ></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',title:'North Title',split:true" style="height:100px;">
    <%Users obj = (Users)session.getAttribute("TOKEN");%>
   <h1>欢迎你:----- <font color="#dc143c"><%=obj.getUname()%></font></h1>
</div>
<div data-options="region:'west',title:'West',split:true" style="width:100px;">
        <%--左菜单--%>
            <div id="aa" fit="true" class="easyui-accordion" style="width:300px;height:200px;">
                <div title="Title2" data-options="href:'<%=path%>/menu',iconCls:'icon-reload',selected:true" style="padding:10px;">

                </div>
                <div title="Title3">
                    1001
                </div>
            </div>

</div>

<%--容器--%>
<div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
    <%--选型卡--%>
        <div id="index_tabs" data-options="fit:true" class="easyui-tabs" style="width:500px;height:250px;">
            <div title="Tab1" style="padding:20px;display:none;">
                <h1>Hello world</h1>
            </div>
        </div>



</div>
</body>
<script type="text/javascript">
    // 初始化echarts对象
    // var myContainer = echarts.init(document.getElementById('container'));
      /*  title:{
            text:'echarts异步加载数据'
        },
        tooltip:{},
        legend:{},
        xAxis:{
            data:[]
        },
        yAxis:{},
        series:[
            {
                name:'访问量',
                type:'bar',
                data:[]
            }
        ]
    }*/
    function formatData(val) {
        let date = new Date(val);
        let year = date.getFullYear();
        let month = date.getMonth()+1;
        let day = date.getDay();
        // let hours = date.getHours();
        // let milliseconds = date.getMilliseconds();
        return year+"-"+month+"-"+day;
    }
      var   option = {
           /* title : {  //标题
               text: 'www.baidu.com',
                subtext: '1001',
                x:'center'
            },
          /* tooltip : {
               trigger: 'item',
               formatter: "{a} <br/>{b} : {c} ({d}%)"
           },*/
           legend: { //左上角展示
               orient : 'vertical',
               x : 'left',
               data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
           },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {
                        show: true,
                         type: ['pie', 'funnel'],
                        option: {
                            funnel: {
                                x: '25%',
                                width: '50%',
                                funnelAlign: 'left',
                                max: 1548
                            }
                        }
                    },
                    restore : {show: true},    //显示初始化表单数据
                    saveAsImage : {show: true}  //显示下载按钮
                }
            },
             calculable : true,
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:335, name:'直接访问'},
                        {value:310, name:'邮件营销'},
                        {value:234, name:'联盟广告'},
                        {value:135, name:'视频广告'},
                        {value:1548, name:'搜索引擎'}
                    ]
                }
            ]
        };
     // myContainer.setOption(option)
    /*$.get('echarts.json',function(res){
        // console.log(res);
        myContainer.setOption({
            xAxis:{
                data:res.name
            },
            series:[{
                data:res.data
            }
            ]
        });
    });*/
</script>
</html>
