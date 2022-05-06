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

<form action="${pageContext.request.contextPath}/user/login" method="post">
    <table align="center">
        <tr>
            <th>用户名：</th>
            <th>
                <input type="text" name="username"/>
            </th>
        </tr>
        <tr>
            <th>密码：</th>
            <th>
                <input type="password" name="password"/>
            </th>
        </tr>
        <tr>
            <th>
                <input type="checkbox" name="f" value="1"/>
            </th>
            <td>十天内免登录</td>

        </tr>
    </table>
    <br>
    <div align="center">
        <input type="submit" value="欢迎登录"/>
    </div>




</form>


</body>
</html>
