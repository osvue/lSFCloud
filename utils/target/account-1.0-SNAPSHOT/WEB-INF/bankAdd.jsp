<%@ page import="com.bz.pojo.Users" %><%--
  Created by IntelliJ IDEA.
  User: THE GIFTED
  Date: 2019/7/23
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>银行添加</title><%String path = request.getContextPath();%>
    <script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=path %>/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>


<%Users obj = (Users)session.getAttribute("TOKEN");%>
<div><form id="bam" method="post">
    <table  style="width:900px;height:350px"
           >
        <thead>
        <tr>
            <th data-options="field:'code',width:300">编码</th>
            <th data-options="field:'name',width:500">名称</th>
            <th data-options="field:'price',width:500,align:'right'"></th>
        </tr>
        </thead>

    <tbody>

    <tr>
        <td width="350px">-</td>
        <td width="500px">
        <input type="hidden" name="bankId" />
           <font color="#dc143c">管理员新增</font>
        </td><td width="500px">-</td>
    </tr>
    <tr>
        <td>卡号</td>
        <td>
            <input type="text" name="bankNo" id="num1" readonly="readonly" />
            <input type="text" value="<%=session.getAttribute("juc")%>" name="juc" />
        </td><td width="500px">-</td>
    </tr>
    <tr>
        <td>卡类型</td>
        <td>
            <input type="radio" name="bankType" value="1"  checked onfocus="changeMoney(0)" />储蓄卡
            <input type="radio" name="bankType" value="2" onfocus="changeMoney(20000)" />信用卡
        </td>
    </tr>
    <tr>
        <td>银行</td>

        <td > <%--异步加载--%>
            <input class="easyui-combobox" data-options="required:true" name="bankBelong" id="csadd" style="width:200px;" >
        </td>
    </tr>
    <tr>
        <td>持卡人</td>
        <td>
            <input type="text" name="bankName" readonly="true" value="<%=obj.getUname()%>"/>
        </td>
    </tr>
    <tr>
        <td>余额</td>
        <td>

            <input type="text" name="bankMoney" readonly="readonly" value="0" id="money"/>
        </td>
    </tr>

    <tr>
        <td></td>
        <td>
            <input type="button" id="num2" value="确认提交" />
        </td>
    </tr>


    </tbody>
</table>
</form>
</div>
<script type="text/javascript" >
    //下拉框的异步加载
    $(function () {
        /*提交*/
        $("#num2").click(()=>{
            $('#bam').form('submit', {
                url:"<%=path%>/tbank/ins",
                success:(data)=>{
                    if(data == 200){
                        setTimeout(()=>{
                            alert("成功");
                            var nums =  $("#num1").val();
                            var sum = parseInt(nums);
                            $("#num1").val(sum+1);
                        },600)
                    }else {
                        alert(data+"ERROR,请联系管理员")
                    }
                }
            })
        })





            /*--加载-*/
        $.ajax({
            url:"<%=path%>/tbank",
            dataType:"text",
            type:"get",
            success:(data)=>{
                $("#num1").val(data)
            },
            error:()=>{
                alert("ERRORERROR")
            }
        })

        /*动态加载银行*/
        $.get("<%=path%>/bank/getBank", function (data) {
            console.log(data)
            $("#csadd").combobox({
                data:data,
                valueField:'bankId',
                textField:'bankName',
                panelHeight:'auto'
            });
        },"json");
    })
    var changeMoney=(val)=>{
        $("#money").val(val)
    }

</script>

</body>
</html>
