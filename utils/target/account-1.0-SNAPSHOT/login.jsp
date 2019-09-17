<%--
  Created by IntelliJ IDEA.
  User: THE GIFTED
  Date: 2019/7/23
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <script src="js/jquery-3.3.1.js" type="text/javascript" ></script>
    <script src="js/jquery.cookie.js" type="text/javascript" ></script>
</head>
<body>
    <h1>登陆注册</h1>

        <%String path = request.getContextPath();%>
        <div style="width: 500px;height: 500px;background-color: #bababa;align-content: center">

                <form align="center" id="uinfo">
                       用户名称 <input type="text" name="uname" />
                        <hr/>
                        密码:<input type="text" name="upwd"/>
                    <hr/>
                    验证码:<input type="text" size="2" name="code"/>
                    <hr><img src="validcode" width="100" height="60"/>
                    <a href="#">看不清</a>
                    <hr/>
                    输入邮箱用于接收验证码:<br>
                    <input type="text" id="email" /><span></span>
                    <hr/>
                    <input type="button" id="1001" value="提交1001"/>

                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="reset" value="重置"/>
                    <input type="checkbox" name="three" onclick="remeberMe(this)" value="true">记住我

                </form>


        </div>
        <script>
                var remeberMe=(aaa)=> {
                }

                var checkLogin=()=>{
                    var token = $.cookie("TOK");
                    if(!token){
                        return;
                    }
                    location.replace("<%=path%>/index");
                }

                $(function () {

                    /*三天免登陆*/
                    checkLogin();










                    // var rex = /^$/

                    $("a").click(function(){
                        //浏览器带有缓存功能,不会多次请求相同数据
                        $("img").attr("src","validcode?date="+new Date());
                        return false;
                    })


                    $("#1001").click(()=>{
                        $.post("<%=path%>/login/user",$("#uinfo").serialize(),(data)=>{
                            console.log(data)
                            if(data.status == 200){
                                setTimeout(()=>{
                                    alert(data.data);
                                    location.replace("<%=path%>/index");
                                },580)
                            }else if(data.status == 5001) {
                                alert("验证码错误")
                            }else {
                                alert("Error")
                            }
                        },"json")

                    })



                })

        </script>


</body>
</html>
