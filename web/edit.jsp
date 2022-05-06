
<%@ page contentType="text/html;charset=UTF-8"%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改部门</title>
</head>
<body>
    <h1 align="center">修改部门</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/dept/modify" method="post" align="center">
        部门编号:<input type="text" name="deptno" value="${dept.deptno}" readonly/><br>
        部门名称:<input type="text" name="dname" value="${dept.dname}"/><br>
        部门位置:<input type="text" name="loc" value="${dept.loc}"/><br><br>
        <input type="submit" value="修改部门信息"/><br>
    </form>
</body>
</html>