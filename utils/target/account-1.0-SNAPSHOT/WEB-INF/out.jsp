<%--
  Created by IntelliJ IDEA.
  User: THE GIFTED
  Date: 2019/7/24
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>1001</title>
</head>
<body> <%String path = request.getContextPath();%>
        <c:if test="${ins == 1}">
            <h2>储蓄卡取款</h2>
        </c:if> <c:if test="${ins == 2}">
            <h2>信用卡取款</h2>
        </c:if>
        <form id="out_form">
            <input type="hidden" name="type" value="${ins}" />
            <input type="hidden" name="bankNum" value="${bnum}" />
            输入金额:<input type="text" class="easyui-numberbox"  name="money" data-options="suffix:'￥',min:0,precision:2"></input>
            <hr/>
            <input type="button" id="out_btn" value="取款" />
        </form>
<script>
        $(function () {
            $("#out_btn").click(()=>{
                $.post("<%=path%>/tbank/outmoney",$("#out_form").serialize(),(data)=>{
                    if(data == 200){
                        alert("成功")
                        $('#dd').dialog('close')
                        $('#user_table').datagrid('reload')
                    }else if(data == 5002 ){
                        alert("超过了你的额度,请重新输入")
                    }else {
                        alert("ERROR")
                    }

                },"text")

            })
        })

</script>
</body>
</html>
