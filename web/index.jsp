<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session ="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎使用oa系统</title>
</head>
<body>


<h1 align="center">欢迎来到oa系统！</h1>
<br>
<script type="text/javascript">
    function loginnew(){
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var f = document.getElementById("f").value;

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function (){
            var tipMsg = document.getElementById("tipMsg");
            tipMsg.innerHTML = "<font color = 'green'>正在进行身份验证，请稍后！</font>";
            if(xhr.readyState == 4){
                if(xhr.status == 200){
                     //var s = xhr.responseText;
                     var jsonString = xhr.responseText;
                     eval("var jsonObj = "+jsonString);
                    //var tipMsg = document.getElementById("tipMsg");
                     if(jsonObj.success){

                         tipMsg.innerHTML = "<font color = 'green'>用户名和密码正确，正在登录！</font>";
                         document.location.href= "${pageContext.request.contextPath}/dept/list";
                     }else {
                         tipMsg.innerHTML = "<font color = 'red'>用户名不存在或密码错误，请重新输入！</font>";
                     }
                    //var nameTipMsg = document.getElementById("nameTipMsg");
                   // nameTipMsg.innerHTML = xhr.responseText;
                }else{
                    alert(xhr.status);
                }
            }
        }
        xhr.open("POST","${pageContext.request.contextPath}/user/login",true);
        xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
        xhr.send("username="+username+"&password="+password+"&f="+f);
    }
</script>

<%--<form action="${pageContext.request.contextPath}/user/login" method="post">--%>
    <table align="center">
        <tr>
            <th>用户名：</th>
            <th>
                <input type="text" id="username"/>
            </th>
        </tr>
        <tr>
            <th>密码：</th>
            <th>
                <input type="password" id="password"/>
            </th>
        </tr>
        <tr>
            <th>
                <input type="checkbox" id="f" value="1"/>
            </th>
            <td>十天内免登录</td>

        </tr>
    </table>
    <br>
    <div align="center">
        <input type="button" value="欢迎登录" onclick="loginnew();"/>
    </div>
<br>

<span id="tipMsg" style="display:block;text-align:center;font-size: 12px;"></span>





<%--</form>--%>


</body>
</html>