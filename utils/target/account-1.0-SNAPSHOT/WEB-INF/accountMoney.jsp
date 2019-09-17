<%--
  Created by IntelliJ IDEA.
  User: THE GIFTED
  Date: 2019/7/24
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%String path = request.getContextPath();%>
    <script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=path %>/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=path %>/js/echarts.js" type="text/javascript" ></script>
</head>
<body>
    <div style="margin-left: auto" align="center">
            <h1>转账页面</h1>
        <form id="ff" method="post">
            <div>
                <label for="name">我的卡:</label>
                <%--我的卡,下拉--%>
                <input id="acc_cc" name="myNum" value="aa">
            </div>
            <div>
                <label for="name">转账金额:</label>
                <input type="text" class="easyui-numberbox"  name="money" data-options="suffix:'￥',min:0,precision:2"></input>
            </div>
            <div>
                <label for="cart">转账的卡号:</label>
                <input class="easyui-textbox" type="text" name="bankNum"/>
            </div>
            <div>
                <label for="5252">持卡人姓名:</label>
                <input class="easyui-textbox" type="text" name="userName" />
            </div>
            <div>
                <label for="369"></label>
                <input  type="button" value="转账" id="acc_btn" />
            </div>

        </form>
    </div>
    <script>
        $(function () {


            $('#acc_cc').combobox({
                url:'<%=path%>/user/getMyCart',
                valueField:'id',
                textField:'text'
            });










            $("#acc_btn").click(()=>{
                $('#ff').form('submit', {
                    url: "<%=path%>/tbank/accountBank" ,
                    onSubmit: function(){
                        var isValid = $(this).form('validate');
                        return isValid;	// 返回false终止表单提交
                    },
                    success: function(data){
                        console.log(data)
                        if(data == 200){
                            alert("成功")
                            var tab = $('#index_tabs').tabs('getSelected');
                            var index = $('#index_tabs').tabs('getTabIndex',tab);
                            $('#index_tabs').tabs('close',index);
                            // var tab = $('#tt').tabs('getSelected');  // 获取选择的面板
                            // tab.panel('refresh', 'get_content.php');

                        }else {
                            alert("ERROR, or Try :"+data);
                        }
                    }
                });

            })
        })

    </script>
</body>
</html>
