<%--
  Created by IntelliJ IDEA.
  User: THE GIFTED
  Date: 2019/7/31
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>批量新增</title>
    <%String path = request.getContextPath();%>
    <script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="<%=path %>/js/echarts.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=path %>/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>

            <h2><span style="color: #5f5f5f">新增infomation</span></h2>
            <span style="color: #449d44"><h3>不知道怎么上传??</h3></span>
            <a href="##"><b>点我获取模板!!</b></a>
            <form id="upload-form"   method="post" enctype="multipart/form-data" >
              选择标准格式的 <font size="3" color="red">EXCEl</font> :
                <hr>
                <input type="file" name="file"  />
                <hr>
                <input  type="button" id="up_btn1" value="确认"/>
            </form>
        <script>
            $(function () {
                $("#up_btn1").click(()=>{
                    var formData = new FormData(document.getElementById("upload-form"));
                    $.ajax({
                        url: "<%=path%>/tbank/batch",
                        method: 'POST',
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (resp) {
                            console.log(resp)
                            if(resp.status == 200){
                                alert("成功");
                                $('#dd').dialog('close')
                                $('#user_table').datagrid('reload')
                            }else if(resp.status == 202){
                                alert(resp.WARN)
                                $('#dd').dialog('close')
                                $('#user_table').datagrid('reload')
                            }else {
                                alert("ERROR..........失败")
                            }
                        }
                    });
                })
            })

        </script>

</body>
</html>
